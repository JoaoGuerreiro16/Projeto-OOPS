import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import	org.junit.jupiter.api.Test;

public class PoligonoTest {
    @Test
    public void testPerimetroPoligono() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        pontos.add(new Ponto(0, 4));

        Poligono poligono = new Poligono(pontos);

        assertEquals(14, poligono.perimetro());
    }jkjbn


    @Test
    public void testToString() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(3, 0));
        pontos.add(new Ponto(3, 4));
        pontos.add(new Ponto(0, 4));

        Poligono poligono = new Poligono(pontos);

        assertEquals("Poligono de 4 vértices: [(0,0), (3,0), (3,4), (0,4)]",poligono.toString());
    }

    @Test
    public void testDuplicate()
    {
        ArrayList<Ponto> pontos1 = new ArrayList<>();
        pontos1.add(new Ponto(0, 0));
        pontos1.add(new Ponto(3, 0));
        pontos1.add(new Ponto(3, 3));
        pontos1.add(new Ponto(0, 3));

        ArrayList<Ponto> pontos2 = new ArrayList<>();
        pontos2.add(new Ponto(3, 3));
        pontos2.add(new Ponto(0, 3));
        pontos2.add(new Ponto(0, 0));
        pontos2.add(new Ponto(3, 0));

        Quadrado q = new Quadrado(pontos1);
        Poligono p = new Poligono(pontos2);

        assertEquals(true,q.equals(p));
    }

    @Test
    public void testPoligonoRotation()
    {
        String input = "4 1 1 3 1 3 5 1 5";
        Poligono p = new Poligono(input);

        assertEquals("Poligono de 4 vertices: [(4,2), (4,4), (0,4), (0,2)]",p.poligonRotation(90,p.calcularCentro()).toString());
    }
}
