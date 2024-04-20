package Game;

public class Circulo {
    private double raio;
    private Ponto centro;

    public Circulo(double raio, Ponto centro) {

        setRaio(raio);
        this.centro = centro;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio <= 0) {
            System.out.println("Circulo:vi");
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

        return new Circulo(raio, centro);
    }

    public Circulo translacaoCentroide(int x, int y)
    {
        Ponto novoCentro = new Ponto(x,y);

        return new Circulo(raio,novoCentro);
    }

}
