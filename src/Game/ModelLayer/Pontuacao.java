package Game.ModelLayer;

/**
 * Classe responsável por gerenciar a pontuação do jogo.
 * Utiliza o padrão Singleton para assegurar uma única instância global.
 */

public class Pontuacao {


    private static Pontuacao instance;
    final int MAX_VALUE = Integer.MAX_VALUE;
    private int pontuacao;

    /**
     * Construtor privado para evitar instanciação externa e garantir o padrão Singleton.
     */

    private Pontuacao(){

        this.pontuacao = 0;
    }


    /**
     * Método estático para obter a instância única da classe Pontuacao.
     * Se a instância não existir, uma nova é criada.
     *
     * @return A única instância de Pontuacao.
     */
    
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
