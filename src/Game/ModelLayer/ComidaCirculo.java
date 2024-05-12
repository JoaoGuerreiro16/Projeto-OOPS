package Game.ModelLayer;

  /**
 * Classe ComidaCirculo que representa um tipo específico de comida no formato de círculo.
 * Estende a classe abstrata Comida, fornecendo implementações específicas para comidas circulares.
 *
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */


public class ComidaCirculo extends Comida{
    private Circulo circulo;

    /**
     * Construtor para criar uma instância de ComidaCirculo.
     *
     * @param circulo O círculo que define a forma e a posição da comida.
     * @param pontuacao A pontuação atribuída a esta comida quando consumida.
     */

    public ComidaCirculo(Circulo circulo, int pontuacao)
    {
        super(circulo.getCentro(), pontuacao);
        this.circulo = circulo;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    /**
     * Verifica se a comida foi consumida pela cobra.
     *
     * @param snake A cobra do jogo.
     * @return true se a cobra consumir a comida, false caso contrário.
     */


    @Override
    public boolean isConsumed(Snake snake) {
        return snake.containsCirculo(this);
    }

    /**
     * Verifica se a comida intercepta um dado polígono.
     * Utiliza um quadrado protetor para verificar a intercepção.
     *
     * @param poligono O polígono a ser verificado para intercepção.
     * @return true se houver intercepção, false caso contrário.
     */

    @Override
    public boolean interceta(Poligono poligono){

       Quadrado quadrado = this.circulo.criaQuadradoProtetor(circulo);
       return quadrado.intercetaPoligono(poligono);

    }

    /**
     * Verifica se um dado ponto está contido no círculo da comida.
     *
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver dentro do círculo, false caso contrário.
     */

    @Override
    public boolean containsPonto(Ponto ponto)
    {
        return circulo.containsPonto(ponto);
    }

}
