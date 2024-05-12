package Game.ModelLayer;


/**
 * Enumeração EstadoCelula representa os possíveis estados de uma célula no grid do jogo.
 *
 * Cada estado é associado a um símbolo específico que é usado para a visualização do jogo na interface.
 * Esta enumeração é crucial para controlar e exibir o estado atual de cada célula no grid, influenciando a lógica de jogo,
 * como a movimentação da cobra e a interação com comida ou obstáculos.
 *
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */

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
