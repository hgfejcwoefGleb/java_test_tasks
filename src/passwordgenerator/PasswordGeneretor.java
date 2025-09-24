package passwordgenerator;

import java.util.Scanner;
import java.util.Random;

public class PasswordGeneretor {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите длину пароля (8-12 символов): ");

        int length = getPasswordLength(scanner);
        String password = generateSecurePassword(length);

        System.out.println("\nВаш безопасный пароль:");
        System.out.println("Пароль: " + password);
        System.out.println("Длина: " + password.length() + " символов");
        scanner.close();
    }

    private static int getPasswordLength(Scanner scanner) {
        int length;
        while (true) {
            System.out.print("Введите число от 8 до 12: ");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
                if (length >= 8 && length <= 12) {
                    break;
                } else {
                    System.out.println("Длина должна быть от 8 до 12 символов!");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число!");
                scanner.next();
            }
        }
        return length;
    }

    private static String generateSecurePassword(int length) {
        StringBuilder password = new StringBuilder();
        String allCharacters = UPPERCASE + LOWERCASE + NUMBERS + SPECIALS;

        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(NUMBERS));
        password.append(getRandomChar(SPECIALS));

        for (int i = 4; i < length; i++) {
            password.append(getRandomChar(allCharacters));
        }

        return shuffleString(password.toString());
    }

    private static char getRandomChar(String characterSet) {
        return characterSet.charAt(random.nextInt(characterSet.length()));
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
