package Game.ModelLayer;

import java.util.ArrayList;



/**
 * Classe que representa um retângulo como subclasse de um poligono.
 *
 * @author Tomás Luz & Joao Guerreiro
 *
 * @version 1.0 01/04/2024
 *
 * @inv o retangulo tem de ter 4 pontos e os seus angulos internos tem 90 graus
 */
public class Retangulo extends Poligono {

    /**
     * Construtor da classe Retangulo. Verifica se a lista de pontos forma um retângulo válido.
     *
     * @param pontos Lista de pontos que define o retângulo.
     */
    public Retangulo(ArrayList<Ponto> pontos){

        super(pontos);
        if(pontos.size() != 4){
            System.out.println("Retangulo:vi");
            System.exit(0);
        }

        for(int i = 0; i < pontos.size(); i++){

            Ponto a = pontos.get(i);
            Ponto b = pontos.get((i+1) % pontos.size());
            Ponto c = pontos.get((i+2) % pontos.size());
            if(calcularAngulo(a,b,c) != 90){

                System.out.println("Retangulo:vi");
                System.exit(0);
            }

        }
    }

    /**
     * Construtor da classe Retangulo a partir de uma string de entrada. Transforma a string em uma lista de pontos.
     *
     * @param input String de entrada contendo as coordenadas dos pontos.
     */
    public Retangulo(String input)
    {
        this(toInt(input));
    }


    /**
     * Retorna uma representação em forma de string do retângulo.
     *
     * @return String representando o retângulo.
     */
    @Override
    public String toString() {
        return "Retangulo: " + getPontos().toString();
    }

    /**
     *  Converte uma string de entrada em uma lista de pontos.
     *
     * @param input String de entrada contendo as coordenadas dos pontos.
     * @return Lista de pontos extraída da string de entrada.
     */
    private static ArrayList<Ponto> toInt (String input) {
        String [] parts = input.split(" ");

        if(parts.length-1 % 2 == 0)
            System.exit(0);

        ArrayList<Ponto> pontos = new ArrayList<>();
        for(int i = 0; i < parts.length; i+=2){
            pontos.add(new Ponto(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
        }
        return pontos;
    }

    /**
     * Método para calcular o ângulo entre três pontos.
     *
     * @param a Primeiro ponto.
     * @param b Segundo ponto.
     * @param c Terceiro ponto.
     * @return O ângulo em graus formado pelos pontos a, b e c.
     */
    private double calcularAngulo(Ponto a, Ponto b, Ponto c) {
        double lado1x = b.getX() - a.getX();
        double lado1y = b.getY() - a.getY();
        double lado2x = c.getX() - b.getX();
        double lado2y = c.getY() - b.getY();

        double produtoEscalar = (lado1x * lado2x) + (lado1y * lado2y);

        double normaLado1 = Math.sqrt((lado1x * lado1x) + (lado1y * lado1y));
        double normaLado2 = Math.sqrt((lado2x * lado2x) + (lado2y * lado2y));


        double cosenoAngulo = produtoEscalar / (normaLado1 * normaLado2);

        double angulo = Math.acos(cosenoAngulo) * (180 / Math.PI);

        return angulo;
    }
    /**
     * Realiza a rotação do retângulo em torno de um ponto (centroide) dado um ângulo.
     * Para cada ponto do retângulo, aplica-se a rotação utilizando a função de rotação definida na classe Ponto.
     *
     * @param angulo Ângulo de rotação em graus.
     * @param centroide Ponto em torno do qual o retângulo será rotacionado.
     * @return Novo retângulo resultante da rotação.
     */
    @Override
    public Retangulo rotacao(int angulo, Ponto centroide) {

        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.rotacaoPonto(angulo, centroide));
        }
        return new Retangulo(newPontos);
    }

    /**
     * Realiza a translação do retângulo movendo todos os seus pontos por uma quantidade específica nas direções x e y.
     * Para cada ponto do retângulo, aplica-se a translação utilizando a função de translação definida na classe Ponto.
     *
     * @param x Quantidade a ser transladada na direção x.
     * @param y Quantidade a ser transladada na direção y.
     * @return Novo retângulo resultante da translação.
     */
    @Override
    public Retangulo translacao(double x, double y)
    {
        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.translacaoPonto(x, y));
        }

        return new Retangulo(newPontos);
    }
    /**
     * Realiza a translação do retângulo de modo que seu centroide seja movido para uma nova posição especificada.
     *
     * @param novoCentroideX Nova coordenada x para o centroide do retângulo.
     * @param novoCentroideY Nova coordenada y para o centroide do retângulo.
     * @return Novo retângulo resultante da translação do centroide para a nova posição.
     */
    @Override
    public Retangulo translacaoCentroide(double novoCentroideX, double novoCentroideY){
        Ponto centroidePoligono = calcularCentro();
        double deslocamentoX = novoCentroideX - centroidePoligono.getX();
        double deslocamentoY = novoCentroideY - centroidePoligono.getY();

        return translacao(deslocamentoX, deslocamentoY);

    }

    /**
 * Verifica se um ponto está contido dentro dos limites definidos pelos vértices de um polígono.
 * O método itera sobre cada vértice do polígono para determinar os valores mínimos e máximos
 * para as coordenadas x e y, que representam os limites do polígono.
 *
 * @param p O ponto a ser verificado.
 * @return true se o ponto estiver dentro dos limites do polígono, false caso contrário.
 * 
 * Este método percorre todos os vértices do polígono, atualizando os valores mínimos e máximos
 * para x e y com base nos valores dos vértices. Uma vez determinados os valores mínimos e máximos,
 * o método verifica se as coordenadas x e y do ponto fornecido estão entre os valores mínimos e máximos,
 * o que indicaria que o ponto está dentro dos limites do polígono.
 */

    @Override
    public boolean containsPonto(Ponto p) {
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;

        for (Ponto vertice : pontos) {
            if (vertice.getX() < minX) minX = vertice.getX();
            if (vertice.getX() > maxX) maxX = vertice.getX();
            if (vertice.getY() < minY) minY = vertice.getY();
            if (vertice.getY() > maxY) maxY = vertice.getY();
        }

        return (p.getX() >= minX && p.getX() <= maxX) && (p.getY() >= minY && p.getY() <= maxY);
    }
}
