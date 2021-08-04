package example.security;

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESUtils {

    public static SecretKey generateKey() throws Exception {
      //Generate the secret key
      String password = "abcd1234";
      DESKeySpec key = new DESKeySpec(password.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      return keyFactory.generateSecret(key);
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
      Cipher encryptCipher = Cipher.getInstance("DES");
      encryptCipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] cipherByteArray = encryptCipher.doFinal(plainText.getBytes());
      return new String(cipherByteArray);
    }
}
