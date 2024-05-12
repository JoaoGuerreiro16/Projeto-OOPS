package Game.ModelLayer;

public class Circulo {
    private double raio;
    private Ponto centro;

    public Circulo(Ponto centro, double raio) {

        setRaio(raio);
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio <= 0) {
           throw new IllegalArgumentException("O raio tem de ser maior que 0");
        }
        this.raio = raio;
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return "Circulo de centro: " + getCentro() + " com raio: " + getRaio();
    }

    public static Circulo toInt(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Formato de entrada inválido. Use 'raio x_centro y_centro'.");
        }

        int raio = Integer.parseInt(parts[0]);
        int centroX = Integer.parseInt(parts[1]);
        int centroY = Integer.parseInt(parts[2]);
        Ponto centro = new Ponto(centroX, centroY);

        return new Circulo(centro, raio);
    }

    public Circulo translacaoCentroide(int x, int y)
    {
        Ponto novoCentro = new Ponto(x,y);

        return new Circulo(novoCentro,raio);
    }


    public Quadrado criaQuadradoProtetor(Circulo circulo){

        return new Quadrado(this.centro, this.raio * 2);
    }

    public boolean containsPonto(Ponto ponto) {
        double distanceSquared = Math.pow(ponto.getX() - centro.getX(), 2) + Math.pow(ponto.getY() - centro.getY(), 2);
        return distanceSquared <= Math.pow(raio, 2);
    }

}
