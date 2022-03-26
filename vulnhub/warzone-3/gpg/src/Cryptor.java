import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Cryptor {
    private String secret;

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String encrypt(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, aesKey);
            String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(text)));
            return decrypted;
        } catch (InvalidKeyException i) {
            System.out.println("[x] Invalid key length {16 required}");
            return null;
        }
    }
}
