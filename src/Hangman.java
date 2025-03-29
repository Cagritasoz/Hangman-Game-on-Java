import java.io.*;
import java.util.*;
import java.io.BufferedWriter;



class DuplicateItemException extends RuntimeException {
    public DuplicateItemException(String message) {
        super(message);
    }
}

class GameMechanic {
    private List<String> words;
    private List<String> guessedLetters;
    private final String filePath = "C:/Users/VICTUS/Desktop/Code/JAVA/Test/words.txt";

    public GameMechanic() {
        this.words = new ArrayList<>();
        this.guessedLetters = new ArrayList<>();
    }

    public List<String> getCurrentList() { //To get the current List
        List<String> currentList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                currentList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("An unexpected error has been detected with the Word List");
        }
        return currentList;
    }

    public void checkNewWords(List<String> wordsToAdd) { //To check whether the new words that the user want to add has any duplicates
        for (String word : wordsToAdd) {
            if (!words.contains(word)) {
                words.add(word);
            }
        }
    }

    public void addNewWordsToList() { //To add the new words that the user want to add and also checks for duplicates,doesn't add already existing words.
        List<String> currentList = getCurrentList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
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
        Random random = new Random();
        List<String> currentList = getCurrentList();
        return currentList.get(random.nextInt(currentList.size()));
    }

    public String[] getRandomWordLetters() {
        String randomWord = getRandomWord();
        String[] letters = new String[randomWord.length()];
        for (int i = 0; i < randomWord.length(); i++) {
            letters[i] = randomWord.substring(i, i + 1);
        }
        return letters;
    }

    public void addGuessedLetters(String letter) throws DuplicateItemException {
        if (!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
        } else {
            throw new DuplicateItemException("You have already guessed this letter");
        }
    }

    public void printGuessedLetters() {
        System.out.println("Guessed letters are:");
        for (String letter : guessedLetters) {
            System.out.print("  " + letter);
        }
    }

    public List<String> getGuessedLetters() {
        return guessedLetters;
    }

    public String getGuessedWord(String randomWord) {
        StringBuilder sb = new StringBuilder();
        for (char letter : randomWord.toCharArray()) {
            if (guessedLetters.contains(String.valueOf(letter))) {
                sb.append(letter);
            } else {
                sb.append(" _");
            }
        }
        return sb.toString();
    }
class GameStart {
    private GameMechanic gameMechanic = new GameMechanic();
    private int fails;
    private List<String> wordsToAdd;
    public GameStart() {
}

    public void gameStart() {
        Scanner scanner = new Scanner(System.in);
        int gameStartScreenUserNumber;
        List<String> wordsToAdd = new ArrayList<>();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------Welcome to the Java Hangman Game!---------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Press 1 if you wish to start playing! You can only guess 6 wrong letters before failing!");
        System.out.println("Press 2 if you wish to add some words to the Word List!");
        while(true) {
            try {
                gameStartScreenUserNumber = scanner.nextInt();
                scanner.nextLine();
                if(gameStartScreenUserNumber == 1 || gameStartScreenUserNumber == 2) {
                    break;
                }
                else {
                    System.out.println("Please enter either 1 or 2");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter either 1 or 2");
                scanner.nextLine();
            }
        }
        if(gameStartScreenUserNumber == 2) {
            int numberOfWordsToAdd = 0;
            System.out.println("Please specify how many words you wish to add.");
            while (true) {
                try {
                    numberOfWordsToAdd = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    scanner.nextLine();
                }
            }
            System.out.println("You can start typing the words now! Duplicate words will be counted as one word!");
            while (true) {
                try {
                    for (int i = 0; i < numberOfWordsToAdd; i++) {
                        String word = scanner.nextLine();
                        scanner.nextLine();
                        if (!wordsToAdd.contains(word)) {
                            wordsToAdd.add(word);
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a word");
                    scanner.nextLine();
                }
            }
            gameMechanic.checkNewWords(wordsToAdd);
            gameMechanic.addNewWordsToList();
        }
        else if(gameStartScreenUserNumber == 1) {

        }









