import java.util.ArrayList;

/** Classe responsável pela criação de um triangulo
 @author João Guerreiro , a81430
 @version 03/04/2024
 @inv os pontos recebidos têm de ser iguais a 3
 */

public class Triangulo extends Poligono {
    /**
     * Construtor para criar um triângulo a partir de uma string contendo as coordenadas dos pontos.
     *
     * @param input String contendo as coordenadas dos pontos
     */
    public Triangulo(String input) {
        this(toInt(input));
    }

    /**
     * Construtor para criar um triângulo a partir de uma lista de pontos.
     *
     * @param pontos Lista de pontos que define os vértices do triângulo
     */
    public Triangulo(ArrayList<Ponto> pontos) {
        super(pontos);
        if (getPontos().size() != 3) {
            System.out.println("Triangulo:vi");
            System.exit(0);
        }
    }

    /**
     * Retorna uma representação em string do triângulo.
     *
     * @return String representando o triângulo
     */
    @Override
    public String toString() {
        return "Triangulo: " + getPontos().toString();
    }

    /**
     * Converte uma string contendo coordenadas de pontos para uma lista de pontos.
     *
     * @param input String contendo coordenadas dos pontos
     * @return Lista de pontos
     */
    private static ArrayList<Ponto> toInt(String input) {
        String[] parts = input.split(" ");
        if ((parts.length - 1) % 2 == 0)
            System.exit(0);

        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < parts.length; i += 2) {
            pontos.add(new Ponto(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
        }
        return pontos;
    }

    /**
     * Realiza a translação do triângulo.
     *
     * @param x Valor a ser somado à coordenada x de cada ponto do triângulo
     * @param y Valor a ser somado à coordenada y de cada ponto do triângulo
     * @return Novo triângulo após a translação
     */

    @Override
    public Triangulo poligonTranslation(int x, int y)
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointTranslation(x,y));
        }

        return new Triangulo(pontos);
    }

    /**
     * Realiza a rotação do triângulo em torno de um ponto de referência (centroide) por um determinado ângulo.
     *
     * @param angulo    Ângulo de rotação em graus
     * @param centroide Ponto de referência (centroide) para a rotação
     * @return Novo triângulo após a rotação
     */
    @Override
    public Triangulo poligonRotation(int angulo, Ponto centroide) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointRotation(angulo, centroide));
        }
        return new Triangulo(pontos);
    }

}
