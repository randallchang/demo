package com.example.demo.client.util;

public class BitMap {

    private boolean[] bitMap1 = new boolean[(1 << 16) - 1];
    private boolean[] bitMap2 = new boolean[(1 << 16) - 1];

    public boolean isElementExist(Object object) {

        int hashCode = object.hashCode();

        int idx1  = hashCode >>> 16;
        int idx2  = hashCode << 16 >>> 16;

        boolean isExist = bitMap1[idx1] && bitMap2[idx2];

        bitMap1[idx1] = true;
        bitMap2[idx2] = true;

        return isExist;
    }
}
