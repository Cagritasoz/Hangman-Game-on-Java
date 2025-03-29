import java.io.*;
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

    public void loadWordsToList() {
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        catch(IOException e) {
            System.out.println("An unexpected error has been detected with the Word List");
        }
    }
    public void addNewWordsToList(List<String> wordsToAdd) throws DuplicateItemException {
        for(String word : wordsToAdd) {
            if(!words.contains(word)) {
                words.add(word);
            }
            else {
                throw new DuplicateItemException(word + " is already in the list, please enter a another word!");
            }
        }
    }
    public void addNewWordsToFile() {
        bw = new BufferedWriter(new FileWriter(filePath,true));
        
    }
}

