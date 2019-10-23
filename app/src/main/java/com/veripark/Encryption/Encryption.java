package com.veripark.Encryption;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class Encryption {

    public static String encript(String aesIV, String aes, String plainText) {
        try {
            byte[] iv = new byte[256/8];
            iv = Base64.getDecoder().decode(aesIV);
            IvParameterSpec ivspec = new IvParameterSpec(iv);


            byte[] keyb = new byte[256/8];
            keyb = Base64.getDecoder().decode(aes);
            SecretKeySpec skey = new SecretKeySpec(keyb, "AES");

            Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);

            byte[] encoded = ci.doFinal(plainText.getBytes("UTF-8"));
            String cyperText = Base64.getEncoder().encodeToString(encoded);
            return cyperText;
        }
        catch (Exception ex){
            Log.println(Log.ERROR, "encript service",ex.getMessage());
        }
        return  "";

    }

    public static String decript(String aesIV, String aes, String cyperText)  {
        try{
            byte[] iv = new byte[256/8];
            iv = Base64.getDecoder().decode(aesIV);
            IvParameterSpec ivspec = new IvParameterSpec(iv);


            byte[] keyb = new byte[256/8];
            keyb = Base64.getDecoder().decode(aes);
            SecretKeySpec skey = new SecretKeySpec(keyb, "AES");

            Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
            ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);

            byte[] encoded = ci.doFinal(Base64.getDecoder().decode(cyperText));
            String plainText = Base64.getEncoder().encodeToString(encoded);
            return plainText;
        }
        catch (Exception ex){
            Log.println(Log.ERROR, "encript service",ex.getMessage());
        }
        return  "";
    }
}
