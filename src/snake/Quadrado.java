
package snake;
import java.util.ArrayList;
/**
 * Classe que representa um quadrado como subclasse do retangulo e do poligono.
 *
 *
 * @author Tomás Luz
 *
 * @version 1.0 01/04/2024
 *
 * @inv o quadrado tem de ter 4 pontos
 *
 */
public class Quadrado extends Retangulo
{
    /**
     * Construtor da classe Quadrado a partir de uma string de entrada. Transforma a string em uma lista de pontos.
     *
     * @param input String de entrada contendo as coordenadas dos pontos.
     */
    public Quadrado(String input){this(toInt(input)); }

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
     * Retorna uma representação em forma de string do quadrado.
     *
     * @return String representando o quadrado.
     */
    @Override
    public String toString() {
        return "Quadrado: " + getPontos().toString();
    }

    /**
     *Converte uma string de entrada em uma lista de pontos.
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
     * Realiza a rotação do quadrado em torno de um ponto (centroide) dado um ângulo.
     * Para cada ponto do quadrado, aplica-se a rotação utilizando a função de rotação definida na classe Ponto.
     *
     * @param angulo Ângulo de rotação em graus.
     * @param centroide Ponto em torno do qual o quadrado será rotacionado.
     * @return Novo quadrado resultante da rotação.
     */
    @Override
    public Quadrado rotacao(int angulo, Ponto centroide) {
        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.rotacaoPonto(angulo, centroide));
        }
        return new Quadrado(newPontos);
    }
    /**
     * Realiza a translação do quadrado movendo todos os seus pontos por uma quantidade específica nas direções x e y.
     * Para cada ponto do quadrado, aplica-se a translação utilizando a função de translação definida na classe Ponto.
     *
     * @param x Quantidade a ser transladada na direção x.
     * @param y Quantidade a ser transladada na direção y.
     * @return Novo quadrado resultante da translação.
     */
    @Override
    public Quadrado translacao(int x, int y)
    {
        ArrayList<Ponto> newPontos = new ArrayList<>();
        for (Ponto ponto : pontos) {
            newPontos.add(ponto.translacaoPonto(x, y));
        }
        return new Quadrado(newPontos);
    }
    /**
     * Realiza a translação do quadrado de modo que seu centroide seja movido para uma nova posição especificada.
     *
     * @param novoCentroideX Nova coordenada x para o centroide do quadrado.
     * @param novoCentroideY Nova coordenada y para o centroide do quadrado.
     * @return Novo quadrado resultante da translação do centroide para a nova posição.
     */
    @Override
    public Quadrado translacaoCentroide(int novoCentroideX, int novoCentroideY){

        ArrayList<Ponto> newPontos = new ArrayList<>();
        Ponto centroidePoligono = calcularCentroInt();
        int deslocamentoX = novoCentroideX - centroidePoligono.getX();
        int deslocamentoY = novoCentroideY - centroidePoligono.getY();

        return translacao(deslocamentoX, deslocamentoY);

    }
}

