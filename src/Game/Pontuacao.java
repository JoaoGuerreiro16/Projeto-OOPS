package Game;

public class Pontuacao {

    final int MAX_VALUE = Integer.MAX_VALUE;
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

    public void pontuacaoMaxima(){

        this.pontuacao = MAX_VALUE;
    }
}
