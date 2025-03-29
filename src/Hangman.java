import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;


class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String message) {
        super(message);
    }
}

class GameMechanics {
    private BufferedWriter br;
    private Random random;
    private List<String> words;
    private List<String> guessedLetters;
    private final String filePath = "C:/Users/VICTUS/Desktop/Code/JAVA/Test/words.txt";

    public GameMechanics() {


    }
    public void addWordsToListAndFile(List<String> wordsToAdd) throws DuplicateItemException {
        for(String word : wordsToAdd) {
            if(words.contains(word)) {
                throw new DuplicateItemException("This word already exists");
            }
            else {
                words.add(word);
            }
        }
        addWordsToFile();
        words.clear();
    }
    public void addWordsToFile() {
        try {
            br = new BufferedWriter(new FileWriter(filePath, true));
            for (String word : words) {
                br.write(word);
                br.newLine();
            }
        }
        catch(IOException e) {
            System.out.println("An unexpected error");
        }
    }
}

