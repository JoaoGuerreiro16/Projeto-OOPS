package Game;

public class Pontuacao {

    private int pontuacao;

    public Pontuacao(){

        this.pontuacao = 0;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void incrementaPontuacao(int pontos){

        this.pontuacao += pontos;
    }
}
