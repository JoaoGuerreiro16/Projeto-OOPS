import java.util.ArrayList;

/**Classe responsável pela criação de um Quadrado
 @author João Guerreiro , a81430
 @version 03/04/2024
 */

public class Quadrado extends Retangulo {
    /**
     * Construtor para criar um quadrado a partir de uma string contendo as coordenadas dos pontos.
     *
     * @param input String contendo as coordenadas dos pontos
     */
    public Quadrado(String input) {
        this(toInt(input));
    }

    /**
     * Construtor para criar um quadrado a partir de uma lista de pontos.
     *
     * @param pontos Lista de pontos que define os vértices do quadrado
     */
    public Quadrado(ArrayList<Ponto> pontos) {
        super(pontos);

        double comprimentoLado = pontos.get(0).dist(pontos.get(1));
        for (int i = 1; i < pontos.size(); i++) {
            double lado = pontos.get(i).dist(pontos.get((i + 1) % pontos.size()));
            if (lado != comprimentoLado) {
                System.out.println("Quadrado:vi");
                System.exit(0);
            }
        }
    }

    /**
     * Retorna uma representação em string do quadrado.
     *
     * @return String representando o quadrado
     */
    @Override
    public String toString() {
        return "Quadrado: " + getPontos().toString();
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
     * Realiza a translação do quadrado.
     *
     * @param x Valor a ser somado à coordenada x de cada ponto do quadrado
     * @param y Valor a ser somado à coordenada y de cada ponto do quadrado
     * @return Novo quadrado após a translação
     */

    @Override
    public Quadrado poligonTranslation(int x, int y)
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointTranslation(x,y));
        }

        return new Quadrado(pontos);
    }

    /**
     * Realiza a rotação do quadrado em torno de um ponto de referência (centroide) por um determinado ângulo.
     *
     * @param angulo    Ângulo de rotação em graus
     * @param centroide Ponto de referência (centroide) para a rotação
     * @return Novo quadrado após a rotação
     */
    @Override
    public Quadrado poligonRotation(int angulo, Ponto centroide) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointRotation(angulo, centroide));
        }
        return new Quadrado(pontos);
    }

}

