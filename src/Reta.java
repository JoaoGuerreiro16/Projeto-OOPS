/**
 * Classe que representa uma reta definida por dois pontos. Tem como responsabilidades verificar se
 * os pontos dados definem uma resta válida e verifica se um terceiro ponto pertence a esta
 *
 * @author Tomás Luz
 *
 * @version 1.0 27/02/24
 *
 * @inv os pontos não podem ser iguais
 */
public class Reta {
    private Ponto ponto1, ponto2;

    /**
     * Construtor da classe Reta.
     * Verifica se os pontos dados definem uma reta válida e inicializa os pontos.
     *
     * @param ponto1 O primeiro ponto que define a reta.
     * @param ponto2 O segundo ponto que define a reta.
     */

    public Reta(Ponto ponto1, Ponto ponto2){

        if(ponto1.getX() == ponto2.getX() && ponto1.getY() == ponto2.getY()){

            System.out.println("false");
            System.exit(0);
        }
        setPonto1(ponto1);
        setPonto2(ponto2);

    }
    /**
     * Obtém o primeiro ponto que define a reta.
     *
     * @return O primeiro ponto que define a reta.
     */
    public Ponto getPonto1() {
        return ponto1;
    }
    /**
     * Define o primeiro ponto que define a reta.
     *
     * @param ponto1 O novo primeiro ponto que define a reta.
     */
    public void setPonto1(Ponto ponto1) {
        this.ponto1 = ponto1;
    }
    /**
     * Obtém o segundo ponto que define a reta.
     *
     * @return O segundo ponto que define a reta.
     */
    public Ponto getPonto2() {

        return ponto2;
    }
    /**
     * Define o segundo ponto que define a reta.
     *
     * @param ponto2 O novo segundo ponto que define a reta.
     */
    public void setPonto2(Ponto ponto2) {

        this.ponto2 = ponto2;
    }
    /**
     * Verifica se um ponto dado está na mesma reta definida por esta classe.
     *
     * @param ponto3 O ponto a ser verificado se está na mesma reta.
     */
    public boolean colineares(Ponto ponto3){

        double dif_y = ponto2.getY() - ponto1.getY();
        double dif_x = ponto2.getX() - ponto1.getX();
        double m = dif_y/dif_x;
        double b = ponto1.getY() - m*ponto1.getX();
        if (ponto3.getY() == ponto3.getX()*m + b){

            return true;

        }
        return false;
    }
}
