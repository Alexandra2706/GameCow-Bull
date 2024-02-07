public enum GameStatus {
    INIT, START, RESTART, WIN("Вы выиграли"), END("Вы проиграли");

    private String description;

    private GameStatus(String description) {
        this.description = description;
    }

    private GameStatus() {
    }

    public String getDescription() {
        return description;
    }

}
