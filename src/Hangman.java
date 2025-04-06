import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



class EmptySetOfWordsException extends RuntimeException {
    public EmptySetOfWordsException(String message) {
        super(message);
    }
}

class WordsSet {
    private Set<String> words;
    private final static String filePath = "C:/Users/VICTUS/Desktop/Code/JAVA/Test/words.txt";

    public WordsSet() throws EmptySetOfWordsException {
        this.words = loadWords();
        if (words.isEmpty()) {
            throw new EmptySetOfWordsException("Word Set is empty! You need to add some words to the Word Set!");
        }
    }

    public Set<String> loadWords() {
        Set<String> wordsSet = new HashSet<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                wordsSet.add(line.trim());
            }
            return wordsSet;
        } catch (IOException e) {
            System.out.println("Failed to load the words!");
        }
        return wordsSet;
    }

    public void saveWords() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false));
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println("Failed to add the words!");
        }
    }

    public void addWords(Scanner scanner) {
        System.out.println("Please enter the words that you want to add to the Word Set!");
        System.out.println("Please note that duplicate words will not be added multiple times.");
        System.out.println("Entering the word \"done\" will stop this process!");
        while (true) {
            String word = scanner.nextLine().trim();
            if (word.equalsIgnoreCase("done")) {
                System.out.println("Adding words process complete");
                break;
            }

            if (word.isEmpty()) {
                System.out.println("You have entered an empty word!");
            } else if (words.contains(word)) {
                System.out.println("The word " + word + " that you are trying to add already exists in the Word Set!");
            } else {
                System.out.println(word + " has been successfully added to the Word Set!");
                words.add(word);
            }
        }
        saveWords();
    }

    public void removeWords(Scanner scanner) {
        System.out.println("Please enter the words that you wish to remove!");
        System.out.println("Entering the word \"done\" will stop this process!");
        while (true) {
            String word = scanner.nextLine().trim();
            if (word.equalsIgnoreCase("done")) {
                System.out.println("Removing words process complete!");
                break;
            }

            if (word.isEmpty()) {
                System.out.println("You have entered an empty word!");
            } else if (!words.contains(word)) {
                System.out.println("The word " + word + " that you are trying to remove is not in the Word Set!");
            } else {
                System.out.println(word + " has been successfully removed from the Word Set!");
                words.remove(word);
            }
        }
        saveWords();
    }

    public String getRandomWord() {
        Random random = new Random();
        String[] array = words.toArray(new String[0]);
        return array[random.nextInt(array.length)];
    }

    public void printAllWords() {
        for(String word : words) {
            System.out.println(word);
        }
    }
}



















