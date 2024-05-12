package Game.ModelLayer;

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

    public void setQuadrado(Quadrado quadrado) {
        this.quadrado = quadrado;
    }

    @Override
    public boolean isConsumed(Snake snake) {


        return (snake.containsQuadrado(this));
    }

    @Override
    public boolean interceta(Poligono poligono){

        return this.quadrado.intercetaPoligono(poligono);

    }

    @Override
    public boolean containsPonto(Ponto ponto)
    {
        return quadrado.containsPonto(ponto);
    }

}
