import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import	org.junit.jupiter.api.Test;

public class QuadradoTest {
  @Test
  public void testToString()
  {
      ArrayList<Ponto> pontos = new ArrayList<>();
      pontos.add(new Ponto(0,0));
      pontos.add(new Ponto(0,3));
      pontos.add(new Ponto(3,3));
      pontos.add(new Ponto(3,0));

      Quadrado q= new Quadrado(pontos);
      assertEquals("Quadrado: [(0,0), (0,3), (3,3), (3,0)]",q.toString());   
  }

    @Test
    public void testRotation() {
        String input = "1 1 3 1 3 3 1 3";
        Quadrado q = new Quadrado(input);

        assertEquals("Quadrado: [(3,1), (3,3), (1,3), (1,1)]", q.poligonRotation(90, q.calcularCentro()).toString());
    }

}
