
package Game;
import java.util.Objects;

/**
 * Classe que representa um ponto.
 *
 * @author Tomás Luz
 *
 * @version 1.3 05/04/2024
 *
 * @inv os pontos tem de estar no primeiro quadrante
 *
 */
public class Ponto {
    private int x,y;
    private double x_db,y_db;

    /**
     * Construtor da classe Ponto com coordenadas do tipo double.
     * Define as coordenadas x e y do ponto.
     *
     * @param x_db coordenada x do ponto.
     * @param y_db coordenada y do ponto.
     */
    public Ponto(double x_db,double y_db)
    {
        check(x,y);
        setX_db(x_db);
        setY_db(y_db);
    }

    /**
     * Construtor da classe Ponto.
     * Define as coordenadas x e y do ponto.
     *
     * @param x coordenada x do ponto.
     * @param y coordenada y do ponto.
     */
    public Ponto(int x, int y){

        check(x,y);
        setX(x);
        setY(y);
    }
    /**
     * Método privado para verificar se as coordenadas são iguais e substituir, se necessário.
     *
     * @param x Coordenada x do ponto.
     * @param y Coordenada y do ponto.
     */
    private void check(double x, double y)
    {
        if(Math.abs(x-y) < Math.pow(10, -9))
            x = y;
    }

    /**
     * Calcula a distância entre este ponto e outro ponto dado.
     *
     * @param p Ponto ao qual a distância será calculada.
     * @return A distância entre os dois pontos.
     */
    public double dist(Ponto p){
        double dx = x - p.x;
        double dy = y - p.y;

        return Math.sqrt(dx*dx + dy*dy);
    }
    /**
     * Obtém a coordenada x do ponto.
     *
     * @return Coordenada x do ponto.
     */
    public int getX() {
        return x;
    }

    /**
     * Define a coordenada x do ponto.
     *
     * @param x Nova coordenada x do ponto.
     */
    public void setX(int x) {

        this.x = x;
    }
    /**
     * Obtém a coordenada y do ponto.
     *
     * @return Coordenada y do ponto.
     */
    public int getY() {
        return y;
    }
    /**
     * Define a coordenada y do ponto.
     *
     * @param y Nova coordenada y do ponto.
     */
    public void setY(int y) {

        this.y = y;
    }
    /**
     * Obtém a coordenada x do ponto como double.
     *
     * @return Coordenada x do ponto como double.
     */
    public double getX_db() {
        return x_db;
    }
    /**
     * Define a coordenada x do ponto como double.
     *
     * @param x_db Nova coordenada x do ponto como double.
     */
    public void setX_db(double x_db) {
        this.x_db = x_db;
    }
    /**
     * Obtém a coordenada y do ponto como double.
     *
     * @return Coordenada y do ponto como double.
     */
    public double getY_db() {
        return y_db;
    }
    /**
     * Define a coordenada y do ponto como double.
     *
     * @param y_db Nova coordenada y do ponto como double.
     */
    public void setY_db(double y_db) {
        this.y_db = y_db;
    }
    /**
     * Verifica se este ponto é igual a outro objeto.
     *
     * @param o Objeto a ser comparado.
     * @return true se o objeto passado é um ponto e tem as mesmas coordenadas x e y, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ponto ponto = (Ponto) o;
        return x == ponto.x && y == ponto.y;
    }
    /**
     * Retorna um código hash baseado nas coordenadas x e y do ponto.
     *
     * @return Código hash do ponto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * Retorna uma representação em forma de string do ponto.
     *
     * @return String representando o ponto no formato "(x,y)".
     */
    @Override
    public String toString()
    {
        return "(" + getX() + "," + getY() + ")";
    }
    /**
     * Realiza a rotação deste ponto em torno de outro ponto (centroide) por um ângulo dado.
     *
     * @param anguloGraus Ângulo de rotação em graus.
     * @param centroide Ponto em torno do qual o ponto será rotacionado.
     * @return Ponto resultante após a rotação.
     */
    public Ponto rotacaoPonto(int anguloGraus, Ponto centroide) {
        double angleRadians = Math.toRadians(anguloGraus);

        int x = (int) Math.round((centroide.getX_db() + ((getX() - centroide.getX_db()) * Math.cos(angleRadians) - (getY() - centroide.getY_db()) * Math.sin(angleRadians))));
        int y = (int) Math.round((centroide.getY_db() + (((getX() - centroide.getX_db()) * Math.sin(angleRadians) + (getY() - centroide.getY_db()) * Math.cos(angleRadians)))));

        return new Ponto(x, y);
    }
    /**
     * Realiza a translação do ponto movendo suas coordenadas por uma quantidade específica nas direções x e y.
     *
     * @param x Quantidade a ser transladada na direção x.
     * @param y Quantidade a ser transladada na direção y.
     * @return Novo ponto resultante da translação.
     */
    public Ponto translacaoPonto(double x, double y){

        double newX = getX() + x;
        double newY = getY() + y;
        return new Ponto(newX, newY);
    }
}