import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Classe responsável pela criação de um Poligono
  @author João Guerreiro , a81430
  @version 23/02/2024
 */

public class Poligono {

    private final ArrayList<Ponto> pontos;
    private final List<SegmentoReta> list_sReta = new ArrayList<>();

    /**
     * Construtor para criar um polígono a partir de uma lista de pontos.
     *
     * @param pontos Lista de pontos que define os vértices do polígono
     */
    public Poligono(ArrayList<Ponto> pontos) {
        if (pontos.size() < 3) {
            System.out.println("Poligono:vi");
            System.exit(0);
        }

        for (int i = 0; i < pontos.size(); i++) {
            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
            list_sReta.add(new SegmentoReta(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }

        for (int i = 0; i < list_sReta.size(); i++) {
            if (list_sReta.get(i).arestasCruzam(list_sReta.get((i + 2) % list_sReta.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }

        this.pontos = pontos;
    }

    /**
     * Construtor para criar um polígono a partir de uma string contendo as coordenadas dos pontos.
     *
     * @param input String contendo as coordenadas dos pontos
     */
    public Poligono(String input) {
        this(toInt(input));
    }

    /**
     * Obtém a lista de pontos que define os vértices do polígono.
     *
     * @return Lista de pontos do polígono
     */
    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    /**
     * Obtém a lista de segmentos de reta que compõem o polígono.
     *
     * @return Lista de segmentos de reta do polígono
     */
    public List<SegmentoReta> getList_sReta() {
        return list_sReta;
    }

    /**
     * Calcula o perímetro do polígono.
     *
     * @return Perímetro do polígono
     */
    public int perimetro() {
        double perimetro = 0;
        for (int i = 0; i < pontos.size(); i++) {
            perimetro += pontos.get(i).dist(pontos.get((i + 1) % pontos.size()));
        }
        return (int) perimetro;
    }

    /**
     * Retorna uma representação em string do polígono.
     *
     * @return String representando o polígono
     */
    @Override
    public String toString() {
        return "Poligono de " + getPontos().size() + " vertices: " + getPontos().toString();
    }

    /**
     * Verifica se dois polígonos são iguais.
     *
     * @param o Objeto a ser comparado
     * @return true se os polígonos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Poligono poligono = (Poligono) object;
        if (list_sReta.size() != poligono.getList_sReta().size()){
            return false;
        }
        List<SegmentoReta> segmentoCopy = new ArrayList<>(poligono.getList_sReta());
        for (int i = 0;i< list_sReta.size();i++){
            for (int j = 0; j < poligono.getList_sReta().size(); j++) {
                if (list_sReta.get(i).equals(segmentoCopy.get(j))){
                    segmentoCopy.remove(j % poligono.getList_sReta().size());
                    break;
                }
            }
        }
        return segmentoCopy.isEmpty();
    }

    /**
     * Retorna o código hash do polígono.
     *
     * @return Código hash do polígono
     */
    @Override
    public int hashCode() {
        return Objects.hash(pontos, list_sReta);
    }

    /**
     * Converte uma string contendo coordenadas de pontos para uma lista de pontos.
     *
     * @param input String contendo coordenadas dos pontos
     * @return Lista de pontos
     */
    private static ArrayList<Ponto> toInt(String input) {
        String[] parts = input.split(" ");
        if (parts.length == 0)
            System.exit(0);

        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 1, count = 0; count < Integer.parseInt(parts[0]); i += 2, count++) {
            pontos.add(new Ponto(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])));
        }
        return pontos;
    }

    /**
     * Realiza a translação do polígono.
     *
     * @param x Valor a ser somado à coordenada x de cada ponto do polígono
     * @param y Valor a ser somado à coordenada y de cada ponto do polígono
     * @return Novo polígono após a translação
     */
    public Poligono poligonTranslation(int x, int y) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < this.getPontos().size(); i++) {
            pontos.add(getPontos().get(i).pointTranslation(x, y));
        }
        return new Poligono(pontos);
    }

    /**
     * Calcula o centroide (centro de massa) do polígono.
     *
     * @return Ponto representando o centroide do polígono
     */
    public Ponto calcularCentro() {
        double centroX = 0;
        double centroY = 0;

        for (Ponto ponto : pontos) {
            centroX += ponto.getX();
            centroY += ponto.getY();
        }

        centroX /= pontos.size();
        centroY /= pontos.size();
        return new Ponto(centroX, centroY);
    }

    /**
     * Realiza a rotação do polígono em torno de um ponto de referência (centroide) por um determinado ângulo.
     *
     * @param angulo    Ângulo de rotação em graus
     * @param centroide Ponto de referência (centroide) para a rotação
     * @return Novo polígono após a rotação
     */
    public Poligono poligonRotation(int angulo, Ponto centroide) {
        ArrayList<Ponto> pontoscopy = new ArrayList<>();
        for (int i = 0; i < pontos.size(); i++) {
            pontoscopy.add(pontos.get(i).pointRotation(angulo, centroide));
        }
        return new Poligono(pontoscopy);
    }


}