import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/** Classe responsável pela criação de um Retangulo
 @author João Guerreiro , a81430
 @version 03/04/2024
 @inv os pontos recebidos têm de ser iguais a 3
 */

public class Retangulo extends Poligono {
    /**
     * Construtor para criar um retângulo a partir de uma string contendo as coordenadas dos pontos.
     *
     * @param input String contendo as coordenadas dos pontos
     */
    public Retangulo(String input) {
        this(toInt(input));
    }

    /**
     * Construtor para criar um retângulo a partir de uma lista de pontos.
     *
     * @param pontos Lista de pontos que define os vértices do retângulo
     */
    public Retangulo(ArrayList<Ponto> pontos) {
        super(pontos);
        if (pontos.size() != 4) {
            System.out.println("Retangulo:vi");
            System.exit(0);
        }

        for (int i = 0; i < pontos.size(); i++) {
            Ponto a = pontos.get(i);
            Ponto b = pontos.get((i + 1) % pontos.size());
            Ponto c = pontos.get((i + 2) % pontos.size());
            if (calcularAngulo(a, b, c) != 90) {
                System.out.println("Retangulo:vi");
                System.exit(0);
            }
        }
    }

    /**
     * Retorna uma representação em string do retângulo.
     *
     * @return String representando o retângulo
     */
    @Override
    public String toString() {
        return "Retangulo: " + getPontos().toString();
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
     * Calcula o ângulo entre três pontos.
     *
     * @param a Primeiro ponto
     * @param b Segundo ponto
     * @param c Terceiro ponto
     * @return Ângulo em graus
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
     * Método para realizar a translação do retângulo.
     *
     * @param x Valor a ser somado à coordenada x de cada ponto do retângulo
     * @param y Valor a ser somado à coordenada y de cada ponto do retângulo
     * @return Novo retângulo após a translação
     */
    @Override
    public Retangulo poligonTranslation(int x, int y)
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointTranslation(x,y));
        }

        return new Retangulo(pontos);
    }

    /**
     * Realiza a rotação do retângulo em torno de um ponto de referência (centroide) por um determinado ângulo.
     *
     * @param angulo    Ângulo de rotação em graus
     * @param centroide Ponto de referência (centroide) para a rotação
     * @return Novo retângulo após a rotação
     */
    @Override
    public Retangulo poligonRotation(int angulo, Ponto centroide) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointRotation(angulo, centroide));
        }
        return new Retangulo(pontos);
    }

}
