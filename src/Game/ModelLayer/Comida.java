package Game.ModelLayer;

/**
 * Classe abstrata Comida que serve como base para diferentes tipos de comidas no jogo.
 * Define os atributos e métodos essenciais que todas as comidas devem ter, permitindo diferentes comportamentos
 * dependendo do tipo de comida implementado.
 *
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */

public abstract class Comida {

    protected Ponto pontoCentro;
    protected int pontuacao;

    /**
     * Construtor para criar uma instância de comida com um ponto específico e valor de pontuação.
     * 
     * @param ponto O ponto central onde a comida será posicionada.
     * @param pontuacao A pontuação que esta comida oferece ao ser consumida.
     * @throws IllegalArgumentException Se o ponto fornecido for null.
     */

    public Comida(Ponto ponto, int pontuacao) {

        if (ponto == null) {
            throw new IllegalArgumentException("Ponto não pode ser null.");
        }
        this.pontoCentro = ponto;
        this.pontuacao = pontuacao;
    }

    public void setPontoCentro(Ponto pontoCentro) {
        this.pontoCentro = pontoCentro;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Ponto getPontoCentro() {
        return pontoCentro;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Método abstrato para verificar se um dado ponto está contido na comida.
     * A implementação específica depende do tipo de comida.
     *
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver contido na comida, false caso contrário.
     */

    public abstract boolean containsPonto(Ponto ponto);
    
    /**
     * Método abstrato para determinar se a comida foi consumida pela cobra.
     * Cada tipo de comida implementa sua própria lógica para verificar a consumição.
     *
     * @param snake A cobra do jogo para verificar se consumiu a comida.
     * @return true se a comida foi consumida pela cobra, false caso contrário.
     */
    
    public abstract boolean isConsumed(Snake snake);


    /**
     * Método abstrato para verificar se a comida intercepta um polígono, geralmente usado para detectar colisões.
     *
     * @param poligono O polígono com o qual a intercepção será verificada.
     * @return true se houver intercepção, false caso contrário.
     */
    public abstract boolean interceta(Poligono poligono);

}

