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
        assertEquals("Circulo de centro: (5,5) " + "com raio: 4", c.toString());
    }
}
