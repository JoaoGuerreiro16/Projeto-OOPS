package Game;

import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {

     private LinkedList<Quadrado> snake;
     private Direcao direcaoAtual;

     public Snake(Quadrado cabeca)
     {
         this.snake = new LinkedList<>();
         this.snake.add(cabeca);
         this.direcaoAtual = Direcao.RIGHT;

     }

    public LinkedList<Quadrado> getSnake() {
        return snake;
    }

    @Override
    public String toString() {
        return "Snake: " + getSnake().toString();
    }


    public void movimentoSnake(int x, int y)
    {

        Quadrado cabecaAtual = snake.getFirst();

        double dx = 0, dy = 0;
        switch (direcaoAtual) {
            case UP:    dy = -cabecaAtual.getLado();
            break;
            case DOWN:  dy = cabecaAtual.getLado();
            break;
            case LEFT:  dx = -cabecaAtual.getLado();
            break;
            case RIGHT: dx = cabecaAtual.getLado();
            break;
        }

        Quadrado novaCabeca = cabecaAtual.translacao(dx,dy);
        snake.addFirst(novaCabeca);
        snake.removeLast();
    }

     public void cresceSnake()
     {
         Quadrado caudaAtual = snake.getLast();

         double dx = 0, dy = 0;
         switch (direcaoAtual) {
             case UP:    dy = caudaAtual.getLado();
             break;
             case DOWN:  dy = -caudaAtual.getLado();
             break;
             case LEFT:  dx = caudaAtual.getLado();
             break;
             case RIGHT: dx = -caudaAtual.getLado();
             break;
         }


         Quadrado novaCauda = caudaAtual.translacao(dx, dy);
         snake.addLast(novaCauda);
     }

     /** nao sei se é na board ou na snake
     public Boolean intercetaParede()
     {
         Quadrado cabeca = snake.getFirst();
         Ponto centro = cabeca.calcularCentro();
         double metadeLado = cabeca.getLado() / 2;
         if(centro.getX() - metadeLado < 0 || centro.getX() + metadeLado)
     }
**/

    public void evitarMudarDirecao180(Direcao novaDirecao) {

        if (Math.abs(novaDirecao.ordinal() - direcaoAtual.ordinal()) % 2 == 1) {
            this.direcaoAtual = novaDirecao;
        }
    }

}
