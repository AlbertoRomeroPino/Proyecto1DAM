package edu.albertoromeropino.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Validations {

    /**
     * Valida el nickName del usuario
     * @param nickName se le pasa el nombre de usuario
     * @return debuelve si es correcto o no
     */

    /*
        Este no lo uso porque me da problemas pero mas adelante lo implementare
        public static boolean validateNickName (String nickName){
        Pattern pattern = Pattern.compile("^(?!.*\\s)\\w{4,12}$");
        return pattern.matcher(nickName).matches();
    }*/

    /**
     * Valida el dni del usuario "muy basicamente"
     *
     * @param dni el dni que se le pasa
     * @return si es valido o no el dni
     */
    public static boolean validateDni(String dni) {
        Pattern pattern = Pattern.compile("^\\d{8}[a-zA-Z]$");
        return pattern.matcher(dni).matches();
    }


    /**
     * Encripta la contraseña usando SHA3-256
     *
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

            hexString = hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString;
    }
}
