package Game;

public class ComidaCirculo extends Comida{
    private Circulo circulo;

    public ComidaCirculo(int raio, Ponto centro)
    {
        this.circulo = new Circulo(raio,centro);
    }
}
