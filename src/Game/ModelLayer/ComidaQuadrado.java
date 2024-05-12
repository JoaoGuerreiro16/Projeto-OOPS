package Game.ModelLayer;

import java.util.ArrayList;

/**
 * Classe ComidaQuadrado que representa um tipo específico de comida no formato de quadrado.
 * Estende a classe abstrata Comida, fornecendo implementações específicas para comidas quadradas.
 *
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */


public class ComidaQuadrado extends Comida{
   private Quadrado quadrado;

   /**
     * Construtor para criar uma instância de ComidaQuadrado.
     *
     * @param quadrado O quadrado que define a forma e a posição da comida.
     * @param pontuacao A pontuação atribuída a esta comida quando consumida.
     */

    public ComidaQuadrado(Quadrado quadrado, int pontuacao)
    {
        super(quadrado.calcularCentro(), pontuacao);
        this.quadrado = quadrado;

    }

    public Quadrado getQuadrado() {
        return quadrado;
    }

    public void setQuadrado(Quadrado quadrado) {
        this.quadrado = quadrado;
    }


    /**
     * Verifica se a comida foi consumida pela cobra.
     *
     * @param snake A cobra do jogo.
     * @return true se a cobra consumir a comida, false caso contrário.
     */

    @Override
    public boolean isConsumed(Snake snake) {


        return (snake.containsQuadrado(this));
    }

    /**
     * Verifica se a comida intercepta um dado polígono.
     *
     * @param poligono O polígono a ser verificado para intercepção.
     * @return true se houver intercepção, false caso contrário.
     */

    @Override
    public boolean interceta(Poligono poligono){

        return this.quadrado.intercetaPoligono(poligono);

    }


    /**
     * Verifica se um dado ponto está contido no quadrado da comida.
     *
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver dentro do quadrado, false caso contrário.
     */
    
    @Override
    public boolean containsPonto(Ponto ponto)
    {
        return quadrado.containsPonto(ponto);
    }

}
