public interface Game {
    /**
     * Метод начала игры
     * 
     * @param sizeWord  - длина загаданного слова
     * @param maxTry    - количество попыток
     * @param uniqueVal - условие для генерации (уникальные или повторяющиеся
     *                  символы)
     */
    void start(Integer sizeWord, Integer maxTry, Unique uniqueVal);

    /**
     * Метод перезапуска игры. Количество попыток сбрасывается
     * 
     * @param sizeWord  - длина загаданного слова
     * @param maxTry    - количество попыток
     * @param uniqueVal - условие для генерации (уникальные или повторяющиеся
     *                  символы)
     */
    void restart(Integer sizeWord, Integer maxTry, Unique uniqueVal);

    /**
     * Метод проверки строки пользователя
     * 
     * @param value - введеное пользоватетелем значение
     * @return - ответ (сколько быков и коров). Класс Answer
     */
    Answer inputValue(String value);

    /**
     * Метод получения текущего статуса игры
     * 
     * @return статус
     */
    GameStatus getGameStatus();
}
