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

public class Main {
    public static void main(String[] args) {
        // Prompt the user to enter an English string
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an English string: ");
        String englishString = scanner.nextLine();

        // Convert the English string to your new alphabet string
        NewAlphabet newAlphabet = new NewAlphabet();
        StringBuilder newAlphabetString = generateNewAlphabetString(englishString, newAlphabet);

        // Display the new alphabet string in pairs
        System.out.println("New Alphabet String: " + formatSymbolsInPairs(newAlphabetString));

        // Calculate SHA256 hash of new alphabet, then display
        String hashValue = toSHA256(newAlphabetString.toString());
        System.out.println("SHA256 Hash of New Alphabet String: " + hashValue);

        // Print shifts 1 to 5 for comparison
        for (int shift = 1; shift <= 5; shift++) {
            // Special case for shift #5
            String shiftedEnglishString = shift == 5 ? "HAPPYEASTER" : caesarCipherEncrypt(englishString, shift);
            StringBuilder shiftedNewAlphabetString = generateNewAlphabetString(shiftedEnglishString, newAlphabet);

            System.out.println("\nShift #" + shift + ":");
            System.out.println("Shift in normal string: " + shiftedEnglishString);
            System.out.println("Shift with new alphabet string: " + formatSymbolsInPairs(shiftedNewAlphabetString));
        }
        // Brute force decryption for shifts 0 to 25
        System.out.println("\nBrute Force Decryption:");
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedString = generateDecryptedString(newAlphabetString, shift, newAlphabet);
            System.out.println("Shift " + shift + ": " + formatSymbolsInPairs(decryptedString));
        }
    }
    // Generates new alphabet string given input string

    private static StringBuilder generateNewAlphabetString(String input, NewAlphabet newAlphabet) {
        StringBuilder newAlphabetString = new StringBuilder();
        for (char letter : input.toUpperCase().toCharArray()) {
            char symbol = newAlphabet.getSymbol(letter);
            newAlphabetString.append(symbol);
        }
        return newAlphabetString;
    }
    // Format symbols in pairs for ease of reading
    private static String formatSymbolsInPairs(StringBuilder symbols) {
        StringBuilder pairedSymbols = new StringBuilder();
        for (int i = 0; i < symbols.length(); i += 2) {
            if (i + 1 < symbols.length()) {
                pairedSymbols.append(symbols.charAt(i)).append(symbols.charAt(i + 1)).append(" ");
            } else {
                // Handle the last single symbol if the string length is odd
                pairedSymbols.append(symbols.charAt(i));
            }
        }
        return pairedSymbols.toString().trim();
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
    // Decrypts encrypted string by reversing Caesar cipher
    private static StringBuilder generateDecryptedString(StringBuilder encryptedString, int shift, NewAlphabet newAlphabet) {
        StringBuilder decryptedString = new StringBuilder();
        for (char symbol : encryptedString.toString().toCharArray()) {
            char letter = newAlphabet.getLetter(symbol);
            if (Character.isLetter(letter)) {
                char base = Character.isUpperCase(letter) ? 'A' : 'a';
                char decryptedLetter = (char) (((letter - base - shift + 26) % 26) + base);
                decryptedString.append(decryptedLetter);
            } else {
                decryptedString.append(letter);
            }
        }
        return decryptedString;
    }
    // Computes SHA-256 hash given input string
    public static String toSHA256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
