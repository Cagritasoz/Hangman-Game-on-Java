import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.io.BufferedWriter;


class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String message) {
        super(message);
    }
}

class GameMechanics {
    private BufferedWriter bw;
    private BufferedReader br;
    private Random random;
    private List<String> words;
    private List<String> guessedLetters;
    private final String filePath = "C:/Users/VICTUS/Desktop/Code/JAVA/Test/words.txt";

    public GameMechanics() {


    }

    public List<String> getCurrentList() { //To get the current List
        List<String> currentList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                currentList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("An unexpected error has been detected with the Word List");
        }
        return currentList;
    }

    public void checkNewWords(List<String> wordsToAdd) throws DuplicateItemException { //To check whether the new words that the user want to add has any duplicates
        for (String word : wordsToAdd) {
            if (!words.contains(word)) {
                words.add(word);
            } else {
                throw new DuplicateItemException(word + " is already in the list, please enter a another word!");
            }
        }
    }

    public void addNewWordsToList() { //To add the new words that the user want to add and also checks for duplicates,doesn't add already existing words.
        List<String> currentList = getCurrentList();
        try {
            bw = new BufferedWriter(new FileWriter(filePath, true));
            for (String word : words) {
                if (!currentList.contains(word)) {
                    bw.write(word);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unexpected error");
        }
    }

    public void printWordList() {
        List<String> currentList = getCurrentList();
        for (String word : currentList) {
            System.out.println(word);
        }
    }

    public String getRandomWord() {
        random = new Random();
        List<String> currentList = getCurrentList();
        String word = currentList.get(random.nextInt(currentList.size()));
        return word;
    }

    public String[] getRandomWordLetters() {
        String randomWord = getRandomWord();
        String[] letters = new String[randomWord.length()];
        for (int i = 0; i < randomWord.length(); i++) {
            letters[i] = randomWord.substring(i, i+1);
        }
        return letters;
    }
    public void addGuessedLetters(String letter) throws DuplicateItemException {
        if(!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
        }
        else {
            throw new DuplicateItemException("You have already guessed this letter");
        }
    }
    public void printGuessedLetters() {
        System.out.println("Guessed letters are:");
        for(String letter : guessedLetters) {
            System.out.print("  " + letter);
        }
    }
    public List<String> getGuessedLetters() {
        return guessedLetters;
    }
    public void printGuessedWord() {
        


    }
}