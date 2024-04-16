import	static	org.junit.jupiter.api.Assertions.*; 
import	org.junit.jupiter.api.Test; 

import java.util.ArrayList;

public class TrianguloTest 
{
    @Test
     public void testToString()
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0,0));
        pontos.add(new Ponto(2,0));
        pontos.add(new Ponto(1,2));

        Triangulo triangulo = new Triangulo(pontos);
        assertEquals("Triangulo: [(0,0), (2,0), (1,2)]",triangulo.toString());
        
    }

    @Test
    public void testTriangleRotation() {
        String input = "1 1 3 1 2 4";
        Triangulo t = new Triangulo(input);

        assertEquals("Triangulo: [(3,1), (3,3), (0,2)]", t.poligonRotation(90, t.calcularCentro()).toString());
    }


}
