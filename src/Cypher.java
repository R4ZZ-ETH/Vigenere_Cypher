import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class Cypher {
    private String key, IV;
    public Cypher(String key, String IV){
        this.key = key;
        this.IV = IV;
    }
    public String encrypt(String message){
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encryptedMessage = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedMessage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encryptedMessage){
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
