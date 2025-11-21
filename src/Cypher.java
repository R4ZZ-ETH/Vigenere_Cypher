import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class Cypher {
    private final String key;
    public Cypher(String key){
        this.key = key; 
    }
    public String encrypt(String message){
        try {
            byte[] ivBytes = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encryptedMessage = cipher.doFinal(message.getBytes("UTF-8"));

            byte[] combinedMessage = new byte[ivBytes.length + encryptedMessage.length];
            System.arraycopy(ivBytes, 0, combinedMessage, 0, ivBytes.length);
            System.arraycopy(encryptedMessage, 0, combinedMessage, ivBytes.length, encryptedMessage.length);

            return Base64.getEncoder().encodeToString(combinedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encryptedMessage){
        try {
            byte[] combinedMessage = Base64.getDecoder().decode(encryptedMessage);

            byte[] ivBytes = new byte[16];
            System.arraycopy(combinedMessage, 0, ivBytes, 0, 16);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            byte[] encryptedBytes = new byte[combinedMessage.length - 16];
            System.arraycopy(combinedMessage, 16, encryptedBytes, 0, encryptedBytes.length);

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
