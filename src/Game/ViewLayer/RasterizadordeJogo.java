package Game.ViewLayer;

import java.util.List;

import Game.ModelLayer.*;


/**
 * Classe responsável por rasterizar o estado do jogo em uma matriz de células, onde cada célula
 * representa um estado visual do jogo, como parte da cobra, comida ou obstáculos.
 * Esta classe é fundamental para converter o modelo lógico do jogo em uma representação visual que pode ser exibida.
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */

public class RasterizadordeJogo {
    private Celula[][] grid;
    private int altura;
    public Celula[][] getGrid() {
        return grid;
    }


    public int getAltura() {
        return altura;
    }


    public int getLargura() {
        return largura;
    }

    private int largura;


    /**
     * Construtor da classe RasterizadordeJogo.
     * Inicializa a matriz de células com a altura e largura especificadas.
     * @param altura Altura da grade.
     * @param largura Largura da grade.
     */
    public RasterizadordeJogo(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
        this.grid = new Celula[altura][largura];

    }


    /**
     * Preenche a grade de exibição baseada nos estados do jogo, como a posição da cobra,
     * localização da comida e posicionamento dos obstáculos.
     * @param snake A cobra do jogo.
     * @param comida A comida no jogo.
     * @param obstaculos Lista de obstáculos presentes no jogo.
     */

    public void fillDisplay(Snake snake, Comida comida, List<Obstaculo> obstaculos) {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                grid[i][j] = new Celula(i, j);
                grid[i][j].setEstado(EstadoCelula.EMPTY);
            }
        }


        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                Ponto pontoAtual = new Ponto(j, i);

                for (Quadrado parte : snake.getSnake()) {
                    if (parte.containsPonto(pontoAtual)) 
                    {
                        if (snake.getSnake().getFirst().containsPonto(pontoAtual)) {
                            grid[i][j].setEstado(EstadoCelula.HEAD);
                        } else {
                            grid[i][j].setEstado(EstadoCelula.TAIL);
                        }

                }
                }

                if (comida.containsPonto(pontoAtual)) {
                    grid[i][j].setEstado(EstadoCelula.FOOD);
                } else {
                    for (Obstaculo obstaculo : obstaculos) {
                        if (obstaculo.getPoligono().containsPonto(pontoAtual)) {
                            grid[i][j].setEstado(EstadoCelula.OBSTACLE);
                            break;
                        }
                    }
                }
            }
        }
    }
}
