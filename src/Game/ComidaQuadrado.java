package Game;

import java.util.ArrayList;

public class ComidaQuadrado extends Comida{
   private Quadrado quadrado;

    public ComidaQuadrado(Quadrado quadrado)
    {
        super(quadrado.calcularCentro());
        this.quadrado = quadrado;

    }

    public Quadrado getQuadrado() {
        return quadrado;
    }
}
