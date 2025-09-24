package hangman;

import java.util.Random;

//класс для генерации рандомного слова
public class WordDictionary {
    private static final String[] WORDS = {
      "ОБУЧЕНИЕ", "JAVA", "КОМПИЛЯТОР", "ИНТЕРПРЕТАТОР", "НОУТБУК",
            "ОПЫТ", "РАЗРАБОТКА", "ПОБЕДА", "БАЙТКОД", "АССЕМБЛЕР"
    };
    private static final Random random = new Random();

    public static String getRandomWord(){
        return WORDS[random.nextInt(WORDS.length)];
    };
}
