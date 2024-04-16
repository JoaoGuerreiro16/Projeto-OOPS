package snake;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa um polígono definido por uma lista de pontos. Tem como responsabilidades calcular
 * o perímetro do polígono, fazer verificações das características que formam um polígono
 *
 *
 * @author Tomás Luz
 *
 * @version 1.2 27/02/2024
 *
 * @inv o polígono tem de ter pelo menos 3 pontos
 *
 */

public class Poligono {

    protected final ArrayList<Ponto> pontos;
    protected final List<SegmentoReta> lista_segmentos = new ArrayList<>();


    /**
     * Construtor da classe Poligono. Verifica se a lista de pontos forma um polígono válido e inicializa os pontos
     * do polígono.
     *
     * @param pontos Lista de pontos que define o polígono.
     */
    public Poligono(ArrayList<Ponto> pontos) {

        if (pontos.size() < 3) {

            System.out.println("Poligono:vi");
            throw new IllegalArgumentException();
        }
        retas(pontos);
        segmentos(pontos);
        this.pontos = pontos;

    }

    public Poligono(String input)
    {
        this(toInt(input));
    }

    /**
     * Obtém a lista de pontos que define o polígono.
     *
     * @return Lista de pontos do polígono.
     */
    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    /**
     * Obtém a lista de segmentos que define o polígono.
     *
     * @return Lista de segmentos do polígono
     */
    public List<SegmentoReta> getLista_segmentos() {
        return lista_segmentos;
    }

    /**
     * Cria as retas que conectam os pontos do polígono e verifica se eles são colineares.
     *
     * @param pontos Lista de pontos que define o polígono.
     */
    public void retas(List<Ponto> pontos) {

        for (int i = 0; i < pontos.size(); i++) {

            Reta reta = new Reta(pontos.get(i), pontos.get((i + 1) % pontos.size()));
            if (reta.colineares(pontos.get((i + 2) % pontos.size()))) {

                System.out.println("Poligono:vi");
                System.exit(0);
            }
        }
    }

    /**
     * Cria os segmentos de reta a partir dos pontos do polígono e verifica se eles se cruzam.
     *
     * @param pontos Lista de pontos que define o polígono.
     */
    public void segmentos(List<Ponto> pontos) {

        for (int i = 0; i < pontos.size(); i++) {
            lista_segmentos.add(new SegmentoReta(pontos.get(i), pontos.get((i + 1) % pontos.size())));
        }
        for (int i = 0; i < lista_segmentos.size(); i++) {
            if (lista_segmentos.get(i).arestasCruzam(lista_segmentos.get((i + 2) % lista_segmentos.size()))) {
                System.out.println("Poligono:vi");
                System.exit(0);
            }

        }

    }

    /**
     * Calcula o perímetro do polígono.
     *
     * @param pontos Lista de pontos que define o polígono.
     * @return perímetro do polígono.
     */

    public int perimetro(List<Ponto> pontos) {

        double result = 0;
        for (int i = 0; i < pontos.size(); i++) {

            result += pontos.get(i).dist(pontos.get((i + 1) % pontos.size()));
        }

        return (int) result;
    }

    /**
     * Verifica se este polígono é igual a outro objeto.
     *
     * @param o Objeto a ser comparado.
     * @return true se o objeto passado é um polígono e tem os mesmos segmentos de reta, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Poligono poligono = (Poligono) o;
        if (lista_segmentos.size() != poligono.getLista_segmentos().size()) {
            return false;
        }

        List<SegmentoReta> segmentocopy = new ArrayList<>(poligono.getLista_segmentos());
        for(int i = 0; i < lista_segmentos.size(); i++){
            for(int j = 0; j < segmentocopy.size(); j++){
                if(lista_segmentos.get(i).equals(segmentocopy.get(j))){
                    segmentocopy.remove(j % poligono.getLista_segmentos().size());
                    break;
                }
            }
        }
        return segmentocopy.isEmpty();
    }
    /**
     * Retorna um código hash baseado nos segmentos de reta e pontos do polígono.
     *
     * @return Código hash do polígono.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lista_segmentos,pontos);
    }
    /**
     *  Converte uma string de entrada em uma lista de pontos.
     *
     * @param input String de entrada contendo as coordenadas dos pontos.
     * @return Lista de pontos extraída da string de entrada.
     */
    private static ArrayList<Ponto> toInt (String input) {
        String [] parts = input.split(" ");

        if(parts.length == 0)
            System.exit(0);

        ArrayList<Ponto> pontos = new ArrayList<>();
        for(int i = 1, count = 0; count < Integer.parseInt(parts[0]); i+=2, count++){
            pontos.add(new Ponto(Integer.parseInt(parts[i]),Integer.parseInt(parts[i+1])));
        }
        return pontos;
    }
    /**
     * Retorna uma representação em forma de string do polígono.
     *
     * @return String representando o polígono.
     */
    @Override
    public String toString() {
        return "Poligono de " + getPontos().size() + " vertices: " + getPontos().toString();
    }
    /**
     * Calcula o centro geométrico do polígono considerando coordenadas inteiras.
     *
     * @return Ponto representando o centro do polígono com coordenadas inteiras.
     */
    public Ponto calcularCentroInt() {
        int centroX = 0;
        int centroY = 0;

        for (Ponto ponto : pontos) {
            centroX += ponto.getX();
            centroY += ponto.getY();
        }

        centroX /= pontos.size();
        centroY /= pontos.size();
        return new Ponto(centroX, centroY);
    }
    /**
     * Calcula o centro geométrico do polígono considerando coordenadas em double.
     *
     * @return Ponto representando o centro do polígono com coordenadas em double.
     */
    public Ponto calcularCentroDouble(){
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
     * Realiza a rotação do polígono em torno de um ponto (centroide) dado um ângulo.
     * Para cada ponto do polígono, aplica-se a rotação utilizando a fórmula de rotação em torno de um ponto.
     *
     * @param angulo Ângulo de rotação em graus.
     * @param centroide Ponto em torno do qual o polígono será rotacionado.
     * @return Novo polígono resultante da rotação.
     */
    public Poligono rotacao(int angulo, Ponto centroide) {
        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.rotacaoPonto(angulo, centroide));
        }
        return new Poligono(newPontos);
    }

    /**
     * Realiza a translação do polígono movendo todos os seus pontos por uma quantidade específica nas direções x e y.
     * Para cada ponto do polígono, aplica-se a translação utilizando a função de translação definida na classe Ponto.
     *
     * @param x Quantidade a ser transladada na direção x.
     * @param y Quantidade a ser transladada na direção y.
     * @return Novo polígono resultante da translação.
     */
    public Poligono translacao(int x, int y)
    {
        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.translacaoPonto(x, y));
        }

        return new Poligono(newPontos);
    }
    /**
     * Realiza a translação do polígono de modo que seu centroide seja movido para uma nova posição especificada.
     *
     * @param novoCentroideX Nova coordenada x para o centroide do polígono.
     * @param novoCentroideY Nova coordenada y para o centroide do polígono.
     * @return Novo polígono resultante da translação do centroide para a nova posição.
     */
    public Poligono translacaoCentroide(int novoCentroideX, int novoCentroideY){

        ArrayList<Ponto> newPontos = new ArrayList<>();
        Ponto centroidePoligono = calcularCentroInt();
        int deslocamentoX = novoCentroideX - centroidePoligono.getX();
        int deslocamentoY = novoCentroideY - centroidePoligono.getY();

        return translacao(deslocamentoX, deslocamentoY);

    }
}
