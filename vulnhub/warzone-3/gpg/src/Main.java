import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("[Warzone 3] Root's Password Manager");
            System.out.print("Secret passphrase : ");
            String secret = in.nextLine();
            Cryptor cryptor = new Cryptor();
            Resources res = new Resources();
            String user = cryptor.decrypt(secret, removeSalt(res.getCipher()));
            String sys = cryptor.decrypt(cryptor.decrypt(res.gotSecret(), removeSalt(res.getSecret())), removeSalt(res.getCipher()));

            String test = cryptor.decrypt(cryptor.decrypt(res.gotSecret(), removeSalt(res.getSecret())), removeSalt(res.getCipher()));
            System.out.println("[+] Success, the password is : " + test);

//            if (user.equals(sys)) {
//                String plaintext = cryptor.decrypt(cryptor.decrypt(res.gotSecret(), removeSalt(res.getSecret())), removeSalt(res.getCipher()));
//                System.out.println("[+] Success, the password is : " + plaintext);
//            } else {
//                System.out.println("[x] Failed");
//            }
        } catch (NullPointerException n) {
            System.out.println("[!] Terminated");
            System.exit(0);
        }
    }

    public static String removeSalt(String salted) {
        String unsalted = salted.replace("al13n", "");
        return unsalted;
    }
}
