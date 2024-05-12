package Game.ModelLayer;
import java.util.LinkedList;
import java.util.Random;
import Game.ModelLayer.MovementStrategy.MovementStrategy;
import Game.ModelLayer.MovementStrategy.MovimentoManual;

/**
 * Classe que representa a cobra no jogo.
 * Gerencia o estado da cobra, incluindo sua posição, direção, movimento, e interações com comida ou obstáculos.
 * @author Tomás Luz & Joao Guerreiro
* @version 1.0 05/04/2024
 */

public class Snake {
    private LinkedList<Quadrado> snake;
    private Direcao direcaoAtual;
    private static Snake instance;
    private MovementStrategy movementStrategy;


     /**
     * Construtor privado que inicializa a cobra com uma cabeça e uma estratégia de movimento.
     * 
     * @param cabeca O primeiro quadrado da cobra, representando a cabeça.
     * @param strategy A estratégia de movimento a ser usada pela cobra.
     */
    private Snake(Quadrado cabeca, MovementStrategy strategy) {
        this.snake = new LinkedList<>();
        this.snake.add(cabeca);
        this.movementStrategy = strategy; 
        this.direcaoAtual = Direcao.values()[new Random().nextInt(Direcao.values().length)];
    }


    /**
     * Método estático para obter a instância única da classe Snake.
     * 
     * @param cabeca O primeiro quadrado da cobra, representando a cabeça.
     * @param strategy A estratégia de movimento a ser usada pela cobra.
     * @return A única instância de Snake.
     */
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

    /**
     * Método para mover a cobra de acordo com a estratégia de movimento atual.
     */

    public void movimentoSnake() {
        if (movementStrategy != null) {
            movementStrategy.movimentoSnake(this);
        }
    }

    /**
     * Método para mudar a direção da cobra se a nova direção é válida (não é oposta à atual).
     * 
     * @param novaDirecao A nova direção para a cobra.
     */

    public void mudaDirecao(Direcao novaDirecao) {

        if (movementStrategy instanceof MovimentoManual) {
            ((MovimentoManual) movementStrategy).mudarDirecao(this, novaDirecao);
        }
    }

    /**
     * Método para fazer a cobra crescer adicionando um novo quadrado na última posição.
     */
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
     
     /**
     * Método para verificar se a cobra intercepta a si mesma.
     * 
     * @return true se a cabeça da cobra intersecta qualquer outra parte do seu corpo.
     */
     public boolean intercetaSnake(){

        if (snake.size() <= 4) return false;

        Quadrado cabeca = snake.getFirst();
             for (int i = 3; i < snake.size(); i++) {
                 if (cabeca.intercetaPoligono(snake.get(i)) || cabeca.containsQuadrado(snake.get(i))) {
                     return true;
                 }
             }
             return false;
         }
     
         /**
 * Modifica a direção da cobra apenas se a nova direção for perpendicular à direção atual.
 * Isso impede que a cobra inverta o curso diretamente para trás em si mesma.
 * 
 * @param novaDirecao A nova direção que a cobra deve tentar assumir.
 */
    public void mudarDirecaoSeValido(Direcao novaDirecao) {

        if (Math.abs(novaDirecao.ordinal() - direcaoAtual.ordinal()) % 2 == 1) {
            this.direcaoAtual = novaDirecao;
        }
    }

    /**
 * Verifica se qualquer parte da cobra contém o quadrado especificado associado à comida.
 * Este método é usado para determinar se a cobra consumiu a comida do tipo quadrado.
 * 
 * @param comida O objeto ComidaQuadrado que possivelmente está sendo consumido pela cobra.
 * @return true se alguma parte da cobra intercepta o quadrado da comida, caso contrário, false.
 */
    public boolean containsQuadrado(ComidaQuadrado comida){

        boolean result = false;
         for (Quadrado partes : snake) {
           if (partes.containsQuadrado(comida.getQuadrado()))
           return true;
         }

         return result;

    }

    /**
 * Verifica se qualquer parte da cobra contém o círculo especificado associado à comida.
 * Este método é usado para determinar se a cobra consumiu a comida do tipo círculo.
 * 
 * @param comida O objeto ComidaCirculo que possivelmente está sendo consumido pela cobra.
 * @return true se alguma parte da cobra intercepta o círculo da comida, caso contrário, false.
 */
    public boolean containsCirculo(ComidaCirculo comida){
        boolean result = false;
        for (Quadrado partes : snake) {
            if (partes.containsCircle(comida.getCirculo()))
            return true;
          };

        return result;
    }

}
