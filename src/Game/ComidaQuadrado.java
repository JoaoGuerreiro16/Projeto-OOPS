package Game;

import java.util.ArrayList;

public class ComidaQuadrado extends Comida{
   private Quadrado quadrado;

    public ComidaQuadrado(Quadrado quadrado, int pontuacao)
    {
        super(quadrado.calcularCentro(), pontuacao);
        this.quadrado = quadrado;

    }

    public Quadrado getQuadrado() {
        return quadrado;
    }

    @Override
    public boolean isConsumed(Snake snake) {
        return false;
    }
}
