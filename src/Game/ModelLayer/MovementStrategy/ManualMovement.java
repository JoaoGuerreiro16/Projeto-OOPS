package Game.ModelLayer.MovementStrategy;

import Game.ModelLayer.Direcao;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Snake;

public class ManualMovement implements MovementStrategy {
    private Direcao direcao;


    @Override
    public void movimentoSnake(Snake snake) {
        if (isChangeValid(snake.getDirecaoAtual(), this.direcao)) {
            snake.setDirecaoAtual(this.direcao);
            
            Quadrado cabecaAtual = snake.getSnake().getFirst();
            double dx = 0, dy = 0;
            switch (snake.getDirecaoAtual()) {
                case UP:    dy = -cabecaAtual.getLado(); break;
                case DOWN:  dy = cabecaAtual.getLado(); break;
                case LEFT:  dx = -cabecaAtual.getLado(); break;
                case RIGHT: dx = cabecaAtual.getLado(); break;
            }

            Quadrado novaCabeca = cabecaAtual.translacao(dx, dy);
            snake.getSnake().addFirst(novaCabeca);
            snake.getSnake().removeLast();
        }
    }

    private boolean isChangeValid(Direcao current, Direcao newDirection) {
        return !((current == Direcao.UP && newDirection == Direcao.DOWN) ||
                 (current == Direcao.DOWN && newDirection == Direcao.UP) ||
                 (current == Direcao.LEFT && newDirection == Direcao.RIGHT) ||
                 (current == Direcao.RIGHT && newDirection == Direcao.LEFT));
    }
}

