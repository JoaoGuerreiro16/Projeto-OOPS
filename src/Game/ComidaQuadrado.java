package Game;

import java.util.ArrayList;

public class ComidaQuadrado extends Comida{
    Quadrado quadrado;

    public ComidaQuadrado(ArrayList<Ponto> pontos)
    {
        this.quadrado = new Quadrado(pontos);
    }
}
