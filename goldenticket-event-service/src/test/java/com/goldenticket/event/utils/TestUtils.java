package com.goldenticket.event.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    public static int MAX_TEST_DATA_SIZE = 20;

    public static String anyString() {
        int length = MAX_TEST_DATA_SIZE;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }

    public static UUID anyUuid() {
        return UUID.randomUUID();
    }

    public static <T> List<T> anyListOf(Supplier<T> supplier) {
        return IntStream.range(0, MAX_TEST_DATA_SIZE)
                        .mapToObj(i -> supplier.get())
                        .collect(Collectors.toList());
    }


    public static <T extends Enum<?>> T anyEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new IllegalArgumentException("Enum class must have constants");
        }

        Random random = new Random();
        return enumConstants[random.nextInt(enumConstants.length)];
    }
}
