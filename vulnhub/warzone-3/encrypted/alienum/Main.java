package alienum;

import java.io.File;
import java.io.IOException;

public class Main {
    static String path = "aliens.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        String key = "w4rz0nerex0gener";
//        File inputFile = new File("aliens.txt");
//        File encryptedFile = new File("aliens.encrypted");
//        try {
//            Cryptor.encrypt(key, inputFile, encryptedFile);
//        } catch (CryptoException ex) {
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }

        String key = "w4rz0nerex0gener";
        File inputFile = new File("aliens.encrypted");
        File decryptedFile = new File("aliens.txt");
        try {
            Cryptor.decrypt(key, inputFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
