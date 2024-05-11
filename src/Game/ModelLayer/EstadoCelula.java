package Game.ModelLayer;

public enum EstadoCelula {
    EMPTY("."),
    FOOD("F"),
    HEAD("H"),
    TAIL("T"),
    OBSTACLE("O");

    private String symbol;

    EstadoCelula(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return this.symbol;
    }
}
