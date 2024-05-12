package Game.ModelLayer;

/**
 * Classe que representa uma célula no grid do jogo.
 * Uma célula é um componente do grid que possui um estado que pode variar conforme as regras do jogo,
 * sendo estas estados como vazia, contendo a cabeça ou cauda da snake, comida ou obstáculo.
 *
 * @author Tomás Luz e Joao Guerreiro
 * @version 1.0 
 */

public class Celula {
 private EstadoCelula estado;
 private int linha;
 private int coluna;

 /**
     * Construtor para a classe Celula.
     * Inicializa uma célula com coordenadas de linha e coluna, e com estado padrão vazio.
     *
     * @param linha A linha no grid onde a célula está localizada.
     * @param coluna A coluna no grid onde a célula está localizada.
     */

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.estado = EstadoCelula.EMPTY;

    }

    public EstadoCelula getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelula estado) {
        this.estado = estado;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }


    /**
     * Retorna uma representação textual da célula incluindo sua localização (linha e coluna) e estado.
     *
     * @return Uma string descrevendo a célula incluindo linha, coluna e estado.
     */
    
    @Override
    public String toString() {

        return "Celula (linha: " + linha + ",coluna: " + coluna + ",estado: " + estado +")";
    }
}
