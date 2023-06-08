package com.jkos.appsvc.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jkos.appsvc.api.constants.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class ConvertUtil {

    private ConvertUtil() {}

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> T instantiateClass(Object source, Class<T> clazz) {
        T target = BeanUtils.instantiateClass(clazz);
        copyNonNullProperties(source, target);
        return target;
    }

    public static <T> T deepCloneInstantiateClass(Object source, Class<T> clazz) {

        try {
            return OBJECT_MAPPER.readValue(OBJECT_MAPPER.writeValueAsString(source), clazz);
        } catch (JsonProcessingException ex) {
            throw ApiError.UNEXPECTED.toException();
        }
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName ->
                    wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
