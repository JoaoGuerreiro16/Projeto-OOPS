package Game.ModelLayer;


/**
 * Classe que representa um círculo no modelo do jogo.
 * Um círculo é definido por um ponto central e um raio.
 *
 * @author Tomás Luz e Joao Guerreiro
 * @version 1.0 
 */

public class Circulo {
    private double raio;
    private Ponto centro;

    /**
     * Construtor que cria um círculo com um centro e raio especificados.
     *
     * @param centro O ponto central do círculo.
     * @param raio O raio do círculo.
     */

    public Circulo(Ponto centro, double raio) {

        setRaio(raio);
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio <= 0) {
           throw new IllegalArgumentException("O raio tem de ser maior que 0");
        }
        this.raio = raio;
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return "Circulo de centro: " + getCentro() + " com raio: " + getRaio();
    }

    public static Circulo toInt(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Formato de entrada inválido. Use 'raio x_centro y_centro'.");
        }

        int raio = Integer.parseInt(parts[0]);
        int centroX = Integer.parseInt(parts[1]);
        int centroY = Integer.parseInt(parts[2]);
        Ponto centro = new Ponto(centroX, centroY);

        return new Circulo(centro, raio);
    }

    /**
     * Realiza a translação do centro do círculo para um novo ponto.
     *
     * @param x Coordenada x do novo centro.
     * @param y Coordenada y do novo centro.
     * @return Um novo círculo com o centro transladado.
     */

    public Circulo translacaoCentroide(int x, int y)
    {
        Ponto novoCentro = new Ponto(x,y);

        return new Circulo(novoCentro,raio);
    }

    /**
     * Cria um quadrado protetor ao redor do círculo, usando o raio do círculo para definir o lado do quadrado.
     *
     * @param circulo O círculo para o qual o quadrado protetor será criado.
     * @return Um novo quadrado que cobre completamente o círculo.
     */

    public Quadrado criaQuadradoProtetor(Circulo circulo){

        return new Quadrado(this.centro, this.raio * 2);
    }

    /**
     * Verifica se um ponto está contido dentro do círculo.
     *
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto estiver dentro do círculo, false caso contrário.
     */

    public boolean containsPonto(Ponto ponto) {
        double distanceSquared = Math.pow(ponto.getX() - centro.getX(), 2) + Math.pow(ponto.getY() - centro.getY(), 2);
        return distanceSquared <= Math.pow(raio, 2);
    }

}
