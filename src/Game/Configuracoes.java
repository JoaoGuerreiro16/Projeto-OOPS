package Game;

public class Configuracoes {
    int largura, altura, tamanhoCabeça, pontuacaoComida, numeroObstaculos, anguloRotacao;
    double tamanhoComida;
    String tipoComida;
    boolean obstaculosDinamicos;

    public Configuracoes(int largura, int altura, int tamanhoCabeça, String tipoComida, double tamanhoComida, int pontuacaoComida, int numeroObstaculos, boolean obstaculosDinamicos, int anguloRotacao) {
        this.largura = largura;
        this.altura = altura;
        this.tamanhoCabeça = tamanhoCabeça;
        this.tipoComida = tipoComida;
        this.tamanhoComida = tamanhoComida;
        this.pontuacaoComida = pontuacaoComida;
        this.numeroObstaculos = numeroObstaculos;
        this.obstaculosDinamicos = obstaculosDinamicos;
        this.anguloRotacao = anguloRotacao;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getTamanhoCabeça() {
        return tamanhoCabeça;
    }

    public int getPontuacaoComida() {
        return pontuacaoComida;
    }

    public int getNumeroObstaculos() {
        return numeroObstaculos;
    }

    public int getAnguloRotacao() {
        return anguloRotacao;
    }

    public double getTamanhoComida() {
        return tamanhoComida;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public boolean isObstaculosDinamicos() {
        return obstaculosDinamicos;
    }
}
