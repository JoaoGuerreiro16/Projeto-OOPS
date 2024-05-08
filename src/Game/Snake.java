package Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Snake {

     private LinkedList<Quadrado> snake;
     private Direcao direcaoAtual;

     private Random rand = new Random();

     public Snake(Quadrado cabeca)
     {
         this.snake = new LinkedList<>();
         this.snake.add(cabeca);
         this.direcaoAtual = Direcao.values()[rand.nextInt(Direcao.values().length)];

     }

    public LinkedList<Quadrado> getSnake() {
        return snake;
    }

    public void setSnake(LinkedList<Quadrado> snake) {
        this.snake = snake;
    }

    public Direcao getDirecaoAtual() {
        return direcaoAtual;
    }

    public void setDirecaoAtual(Direcao direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }

    @Override
    public String toString() {
        return "Snake: " + getSnake().toString();
    }

    public void mudaDirecao(Direcao novaDirecao) {

        if (this.direcaoAtual == Direcao.UP && novaDirecao == Direcao.DOWN) return;
        if (this.direcaoAtual == Direcao.DOWN && novaDirecao == Direcao.UP) return;
        if (this.direcaoAtual == Direcao.LEFT && novaDirecao == Direcao.RIGHT) return;
        if (this.direcaoAtual == Direcao.RIGHT && novaDirecao == Direcao.LEFT) return;

        this.direcaoAtual = novaDirecao;
    }


    public void movimentoSnake()
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
     public boolean intercetaSnake(){

             Quadrado cabeca = snake.getFirst();
             for (int i = 1; i < snake.size(); i++) {
                 if (cabeca.intercetaPoligono(snake.get(i))) {
                     return true;
                 }
             }
             return false;
         }
     
    public void mudarDirecaoSeValido(Direcao novaDirecao) {

        if (Math.abs(novaDirecao.ordinal() - direcaoAtual.ordinal()) % 2 == 1) {
            this.direcaoAtual = novaDirecao;
        }
    }

    public boolean containsQuadrado(ComidaQuadrado comida){

         Quadrado cabeca = snake.getFirst();

        return cabeca.containsQuadrado(comida.getQuadrado());
    }

    public boolean containsCirculo(ComidaCirculo comida){

         Quadrado cabeca = snake.getFirst();

        return cabeca.containsCircle(comida.getCirculo());
    }

}
