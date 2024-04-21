
package Game;
import java.util.Objects;

/**
 * Classe que representa um segmento de reta definido por dois pontos. Tem como responsabilidades
 * verificar se os pontos dados definem um segmento de reta válido e verificar se este segmento se cruza
 * com outro segmento
 *
 * @version 1.0 27/02/2024
 *
 * @inv os pontos não podem ser iguais
 */
public class SegmentoReta {

    private Ponto ponto1, ponto2;
    /**
     * Construtor da classe SegmentoReta.
     * Verifica se os pontos dados definem um segmento válido e inicializa os pontos.
     *
     * @param ponto1 O primeiro ponto que define o segmento de reta.
     * @param ponto2 O segundo ponto que define o segmento de reta.
     */
    public SegmentoReta(Ponto ponto1, Ponto ponto2){

        if(ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()){

            System.out.println("Segmento:vi");
            System.exit(0);
        }
        setPonto1(ponto1);
        setPonto2(ponto2);
    }
    /**
     * Obtém o primeiro ponto que define o segmento de reta.
     *
     * @return O primeiro ponto que define o segmento de reta.
     */
    public Ponto getPonto1() {
        return ponto1;
    }
    /**
     * Define o primeiro ponto que define o segmento de reta.
     *
     * @param ponto1 O novo primeiro ponto que define o segmento de reta.
     */
    public void setPonto1(Ponto ponto1) {
        this.ponto1 = ponto1;
    }
    /**
     * Obtém o segundo ponto que define o segmento de reta.
     *
     * @return O segundo ponto que define o segmento de reta.
     */
    public Ponto getPonto2() {
        return ponto2;
    }
    /**
     * Define o segundo ponto que define o segmento de reta.
     *
     * @param ponto2 O novo segundo ponto que define o segmento de reta.
     */
    public void setPonto2(Ponto ponto2) {
        this.ponto2 = ponto2;
    }
    /**
     * Calcula o produto vetorial entre três pontos.
     *
     * @param ponto1 Primeiro ponto.
     * @param ponto2 Segundo ponto.
     * @param ponto3 Terceiro ponto.
     * @return O valor do produto vetorial.
     */
    public double produtoVetorial(Ponto ponto1, Ponto ponto2, Ponto ponto3){

        return (ponto2.getX() - ponto1.getX()) * (ponto3.getY() - ponto1.getY()) - (ponto2.getY() - ponto1.getY()) * (ponto3.getX() - ponto1.getX());
    }

    /**
     * Verifica se este segmento de reta se cruza com outro segmento
     *
     * @param segmento O outro segmente a ser verificado
     *
     * @return Retorna "true" se se cruzarem e "false" caso contrário
     */

    public boolean arestasCruzam(SegmentoReta segmento) {

        double produtoVetorial1 = produtoVetorial(this.ponto2, this.ponto1, segmento.ponto1);
        double produtoVetorial2 = produtoVetorial(this.ponto2, this.ponto1, segmento.ponto2);
        double produtoVetorial3 = produtoVetorial(segmento.ponto2, segmento.ponto1, this.ponto1);
        double produtoVetorial4 = produtoVetorial(segmento.ponto2, segmento.ponto1, this.ponto2);

        if (produtoVetorial1 * produtoVetorial2 < 0 && produtoVetorial3 * produtoVetorial4 < 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentoReta that = (SegmentoReta) o;
        return ponto1.equals(that.getPonto1()) && ponto2.equals(that.getPonto2()) || ponto1.equals(that.getPonto2()) && ponto2.equals(that.getPonto1());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ponto1, ponto2);
    }

    public double lenght()
    {
        return ponto1.dist(ponto2);
    }
}
