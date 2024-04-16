import java.util.Objects;

/**Classe responsável pela criação de um segmento de reta
  @author João Guerreiro , a81430
  @version 21/02/2024
  @inv os pontos recebidos não podem ser iguais
  */

public class SegmentoReta {

    private Ponto p1;
    private Ponto p2;

    public SegmentoReta(Ponto p1, Ponto p2)
    {
        check(p1,p2);
        setP1(p1);
        setP2(p2);
    }

     /** Verifica se os dois pontos são iguais e se forem,o programa acaba com uma mensagem
           @param p1 Primeito ponto usado para a criação da reta
           @param p2 Segundo ponto usado para a criação da reta
        */ 

    public void check(Ponto p1,Ponto p2){
 
            if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
            {
                System.out.println("Segmento:vi");
                System.exit(0);
            }
        }

    public Ponto getP1() {
        return p1;
    }

    public void setP1(Ponto p1) {
        this.p1 = p1;
    }

    public Ponto getP2() {
        return p2;
    }

    public void setP2(Ponto p2) {
        this.p2 = p2;
    } 

    /** Método responsável pelo calculo do produto vetorial de 3 pontos
       @param p1 Primeito ponto usado o calculo
       @param p2 Segundo ponto usado o calculo
       @param p3 Terceiro ponto usado o calculo
       @return Resultado do produto vetorial
    */

    public double produtoVetorial(Ponto p1, Ponto p2,Ponto p3)
    {
        return (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
    }

    /** Método responsável pela verificação de que nenhum par de arestas se cruza. Se se cruzarem acaba o programa com uma mensagem     
       @param segmentoDeReta Segmento de reta recebido
    */

    public boolean arestasCruzam(SegmentoReta segmentoDeReta){
        double abac = produtoVetorial(p2,p1,segmentoDeReta.p1);
        double abad = produtoVetorial(p2,p1,segmentoDeReta.p2);
        double cdca = produtoVetorial(segmentoDeReta.p2,segmentoDeReta.p1,p1);
        double cdcb = produtoVetorial(segmentoDeReta.p2,segmentoDeReta.p1,p2);
        return abac * abad < 0 && cdca * cdcb < 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentoReta that = (SegmentoReta) o;
        return p1.equals(that.getP1()) && p2.equals(that.getP2()) || p1.equals(that.getP2()) && p2.equals(that.getP1());
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}
