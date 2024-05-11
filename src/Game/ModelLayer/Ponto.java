
package Game.ModelLayer;
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
    private double x,y;

    /**
     * Construtor da classe Ponto com coordenadas do tipo double.
     * Define as coordenadas x e y do ponto.
     *
     * @param x coordenada x do ponto.
     * @param y coordenada y do ponto.
     */
    public Ponto(double x,double y)
    {
       
        setX(x);
        setY(y);
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
     * Obtém a coordenada x do ponto como double.
     *
     * @return Coordenada x do ponto como double.
     */
    public double getX() {
        return x;
    }
    /**
     * Define a coordenada x do ponto como double.
     *
     * @param x Nova coordenada x do ponto como double.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Obtém a coordenada y do ponto como double.
     *
     * @return Coordenada y do ponto como double.
     */
    public double getY() {
        return y;
    }
    /**
     * Define a coordenada y do ponto como double.
     *
     * @param y Nova coordenada y do ponto como double.
     */
    public void setY(double y) {
        this.y = y;
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

        int x = (int) Math.round((centroide.getX() + ((getX() - centroide.getX()) * Math.cos(angleRadians) - (getY() - centroide.getY()) * Math.sin(angleRadians))));
        int y = (int) Math.round((centroide.getY() + (((getX() - centroide.getX()) * Math.sin(angleRadians) + (getY() - centroide.getY()) * Math.cos(angleRadians)))));

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