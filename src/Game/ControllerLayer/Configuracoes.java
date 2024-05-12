package Game.ControllerLayer;

import Game.ModelLayer.MovementStrategy.MovementStrategy;
import Game.ViewLayer.UI;


/**
 * Classe que armazena as configurações do jogo, incluindo detalhes sobre o ambiente de jogo,
 * opções de jogo e preferências de interface.
 * 
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 
 */

public class Configuracoes {
    private int largura, altura, tamanhoCabeca, pontuacaoComida, numeroObstaculos, anguloRotacao;
    private double tamanhoComida;
    private String tipoComida;
    private boolean obstaculosDinamicos;
    private MovementStrategy movementStrategy;
    private UI ui;


    /**
     * Construtor da classe Configuracoes, inicializa todas as configurações necessárias para o jogo.
     *
     * @param largura Largura da arena de jogo.
     * @param altura Altura da arena de jogo.
     * @param tamanhoCabeca Tamanho da cabeça da cobra em unidades de jogo.
     * @param tipoComida Tipo de comida disponível no jogo (ex: "quadrado" ou "circulo").
     * @param tamanhoComida Tamanho físico da comida na arena.
     * @param pontuacaoComida Pontos concedidos por comer a comida.
     * @param numeroObstaculos Número de obstáculos na arena.
     * @param obstaculosDinamicos Se os obstáculos são dinâmicos e podem mover ou rotacionar.
     * @param anguloRotacao Ângulo de rotação para os obstáculos dinâmicos.
     * @param movementStrategy Estratégia de movimento da cobra (manual ou automático).
     * @param ui Interface de usuário para interação visual com o jogo.
     */

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
