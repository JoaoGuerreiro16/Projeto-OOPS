package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import Game.ModelLayer.Circulo;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Quadrado;
import org.junit.jupiter.api.Test;

public class CirculoTest {

    @Test
    public void testeConstrutor()
    {
        Circulo c = new Circulo(new Ponto(5,5),4);
        assertEquals("Circulo de centro: (5,5) " + "com raio: 4.0", c.toString());
    }

    @Test
    public void testeTranslacaoCentroide()
    {
        Circulo c = new Circulo(new Ponto(2,2), 1);
        assertEquals("Circulo de centro: (5,5) " + "com raio: 1.0",c.translacaoCentroide(5,5).toString());
    }

    @Test
    public void testContainsPonto() {
        Circulo circulo = new Circulo(new Ponto(5, 5), 3);
        assertTrue(circulo.containsPonto(new Ponto(6, 6)));
        assertTrue(circulo.containsPonto(new Ponto(5, 8)));
        assertFalse(circulo.containsPonto(new Ponto(9, 9)));
    }
}
