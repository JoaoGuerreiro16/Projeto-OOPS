
package Tests;
import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import Game.ModelLayer.*;
import	org.junit.jupiter.api.Test;

public class QuadradoTest {
    @Test
    public void testConstrutor() {
        String input = "1 1 3 1 3 3 1 3";
        new Quadrado(input);
    }

    @Test
    public void testToString()
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0,0));
        pontos.add(new Ponto(0,3));
        pontos.add(new Ponto(3,3));
        pontos.add(new Ponto(3,0));

        Quadrado q= new Quadrado(pontos);
        assertEquals("Quadrado: [(0.0,0.0), (0.0,3.0), (3.0,3.0), (3.0,0.0)]",q.toString());
    }

    @Test
    public void testRotacao() {
        String input = "1 1 3 1 3 3 1 3";
        Quadrado q = new Quadrado(input);

        assertEquals("Quadrado: [(3.0,1.0), (3.0,3.0), (1.0,3.0), (1.0,1.0)]", q.rotacao(90, q.calcularCentro()).toString());
    }

    @Test
    public void testTranslacao() {

        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(0, 4));
        Retangulo retangulo = new Retangulo(pontos);
        Ponto centroide = retangulo.calcularCentro();
        Retangulo rotacionado = retangulo.rotacao(90, centroide);
        assertNotNull(rotacionado);
        assertEquals(4, rotacionado.getPontos().size());
    }

    @Test
    public void testTranslacaoCentroide() {
        String input1 = "0 0 2 0 2 2 0 2";
        assertEquals("Quadrado: [(3.0,2.0), (5.0,2.0), (5.0,4.0), (3.0,4.0)]", new Quadrado(input1).translacaoCentroide(4,3).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        assertEquals("Quadrado: [(6.0,1.0), (8.0,1.0), (8.0,3.0), (6.0,3.0)]", new Quadrado(input2).translacaoCentroide(7,2).toString());
    }

    @Test
    public void containsPontoTest() {
        Quadrado quadrado = new Quadrado("0 0 4 0 4 4 0 4");
        assertTrue(quadrado.containsPonto(new Ponto(2, 2)));
        assertTrue(quadrado.containsPonto(new Ponto(0, 0)));
        assertFalse(quadrado.containsPonto(new Ponto(5, 5)));
    }

    @Test
    public void containsQuadradoTest() {
        Quadrado base = new Quadrado("0 0 10 0 10 10 0 10");
        Quadrado dentro = new Quadrado("3 3 5 3 5 5 3 5");
        Quadrado interceta = new Quadrado("8 8 11 8 11 11 8 11");
        Quadrado fora = new Quadrado("12 12 17 12 17 17 12 17");

        assertTrue(base.containsQuadrado(dentro));
        assertFalse(base.containsQuadrado(interceta));
        assertFalse(base.containsQuadrado(fora) );
    }

    @Test
    public void containsCircle() {
        Quadrado quadrado = new Quadrado("0 0 10 0 10 10 0 10");
        Circulo dentro = new Circulo(new Ponto(5, 5), 2);
        Circulo interceta = new Circulo(new Ponto(5, 5), 7);
        Circulo fora = new Circulo(new Ponto(20, 20), 5);

        assertTrue(quadrado.containsCircle(dentro));
        assertFalse(quadrado.containsCircle(interceta));
        assertFalse(quadrado.containsCircle(fora));
    }

}
