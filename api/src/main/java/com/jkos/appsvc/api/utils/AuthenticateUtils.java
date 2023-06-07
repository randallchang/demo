package com.jkos.appsvc.api.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class AuthenticateUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private AuthenticateUtils() {
    }

    public static Map<String, String> decrypt(String src, String key, String iv) {

        try {
            SecretKeySpec keySpec =
                new SecretKeySpec(
                    key.getBytes(StandardCharsets.US_ASCII), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec =
                new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            byte[] encrypted = Base64.getDecoder().decode(src);
            byte[] original = cipher.doFinal(encrypted);
            String json = new String(original, StandardCharsets.UTF_8);

            return OBJECT_MAPPER.readValue(
                json, new TypeReference<Map<String, String>>() {});

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return Collections.emptyMap();
    }
}