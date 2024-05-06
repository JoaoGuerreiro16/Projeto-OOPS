package Game;

public class ComidaCirculo extends Comida{
    private Circulo circulo;

    public ComidaCirculo(Circulo circulo, int pontuacao)
    {
        super(circulo.getCentro(), pontuacao);
        this.circulo = circulo;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    @Override
    public boolean isConsumed(Snake snake) {
        return false;
    }

}
