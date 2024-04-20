package Game;

public class Circulo {
    private int raio;
    private Ponto centro;

    public Circulo(int raio, Ponto centro) {

        setRaio(raio);
        this.centro = centro;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
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

    public int calcularArea() {
        return (int) Math.PI * raio * raio;
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

}
