import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static Scanner scanner = new Scanner(System.in, "Cp866");

    public static void main(String[] args) {
        playGame();
        scanner.close();
    }

    /**
     * Метод проверяет, является ли введеный символ целым числом
     * 
     * @param msg      - сообщаение для пользователя
     * @param minValue - нижняя граница числа
     * @param maxValue - верхняя граница числа
     * @return введеное число
     */
    public static int intValidation(String msg, int minValue, int maxValue) {
        int numberValidate;
        System.out.println(msg);
        while (!scanner.hasNextInt() || (numberValidate = scanner.nextInt()) > maxValue || numberValidate < minValue) {
            scanner.nextLine();
            System.out.println("Введены неверные данные, попробуйте еще");
        }
        scanner.nextLine();
        return numberValidate;
    }

    /**
     * Метод проверяет длину введеной последовательности
     * 
     * @param baseLen - требуемая длина последовательности
     * @param str     - проверяемая строка
     * @return - строку
     */
    public static String lengthValidation(int baseLen, String str) {
        // String str;
        while (str.length() != baseLen) {
            System.out.println("Длинна введенной последовательности должна быть " + baseLen);
            str = scanner.nextLine();
        }
        return str;
    }

    /**
     * Метод выводит главное меню
     */
    public static void playGame() {
        AbstractGame nGame = null;
        boolean end = false;
        do {
            int menuPoint = getMainMenu();
            switch (menuPoint) {
                case 1:
                    nGame = new NumberGame();
                    startGame(nGame);
                    break;
                case 2:
                    nGame = new RuGame();
                    startGame(nGame);
                    break;
                case 3:
                    nGame = new EngGame();
                    startGame(nGame);
                    break;
                case 0:
                    end = true;
            }
        } while (!end);
    }

    public static int getMainMenu() {
        int minValue = 0;
        int maxValue = 3;
        String menu = "Выберите действие:\n" +
                "1 - Играть в числа\n" +
                "2 - Играть в буквы русского алфавита\n" +
                "3 - Играть в буквы английского алфавита\n" +
                "0 - Выход из программы";
        return intValidation(menu, minValue, maxValue);
    }

    public static int getUniqueMenu() {
        int minValue = 1;
        int maxValue = 2;
        String menu = "Выберите действие:\n" +
                "1 - Генерировать последовательность из уникальных символов\n" +
                "2 - Генерировать последовательность c повторяющимися символами";
        return intValidation(menu, minValue, maxValue);
    }

    /**
     * Метод запускает игру
     * 
     * @param nGame - тип игры
     */
    public static void startGame(AbstractGame nGame) {
        int wordLen = intValidation("Введите длину слова (до 100): ", 1, 100);
        int tryNum = intValidation("Введите количество попыток (до 100): ", 1, 100);
        Unique uniqueVal = Unique.UNIQUE_VALUE;
        switch (getUniqueMenu()) {
            case 1:
                uniqueVal = Unique.UNIQUE_VALUE;
                break;
            case 2:
                uniqueVal = Unique.NON_UNIQUE_VALUE;
                break;
        }
        System.out.println("Если захотите начать эту игру заново напишите restart");
        nGame.start(wordLen, tryNum, uniqueVal);
        while (nGame.getGameStatus().equals(GameStatus.START)) {
            System.out.println("Ваш ход: ");
            String value = scanner.nextLine();
            if (value.equals("restart")) {
                System.out.println("Перезапуск");
                nGame.restart(wordLen, tryNum, uniqueVal);
            } else {
                value = lengthValidation(wordLen, value);
                Answer answer = nGame.inputValue(value.toLowerCase());
                System.out.println(answer);
            }
        }
        System.out.println(nGame.getGameStatus().getDescription());
        System.out.println("Было загадано: " + nGame.getWord());
        System.out.println("\nХотите посмотреть историю игры? (y/n)");
        if (scanner.nextLine().equals("y")) {
            System.out.println(nGame.getLogList().stream().collect(Collectors.joining("\n")));
        }

    }

}