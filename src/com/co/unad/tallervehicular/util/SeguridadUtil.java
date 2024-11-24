/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.util;


import java.security.SecureRandom;
import java.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Charlie
 */
public class SeguridadUtil {
     public static String hashPassword(String password, String salt) {
        try {
            return DigestUtils.sha256Hex(password + salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // Método para generar un salt aleatorio por si hay usuarios con la misma contraseña
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
