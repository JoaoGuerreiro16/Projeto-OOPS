package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import Game.Circulo;
import Game.Ponto;
import org.junit.jupiter.api.Test;

public class CirculoTest {

    @Test
    public void testeConstrutor()
    {
        Circulo c = new Circulo(4,new Ponto(5,5));
        assertEquals("Circulo de centro: (5,5) " + "com raio: 4.0", c.toString());
    }

    @Test
    public void testeTranslacaoCentroide()
    {
        Circulo c = new Circulo(1, new Ponto(2,2));
        assertEquals("Circulo de centro: (5,5) " + "com raio: 1.0",c.translacaoCentroide(5,5).toString());
    }
}
