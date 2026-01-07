package com.sapo.customer.util;

public class StringUtils {
    public static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
