package utils;

import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SaltHashUtil {

    private static final Logger logger = Logger.getLogger(SaltHashUtil.class);

    public static String getSHA512SecurePassword(String passwordToHash, String salt) {

        String generatePassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatePassword = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm", e);
        }
        return generatePassword;
    }

    public static String getRandomeSalt() {
        byte[] array = new byte[6];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
