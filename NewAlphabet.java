/** Purpose Details: Define new alphabet to change letters to symbols and back to letters.
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 3/31/24
 * Last Date Changed:3/31/24
 * Rev: 1.0

        */
import java.util.HashMap;
import java.util.Map;

public class NewAlphabet {
    // Mapping letters to symbols in new alphabet
    private Map<Character, Character> alphabetMapping;
    // Constructs new alphabet with mappings

    public NewAlphabet() {
        // Initialize the alphabet mapping
        alphabetMapping = new HashMap<>();
        alphabetMapping.put('A', '~');
        alphabetMapping.put('B', '!');
        alphabetMapping.put('C', '@');
        alphabetMapping.put('R', '#');
        alphabetMapping.put('E', '$');
        alphabetMapping.put('T', '%');
        alphabetMapping.put('S', '^');
        alphabetMapping.put('H', '&');
        alphabetMapping.put('Y', '&');
        alphabetMapping.put('P', '*');
    }

    // Method to get the symbol corresponding to a given letter
    public char getSymbol(char letter) {
        // Convert letter to uppercase
        letter = Character.toUpperCase(letter);
        // Get the symbol from the mapping
        return alphabetMapping.getOrDefault(letter, letter); // Return the letter itself if not found in the mapping
    }

    // Method to get the letter corresponding to a given symbol
    public char getLetter(char symbol) {
        // Going through the mappings to find the matching symbol
        for (Map.Entry<Character, Character> entry : alphabetMapping.entrySet()) {
            if (entry.getValue() == symbol) {
                return entry.getKey();
            }
        }
        // Return the symbol if not found in the mapping
        return symbol;
    }
    // Method for testing NewAlphabet class

    public static void main(String[] args) {
        // Create instance of new alphabet
        NewAlphabet newAlphabet = new NewAlphabet();

        // Example usage
        char englishLetter = 'A';
        char symbol = newAlphabet.getSymbol(englishLetter);
        System.out.println("Symbol for " + englishLetter + ": " + symbol);

        char englishLetterBack = newAlphabet.getLetter(symbol);
        System.out.println("English letter for " + symbol + ": " + englishLetterBack);
    }
}