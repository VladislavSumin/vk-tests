package ru.falseteam.vktests;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static SecureRandom random = new SecureRandom();

    /**
     * different dictionaries used
     */
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    public static String generatePassword(int len) {
        return generatePassword(len, ALPHA_CAPS + ALPHA + NUMERIC);
    }

    /**
     * Method will generate random string based on the parameters
     *
     * @param len the length of the random string
     * @param dic the dictionary used to generate the password
     * @return the random password
     */
    public static String generatePassword(int len, String dic) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result.append(dic.charAt(index));
        }
        return result.toString();
    }
}
