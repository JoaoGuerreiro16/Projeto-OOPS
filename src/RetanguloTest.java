import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import	org.junit.jupiter.api.Test; 

public class RetanguloTest {
    @Test
     public void testToString()
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0,0));
        pontos.add(new Ponto(3,0));
        pontos.add(new Ponto(3,3));
        pontos.add(new Ponto(0,2));

        Retangulo r = new Retangulo(pontos);
        assertEquals("Retangulo: [(0,0), (3,0), (3,3), (0,2)]",r.toString());   
    }

    @Test
    public void testRotation()
    {
        String input = "1 1 3 1 3 5 1 5";
        Retangulo r = new Retangulo(input);

        assertEquals("Retangulo: [(4,2), (4,4), (0,4), (0,2)]",r.poligonRotation(90,r.calcularCentro()).toString());
    }
}
