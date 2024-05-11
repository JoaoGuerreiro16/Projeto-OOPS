package Tests;

import Game.ModelLayer.Celula;
import Game.ModelLayer.EstadoCelula;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CelulaTest {

    @Test
    public void constructorTest()
    {
      Celula c = new Celula(4,5);
      c.setEstado(EstadoCelula.HEAD);
      assertEquals("Celula (linha: 4,coluna: 5,estado: HEAD)",c.toString());
    }

    @Test
    public void setEstadoTest()
    {
        Celula c = new Celula(4,5);
        c.setEstado(EstadoCelula.TAIL);
        assertEquals(EstadoCelula.TAIL,c.getEstado());
    }
}
