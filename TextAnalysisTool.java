import java.util.*;

public class TextAnalysisTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Welcome to the Text Analysis Tool! ===");
        System.out.println("Please enter a paragraph or lengthy text:");
        String text = scanner.nextLine();
        
        // Check if user entered empty text
        if (text.trim().isEmpty()) {
            System.out.println("Error: You didn't enter any text. Please run the program again.");
            return;
        }
        
        System.out.println("\n--- Analysis Results ---");
        
        // Task 1: Character Count
        int charCount = text.length();
        System.out.println("1. Total characters: " + charCount);
        
        // Task 2: Word Count
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        System.out.println("2. Total words: " + wordCount);
        
        // Task 3: Most Common Character
        char mostCommon = findMostCommonChar(text);
        System.out.println("3. Most common character: '" + mostCommon + "'");
        
        // Task 4: Character Frequency
        System.out.print("\n4. Enter a character to check its frequency: ");
        String charInput = scanner.nextLine();
        if (!charInput.isEmpty()) {
            char searchChar = charInput.charAt(0);
            int charFreq = countCharFrequency(text, searchChar);
            System.out.println("   Frequency of '" + searchChar + "' (case-insensitive): " + charFreq);
        }
        
        // Task 5: Word Frequency
        System.out.print("\n5. Enter a word to check its frequency: ");
        String searchWord = scanner.nextLine();
        if (!searchWord.trim().isEmpty()) {
            int wordFreq = countWordFrequency(text, searchWord);
            System.out.println("   Frequency of \"" + searchWord + "\" (case-insensitive): " + wordFreq);
        }
        
        // Task 6: Unique Words
        int uniqueWords = countUniqueWords(text);
        System.out.println("\n6. Number of unique words (case-insensitive): " + uniqueWords);
        
        System.out.println("\n=== Analysis Complete! ===");
        scanner.close();
    }
    
    // Method to find most common character
    public static char findMostCommonChar(String text) {
        text = text.toLowerCase().replaceAll("\\s", ""); // Remove spaces for character analysis
        if (text.isEmpty()) return ' ';
        
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }
        
        char mostCommon = ' ';
        int maxCount = 0;
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommon = entry.getKey();
            }
        }
        
        return mostCommon;
    }
    
    // Method to count character frequency (case-insensitive)
    public static int countCharFrequency(String text, char searchChar) {
        int count = 0;
        String lowerText = text.toLowerCase();
        char lowerSearchChar = Character.toLowerCase(searchChar);
        
        for (char c : lowerText.toCharArray()) {
            if (c == lowerSearchChar) {
                count++;
            }
        }
        return count;
    }
    
    // Method to count word frequency (case-insensitive)
    public static int countWordFrequency(String text, String searchWord) {
        String lowerText = text.toLowerCase();
        String lowerSearchWord = searchWord.toLowerCase();
        String[] words = lowerText.split("\\s+");
        
        int count = 0;
        for (String word : words) {
            // Remove punctuation from word for accurate comparison
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            if (word.equals(lowerSearchWord)) {
                count++;
            }
        }
        return count;
    }
    
    // Method to count unique words (case-insensitive)
    public static int countUniqueWords(String text) {
        String lowerText = text.toLowerCase();
        String[] words = lowerText.split("\\s+");
        Set<String> uniqueWords = new HashSet<>();
        
        for (String word : words) {
            // Remove punctuation and add to set
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            if (!word.isEmpty()) {
                uniqueWords.add(word);
            }
        }
        return uniqueWords.size();
    }
}
