import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Welcome to Hangman!");
        final Random random = new Random();
        final Scanner scanner = new Scanner(System.in);
        final String[] words = {"java", "skillmea", "python", "programming", "webinar"};
        final String wordToGuess = selectWord(words, random);
        String hiddenWord = generateHiddenWord(wordToGuess);
        System.out.print("\nGuess the word: " + hiddenWord);

        final int MAX_INCORRECT_GUESSES = 6;
        int incorrectGuesses = 0;

        while (incorrectGuesses < MAX_INCORRECT_GUESSES && hiddenWord.contains("_")){
            System.out.print("\nEnter a letter: ");
            char guess = scanLetter(scanner);
            if (wordToGuess.contains(String.valueOf(guess))){
                hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                System.out.println("Correct guess! Updated word: " + hiddenWord);
            }else{
                incorrectGuesses++;
                System.out.println("Incorrect guess! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " guesses left");
            }
        }
    }
    public static char scanLetter(Scanner scanner){
        char guess;
        while (true){
            try{
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line length is not 1. Please enter a single letter");
                }
                guess = line.charAt(0);
                if (!Character.isLetter(guess)){
                    throw new Exception("Please enter a single letter");
                }
                break;
            } catch (Exception e){
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        return guess;
    }
    public static String selectWord(String[] words, Random random){ // metoda ma vratit string v pocte
        return words[random.nextInt(words.length)];
    }
    public static String generateHiddenWord (String word){
        return "_".repeat(word.length());
    }
    public static String revealLetters(String wordToGuess, String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();

        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }

        return String.valueOf(hiddenWordChars);
    }
}