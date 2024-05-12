package Game.ModelLayer;
import java.util.LinkedList;
import java.util.Random;
import Game.ModelLayer.MovementStrategy.MovementStrategy;
import Game.ModelLayer.MovementStrategy.MovimentoManual;

public class Snake {

    private LinkedList<Quadrado> snake;
    private Direcao direcaoAtual;
    private static Snake instance;
    private MovementStrategy movementStrategy;

    private Snake(Quadrado cabeca, MovementStrategy strategy) {
        this.snake = new LinkedList<>();
        this.snake.add(cabeca);
        this.movementStrategy = strategy; 
        this.direcaoAtual = Direcao.values()[new Random().nextInt(Direcao.values().length)];
    }

    public static Snake getInstance(Quadrado cabeca, MovementStrategy strategy) {
        if (instance == null) {
            instance = new Snake(cabeca, strategy);
        }
        return instance;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public static void resetInstance() {
        instance = null;
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

    public void movimentoSnake() {
        if (movementStrategy != null) {
            movementStrategy.movimentoSnake(this);
        }
    }

    public void mudaDirecao(Direcao novaDirecao) {

        if (movementStrategy instanceof MovimentoManual) {
            ((MovimentoManual) movementStrategy).mudarDirecao(this, novaDirecao);
        }
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

        if (snake.size() < 4) return false;

        Quadrado cabeca = snake.getFirst();
             for (int i = 3; i < snake.size(); i++) {
                 if (cabeca.intercetaPoligono(snake.get(i)) || cabeca.containsQuadrado(snake.get(i))) {
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

        boolean result = false;
         for (Quadrado partes : snake) {
           if (partes.containsQuadrado(comida.getQuadrado()))
           return true;
         }

         return result;

    }

    public boolean containsCirculo(ComidaCirculo comida){
        boolean result = false;
        for (Quadrado partes : snake) {
            if (partes.containsCircle(comida.getCirculo()))
            return true;
          };

        return result;
    }

}
