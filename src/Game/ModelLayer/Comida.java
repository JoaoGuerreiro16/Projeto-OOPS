package Game.ModelLayer;

public abstract class Comida {

    protected Ponto pontoCentro;
    protected int pontuacao;

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

    public abstract boolean containsPonto(Ponto ponto);
    
    
    public abstract boolean isConsumed(Snake snake);

    public abstract boolean interceta(Poligono poligono);

}

