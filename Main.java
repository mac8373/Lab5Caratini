/** Project: Lab5Caratini
 * Purpose Details: Performs various encryption and decryption processes like generating a SHA-256 hash value, Caesar Cipher, and Brute force decryption.
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 3/31/24
 * Last Date Changed:3/31/24
 * Rev: 1.0
 */

import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        // Prompt the user to enter an English string
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an English string: ");
        String englishString = scanner.nextLine();

        // Convert the English string to your new alphabet string
        NewAlphabet newAlphabet = new NewAlphabet();
        StringBuilder newAlphabetString = new StringBuilder();
        for (char letter : englishString.toCharArray()) {
            char symbol = newAlphabet.getSymbol(letter);
            newAlphabetString.append(symbol);
        }

        // Display the new alphabet string
        System.out.println("New Alphabet String: " + newAlphabetString.toString());

        // Encrypt new alphabet string with Caesar cipher shift 5
        String encryptedText = caesarCipherEncrypt(newAlphabetString.toString(), 5);
        System.out.println("Encrypted String: " + encryptedText);

        // Decrypt text back to original state
        String decryptedText = caesarCipherDecrypt(encryptedText, 5);
        System.out.println("Decrypted String: " + decryptedText);

        // Calculate SHA256 hash of the new alphabet string
        String sha256Hash = calculateSHA256Hash(newAlphabetString.toString());
        System.out.println("SHA256 Hash Value: " + sha256Hash);

        // Brute force decryption
        System.out.println("Brute Force Decryption:");
        for (int i = 0; i < 26; i++) {
            String decrypted = caesarCipherDecrypt(encryptedText, i);
            System.out.println("Shift " + i + ": " + decrypted);
        }

    }
    // Method to perform Caesar cipher encryption with a given shift
    private static String caesarCipherEncrypt(String input, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                encryptedText.append((char) (((ch - base + shift) % 26) + base));
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    // Method to perform Caesar cipher decryption with a given shift
    private static String caesarCipherDecrypt(String input, int shift) {
        return caesarCipherEncrypt(input, 26 - shift);
    }
    // Method for calculating SHA-256 hash of a string

    private static String calculateSHA256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}