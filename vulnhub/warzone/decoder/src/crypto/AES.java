package crypto;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(new byte[19]);

    private static final String ALGORITHM = "AES";

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private Key key;

    private IvParameterSpec iv;

    private Cipher cipher;

    public AES(String key) {
        this(key, 128);
    }

    public AES(String key, int bit) {
        this(key, bit, null);
    }

    public AES(String key, int bit, String iv) {
        if (bit == 256) {
            this.key = new SecretKeySpec(getHash("SHA-256", key), "AES");
        } else {
            this.key = new SecretKeySpec(getHash("MD5", key), "AES");
        }
        if (iv != null) {
            this.iv = new IvParameterSpec(getHash("MD5", iv));
        } else {
            this.iv = DEFAULT_IV;
        }
        init();
    }

    private static byte[] getHash(String algorithm, String text) {
        try {
            return getHash(algorithm, text.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static byte[] getHash(String algorithm, byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(data);
            return digest.digest();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private void init() {
        try {
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String encrypt(String str) {
        try {
            return encrypt(str.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String decrypt(String str) {
        try {
            return decrypt(str.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String encrypt(byte[] data) {
        try {
            this.cipher.init(1, this.key, this.iv);
            byte[] encryptData = this.cipher.doFinal(data);
            return new String(Base64.getEncoder().encode(encryptData));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String decrypt(byte[] data) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.iv);
            byte[] encryptData = this.cipher.doFinal(data);
            return new String(Base64.getEncoder().encode(encryptData));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String encryptString(String content) {
        Obfuscated obs = new Obfuscated();
        AES ea = new AES(obs.getIV(), 128, obs.getKey());
        return ea.encrypt(content);
    }

    public static String decryptString(String content) {
        Obfuscated obs = new Obfuscated();
        AES ea = new AES(obs.getIV(), 128, obs.getKey());

        byte[] data = Base64.getDecoder().decode(content);
        byte[] dec_data;

        try {
            ea.cipher.init(Cipher.DECRYPT_MODE, ea.key, ea.iv);
            dec_data = ea.cipher.doFinal(data);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return new String(dec_data);
    }
}

class Obfuscated {
    public String getIV() {
        return "w4rz0n3s3cur31vv";
    }

    public String getKey() {
        return "w4rz0n3s3cur3k3y";
    }
}