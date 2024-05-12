package Game.ControllerLayer;

import Game.ModelLayer.MovementStrategy.MovementStrategy;
import Game.ViewLayer.UI;

public class Configuracoes {
    private int largura, altura, tamanhoCabeca, pontuacaoComida, numeroObstaculos, anguloRotacao;
    private double tamanhoComida;
    private String tipoComida;
    private boolean obstaculosDinamicos;
    private MovementStrategy movementStrategy;
    private UI ui;

    public Configuracoes(int largura, int altura, int tamanhoCabeca, String tipoComida, double tamanhoComida, int pontuacaoComida, int numeroObstaculos, boolean obstaculosDinamicos, int anguloRotacao,MovementStrategy movementStrategy,UI ui) {
        this.largura = largura;
        this.altura = altura;
        this.tamanhoCabeca = tamanhoCabeca;
        this.tipoComida = tipoComida;
        this.tamanhoComida = tamanhoComida;
        this.pontuacaoComida = pontuacaoComida;
        this.numeroObstaculos = numeroObstaculos;
        this.obstaculosDinamicos = obstaculosDinamicos;
        this.anguloRotacao = anguloRotacao;
        this.movementStrategy = movementStrategy;
        this.ui = ui;
    }

    public UI getUi() {
        return ui;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getTamanhoCabeca() {
        return tamanhoCabeca;
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

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
}
