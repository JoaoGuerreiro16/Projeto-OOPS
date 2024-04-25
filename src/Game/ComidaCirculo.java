package Game;

public class ComidaCirculo extends Comida{
    private Circulo circulo;

    public ComidaCirculo(Circulo circulo)
    {
        super(circulo.getCentro());
        this.circulo = circulo;
    }

    public Circulo getCirculo() {
        return circulo;
    }

}
