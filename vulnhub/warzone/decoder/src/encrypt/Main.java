package encrypt;

import crypto.AES;

import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Symmetric Encryption by Alienum");
        Scanner in = new Scanner(System.in);
        System.out.print("enter the password to decrypt : ");
        String password = in.nextLine();
        System.out.println("decrypted password : " + AES.decryptString(password));
        System.exit(0);
    }
}
