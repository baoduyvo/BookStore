package org.voduybao.bookstorebackend.tools.utils;


import java.util.UUID;

public class RandomNumberGenerator {
    public static String generateRandomNumber() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        while (uuid.length() < 40) {
            uuid += UUID.randomUUID().toString().replaceAll("[^0-9]", "");
        }
        return uuid.substring(0, 40);
    }
}