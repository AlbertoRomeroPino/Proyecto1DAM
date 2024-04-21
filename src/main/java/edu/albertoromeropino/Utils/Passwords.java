package edu.albertoromeropino.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Passwords {

    /**
     * Encripta la contraseña usando SHA3-256
     * @param password la contraseña que se va a encriptar
     * @return la contraseña encriptada
     */
    public static String encryptPassword(String password) {
        String hexString = null;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA3-256");

            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexStringBuilder = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }

                hexStringBuilder.append(hex);

            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString;
    }
}
