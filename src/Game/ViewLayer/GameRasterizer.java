package Game.ViewLayer;

import java.util.List;

import Game.ModelLayer.*;

public class GameRasterizer {
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

    public GameRasterizer(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
        this.grid = new Celula[altura][largura];

    }


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
