import java.util.Objects;

/**
 * Classe responsável pela criação de um ponto.
 * Representa um ponto no plano cartesiano com coordenadas x e y.
 * Garante que as coordenadas recebidas estejam ambas no primeiro quadrante.
 *
 * @author João Guerreiro, a81430
 * @version 03/04/2024
 */
public class Ponto {
    private int x;
    private int y;
    private double x_db,y_db;

    /**
     * Construtor para criar um ponto com coordenadas double x e y.
     *
     * @param x_db Coordenada x do ponto
     * @param y_db Coordenada y do ponto
     */

    public Ponto(double x_db,double y_db)
    {
        check(x_db,y_db);
        setX_db(x_db);
        setY_db(y_db);
    }

    /**
     * Construtor para criar um ponto com coordenadas int x e y.
     *
     * @param x Coordenada x do ponto
     * @param y Coordenada y do ponto
     */
    public Ponto(int x, int y) {
        check(x,y);
        setX(x);
        setY(y);
    }
    private void check(double x, double y)
    {
        if(Math.abs(x-y) < Math.pow(10, -9))
            x = y;
    }

    /**
     * Método responsável pelo cálculo da distância entre dois pontos.
     *
     * @param p Ponto com o qual calcular a distância
     * @return Retorna a distância entre os dois pontos
     */
    public double dist(Ponto p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Obtém a coordenada x do ponto.
     *
     * @return Coordenada x do ponto
     */
    public int getX() {
        return x;
    }

    /**
     * Define a coordenada x do ponto.
     * Se a coordenada x ou y for menor que 0, encerra o programa.
     *
     * @param x Nova coordenada x do ponto
     */
    public void setX(int x) {
        if (x < 0 ) {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.x = x;
    }

    /**
     * Obtém a coordenada y do ponto.
     *
     * @return Coordenada y do ponto
     */
    public int getY() {
        return y;
    }

    /**
     * Define a coordenada y do ponto.
     * Se a coordenada x ou y for menor que 0, encerra o programa.
     *
     * @param y Nova coordenada y do ponto
     */
    public void setY(int y) {
        if ( y < 0) {
            System.out.println("Ponto:vi");
            System.exit(0);
        }
        this.y = y;
    }

    /**
     * Obtém a coordenada x do ponto em formato double.
     *
     * @return Coordenada x do ponto em formato double
     */
    public double getX_db() {
        return x_db;
    }

    /**
     * Define a coordenada x do ponto em formato double.
     *
     * @param x_db Nova coordenada x do ponto em formato double
     */
    public void setX_db(double x_db) {
        this.x_db = x_db;
    }

    /**
     * Obtém a coordenada y do ponto em formato double.
     *
     * @return Coordenada y do ponto em formato double
     */
    public double getY_db() {
        return y_db;
    }

    /**
     * Define a coordenada y do ponto em formato double.
     *
     * @param y_db Nova coordenada y do ponto em formato double
     */
    public void setY_db(double y_db) {
        this.y_db = y_db;
    }


    /**
     * Verifica se dois pontos são iguais.
     *
     * @param o Objeto a ser comparado
     * @return true se os pontos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return x == ponto.x && y == ponto.y;
    }

    /**
     * Retorna o código hash do ponto.
     *
     * @return Código hash do ponto
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Retorna uma representação em string do ponto no formato (x, y).
     *
     * @return String representando o ponto
     */
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }

    /**
     * Realiza a translação do ponto.
     *
     * @param x Valor a ser somado à coordenada x do ponto
     * @param y Valor a ser somado à coordenada y do ponto
     * @return Novo ponto após a translação
     */
    public Ponto pointTranslation(int x, int y) {
        return new Ponto(getX() + x, getY() + y);
    }

    /**
     * Realiza a rotação do ponto em torno de um ponto de referência (centroide) por um determinado ângulo.
     *
     * @param anguloGraus Ângulo de rotação em graus
     * @param centroide Ponto de referência (centroide) para a rotação
     * @return Novo ponto após a rotação
     */
    public Ponto pointRotation(int anguloGraus, Ponto centroide) {
        double angleRadians = Math.toRadians(anguloGraus);

        int x = (int) Math.round((centroide.getX_db() + ((getX() - centroide.getX_db()) * Math.cos(angleRadians) - (getY() - centroide.getY_db()) * Math.sin(angleRadians))));
        int y = (int) Math.round((centroide.getY_db() + (((getX() - centroide.getX_db()) * Math.sin(angleRadians) + (getY() - centroide.getY_db()) * Math.cos(angleRadians)))));

        return new Ponto(x, y);
    }
}
