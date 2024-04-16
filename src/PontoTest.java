import	static	org.junit.jupiter.api.Assertions.*; 
import	org.junit.jupiter.api.Test;

public class PontoTest {
    
    @Test
    public void testGetX() {
        Ponto ponto = new Ponto(3, 4);
        assertEquals(3, ponto.getX());
    }

    @Test
    public void testGetY() {
        Ponto ponto = new Ponto(3, 4);
        assertEquals(4, ponto.getY());
    }

    @Test
    public void testSetX() {
        Ponto ponto = new Ponto(3, 4);
        ponto.setX(5);
        assertEquals(5, ponto.getX());
    }

    @Test
    public void testSetY() {
        Ponto ponto = new Ponto(3, 4);
        ponto.setY(6);
        assertEquals(6, ponto.getY());
    }

    @Test
    public void testDistanciaEntrePontos() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(3, 4);
        
        double distancia = ponto1.dist(ponto2);
        
        assertEquals(5.0, distancia);
    }

    @Test
    public void rotationTest()
    {
        String input = "4 1 2 5 6 8 7 12 14";
        Ponto p = new Ponto(1,2);
        Poligono poligono = new Poligono(input);
        assertEquals("(12,2)",p.pointRotation(90,poligono.calcularCentro()).toString());
    }
}
