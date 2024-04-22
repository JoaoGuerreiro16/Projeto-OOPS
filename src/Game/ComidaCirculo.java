package Game;

public class ComidaCirculo extends Comida{
    private Circulo circulo;

    public ComidaCirculo(Circulo circulo)
    {
        super(circulo.getCentro().getX(), circulo.getCentro().getY());
        this.circulo = circulo;
    }

    public Circulo getCirculo() {
        return circulo;
    }

}
