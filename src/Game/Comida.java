package Game;

public abstract class Comida {

    protected Ponto pontoCentro;
    protected int pontuacao;

    public Comida(Ponto ponto, int pontuacao){

        this.pontoCentro = ponto;
        this.pontuacao = pontuacao;
    }

    public Ponto getPontoCentro() {
        return pontoCentro;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public abstract boolean isConsumed(Snake snake);
}
