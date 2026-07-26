package com.contactbook.contactbook.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final int SALT_LENGTH = 16;
    private static final String HASH_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        try {
            //Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            //Hash password with salt
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes());

            //Combine salt and hash, then encode to BASSE64
            String saltBase64 = Base64.getEncoder().encodeToString(hash);
            String hashBase64 = Base64.getEncoder().encodeToString(hash);

            return saltBase64 + ":" + hashBase64;
        } catch (NoSuchAlgorithmException e) {
            throw new  RuntimeException("SHA-256 algorithm not available: " + e.getMessage());
        }
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        try{
            String[] parts = hashedPassword.split(":");
            String saltBase64 = parts[0];
            String hashBase64 = parts[1];

            //Decode salt and hash from BASE64
            byte[] saltByte = Base64.getDecoder().decode(saltBase64);
            byte[] hashByte = Base64.getDecoder().decode(hashBase64);

            //Hash the provided password using the same salt
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(saltByte);
            byte[] computedHash = digest.digest(password.getBytes());

            return MessageDigest.isEqual(computedHash, hashByte);
        }catch(IllegalArgumentException | NoSuchAlgorithmException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
}
