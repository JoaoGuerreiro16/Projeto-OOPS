package Game.ModelLayer.MovementStrategy;

import Game.ModelLayer.Direcao;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Snake;

/**
 * Classe que implementa a estratégia de movimento manual para a cobra no jogo.
 * Permite que a direção do movimento da cobra seja controlada pelo usuário através de comandos.
 * @author Joao Guerreiro & Tomas Luz
 */

public class MovimentoManual implements MovementStrategy {

    @Override
    public void movimentoSnake(Snake snake) {
        Quadrado cabecaAtual = snake.getSnake().getFirst();
        Ponto centroAtual = cabecaAtual.calcularCentro(); 
        double dx = 0, dy = 0;
        switch (snake.getDirecaoAtual()) {
            case UP:    dy = -cabecaAtual.getLado(); break;
            case DOWN:  dy = cabecaAtual.getLado(); break;
            case LEFT:  dx = -cabecaAtual.getLado(); break;
            case RIGHT: dx = cabecaAtual.getLado(); break;
        }

        double novoCentroideX = centroAtual.getX() + dx;
        double novoCentroideY = centroAtual.getY() + dy;
        Quadrado novaCabeca = cabecaAtual.translacaoCentroide(novoCentroideX, novoCentroideY);
        snake.getSnake().addFirst(novaCabeca); 
        snake.getSnake().removeLast(); 
    }

    public void mudarDirecao(Snake snake, Direcao novaDirecao) {
        if (eMudancaValida(snake.getDirecaoAtual(), novaDirecao)) {
            snake.setDirecaoAtual(novaDirecao);
            
        }
    }

    private boolean eMudancaValida(Direcao direcaoAtual, Direcao novaDirecao) {
        return !((direcaoAtual == Direcao.UP && novaDirecao == Direcao.DOWN) ||
                (direcaoAtual == Direcao.DOWN && novaDirecao == Direcao.UP) ||
                (direcaoAtual == Direcao.LEFT && novaDirecao == Direcao.RIGHT) ||
                (direcaoAtual == Direcao.RIGHT && novaDirecao == Direcao.LEFT));
    }
}

