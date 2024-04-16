/**Classe responsável pela criação de uma reta
  @author João Guerreiro , a81430
  @version 21/02/2024
  @inv os pontos recebidos não podem ser iguais
 */
public class Reta{

    private Ponto p1;
    private Ponto p2;

    /**
     * Construtor para criar uma reta com dois pontos.
     *
     * @param p1 Primeiro ponto usado para a criação da reta
     * @param p2 Segundo ponto usado para a criação da reta
     */
    public Reta(Ponto p1, Ponto p2) {
        check(p1, p2);
        setP1(p1);
        setP2(p2);
    }

    /**
     * Verifica se os dois pontos são iguais e se forem, encerra o programa com uma mensagem.
     *
     * @param p1 Primeiro ponto usado para a criação da reta
     * @param p2 Segundo ponto usado para a criação da reta
     */
    public void check(Ponto p1, Ponto p2) {
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            System.out.println("false");
            System.exit(0);
        }
    }

    /**
     * Verifica se existem 3 pontos consecutivos colineares. Se existirem, encerra o programa com uma mensagem.
     *
     * @param p3 Ponto recebido de modo a existirem 3 pontos para a verificação
     * @return true se os três pontos são colineares, false caso contrário
     */
    public boolean colineares(Ponto p3) {
        double diferenca_y = p2.getY() - p1.getY();
        double diferenca_x = p2.getX() - p1.getX();
        double m = diferenca_y / diferenca_x;
        double b = p1.getY() - m * p1.getX();
        if (p3.getY() == p3.getX() * m + b) {
            return true;
        }
        return false;
    }

    /**
     * Obtém o primeiro ponto da reta.
     *
     * @return Primeiro ponto da reta
     */
    public Ponto getP1() {
        return p1;
    }

    /**
     * Define o primeiro ponto da reta.
     *
     * @param p1 Novo primeiro ponto da reta
     */
    public void setP1(Ponto p1) {
        this.p1 = p1;
    }

    /**
     * Obtém o segundo ponto da reta.
     *
     * @return Segundo ponto da reta
     */
    public Ponto getP2() {
        return p2;
    }

    /**
     * Define o segundo ponto da reta.
     *
     * @param p2 Novo segundo ponto da reta
     */
    public void setP2(Ponto p2) {
        this.p2 = p2;
    }

}
