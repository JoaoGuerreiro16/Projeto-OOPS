package Game.ModelLayer;

public class Pontuacao {


    private static Pontuacao instance;
    final int MAX_VALUE = Integer.MAX_VALUE;
    private int pontuacao;

    private Pontuacao(){

        this.pontuacao = 0;
    }

    public static Pontuacao getInstance(){

        if (instance == null) {
            instance = new Pontuacao();
        }
        return instance;

    }

    public static void resetInstance() {
        instance = null;
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
