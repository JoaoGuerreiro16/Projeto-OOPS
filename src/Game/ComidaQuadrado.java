package Game;

import java.util.ArrayList;

public class ComidaQuadrado extends Comida{
   private Quadrado quadrado;

    public ComidaQuadrado(ArrayList<Ponto> pontos)
    {
        this.quadrado = new Quadrado(pontos);
    }

    public Quadrado getQuadrado() {
        return quadrado;
    }
}
