package hangman;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class HangManGame {

    //константа для числа жизней
    private static final int MAX_LIVES = 6;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String currentWord = WordDictionary.getRandomWord();
        Set<Character> guessedLetters = new HashSet<>();
        System.out.println("Угадайте слово из " + currentWord.length() + "букв");
        System.out.println("У вас " + MAX_LIVES + " жизней");
        int lives = MAX_LIVES;

        while(lives > 0){

            System.out.println("Введите букву: ");
            String curLetter = scanner.nextLine().toUpperCase();
            if (curLetter.isEmpty() || !Character.isLetter(curLetter.charAt(0))) {
                System.out.println("Пожалуйста, введите одну букву!");
                continue;
            }
            char letter = curLetter.charAt(0);
            if(guessedLetters.contains(letter)){
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }
            guessedLetters.add(letter);
            if(currentWord.indexOf(letter) >= 0){
                System.out.println("Правильно, буква " + letter + " есть в слове!");
                if(isFinished(currentWord, guessedLetters)){
                    System.out.println("Поздравляем, вы угадали слово!");
                    displayWordProgress(currentWord, guessedLetters);
                    break;
                }
            }
            else {
                System.out.println("Неверно, буквы " + letter + " нет в слове!");
                lives--;
                String correctWord = getCorrectLivesEnd(lives);
                System.out.println("Осталось: " + lives + " " + correctWord);
            }

            if (lives == 0) {
                displayHangman(lives);
                System.out.println("Игра окончена! Вы проиграли.");
                System.out.println("Загаданное слово было: " + currentWord);
                break;
            }

            displayWordProgress(currentWord, guessedLetters);
            displayHangman(lives);
        }
        scanner.close();
    }

    private static void displayWordProgress(String currentWord, Set<Character> guessedLetters){
        System.out.println("Слово: ");
        for(char c: currentWord.toCharArray()) {
            if(guessedLetters.contains(c)){
                System.out.print(c + " ");
            }
            else{
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    public static boolean isFinished(String currentWord, Set<Character> guessedLetters){
        for(char c: currentWord.toCharArray()){
            if(!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static String getCorrectLivesEnd(int lives){
        if(11 <= lives && lives <= 20){
            return "жизней";
        }
        else if(lives % 10 == 1){
            return "жизнь";
        } else if (lives % 10 == 2 || lives % 10 == 3 || lives % 10 == 4) {
            return "жизни";
        }
        else{
            return "жизней";
        }
    }

    private static void displayHangman(int lives) {
        String[] hangmanStages = {
                """
              -----
              |   |
              |   O
              |  /|\\
              |  / \\
              |
            """,
                """
              -----
              |   |
              |   O
              |  /|\\
              |  / 
              |
            """,
                """
              -----
              |   |
              |   O
              |  /|\\
              |  
              |
            """,
                """
              -----
              |   |
              |   O
              |  /|
              |  
              |
            """,
                """
              -----
              |   |
              |   O
              |   |
              |  
              |
            """,
                """
              -----
              |   |
              |   O
              |  
              |  
              |
            """,
                """
              -----
              |   |
              |  
              |  
              |  
              |
            """
        };

        System.out.println(hangmanStages[lives]);
    }

}
