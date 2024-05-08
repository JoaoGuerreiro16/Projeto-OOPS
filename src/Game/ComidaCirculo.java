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

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    @Override
    public boolean isConsumed(Snake snake) {
        return snake.containsCirculo(this);
    }

}
