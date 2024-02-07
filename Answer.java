public class Answer {
    private Integer countTry;
    private Integer countBull;
    private Integer countCow;

    public Answer(Integer countTry, Integer countBull, Integer countCow) {
        this.countTry = countTry;
        this.countBull = countBull;
        this.countCow = countCow;
    }

    @Override
    public String toString() {
        String msgCow;
        String msgBull;
        String msgTry;
        if (countCow % 10 == 1) {
            msgCow = " корова, ";
        } else if (countCow % 10 == 2 || countCow % 10 == 3 || countCow % 10 == 4) {
            msgCow = " коровы, ";
        } else {
            msgCow = " коров, ";
        }

        if (countBull % 10 == 1) {
            msgBull = " бык, осталось: ";
        } else if (countBull % 10 == 2 || countBull % 10 == 3 || countBull % 10 == 4) {
            msgBull = " быка, осталось: ";
        } else {
            msgBull = " быков, осталось: ";
        }

        if (countTry % 10 == 1) {
            msgTry = " попытка";
        } else if (countTry % 10 == 2 || countTry % 10 == 3 || countTry % 10 == 4) {
            msgTry = " попытки";
        } else {
            msgTry = " попыток";
        }

        return "Вывод: " + countCow + msgCow + countBull + msgBull + countTry + msgTry;
    }

}
