import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class AbstractGame implements Game {
    private String word;
    private GameStatus status = GameStatus.INIT;
    private Integer maxTry;
    private Logging logging;

    /**
     * Генерирует заданную последовательность символов
     * 
     * @param size          - длина последовательности
     * @param uniqueSymbols - условие для генерации (уникальные или повторяющиеся
     *                      символы)
     * @return строку
     */
    public String generateWord(Integer size, Unique uniqueSymbols) {
        List<String> charList = generateCharList();
        Random random = new Random();
        String result = "";
        if (uniqueSymbols.equals(Unique.UNIQUE_VALUE)) {
            Set<String> word = new HashSet<>();
            while (word.size() < size) {
                word.add(charList.get(random.nextInt(charList.size())));
            }
            result = String.join("", word);
        } else {
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < size; i++) {
                word.append(charList.get(random.nextInt(charList.size())));
            }
            result = word.toString();
        }
        System.out.println("Последовательность загадана");
        logging.addResult("Загаданная последовательность: " + result);
        return result;
    }

    /**
     * Метод генерации символов, их которых потом составляется строка
     * метод имплементируется в классах, которые реализуют конкретный тип игры
     * 
     * @return список символов
     */
    public abstract List<String> generateCharList();

    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    @Override
    public Answer inputValue(String value) {
        if (!getGameStatus().equals(GameStatus.START))
            throw new RuntimeException("Игра не в активном состоянии");
        StringBuilder tempWord = new StringBuilder(word);
        Integer countBull = 0;
        Integer countCow = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == value.charAt(i)) {
                countBull++;
            }
            if (word.contains(String.valueOf(value.charAt(i)))) {
                int index = tempWord.indexOf(String.valueOf(value.charAt(i)));
                if (index != -1) {
                    tempWord.deleteCharAt(index);
                    countCow++;
                }
            }
        }
        --maxTry;
        if (countBull == word.length()) {
            status = GameStatus.WIN;
        } else if (maxTry == 0) {
            status = GameStatus.END;
        }
        Answer result = new Answer(maxTry, countBull, countCow);
        logging.addResult("Введенное значение: " + value + "\t" + result.toString());
        return result;
    }

    @Override
    public void start(Integer sizeWord, Integer maxTry, Unique uniqueVal) {
        if (!status.equals(GameStatus.RESTART)) {
            logging = new Logging();
            word = generateWord(sizeWord, uniqueVal);
        }
        status = GameStatus.START;
        this.maxTry = maxTry;
    }

    @Override
    public void restart(Integer sizeWord, Integer maxTry, Unique uniqueVal) {
        status = GameStatus.RESTART;
        logging.addResult("Перезапуск игры");
        start(sizeWord, maxTry, uniqueVal);
    }

    public String getWord() {
        return word;
    }

    public List<String> getLogList() {
        return logging.getLogList();
    }
}
