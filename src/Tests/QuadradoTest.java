
package Tests;
import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import	org.junit.jupiter.api.Test;
import Game.Poligono;
import Game.Ponto;
import Game.Quadrado;

public class QuadradoTest {
    @Test
    public void testConstrutor0() {
        String input = "1 0 1 1 5 1 5 0";
        new Quadrado(input);
    }

    @Test
    public void testConstrutor1() {
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
        assertEquals("Quadrado: [(0,0), (0,3), (3,3), (3,0)]",q.toString());
    }

    @Test
    public void testRotation() {
        String input = "1 1 3 1 3 3 1 3";
        Quadrado q = new Quadrado(input);

        assertEquals("Quadrado: [(3,1), (3,3), (1,3), (1,1)]", q.rotacao(90, q.calcularCentro()).toString());
    }

    @Test
    public void testTranslate() {
        String input1 = "0 0 2 0 2 2 0 2";
        assertEquals("Quadrado: [(2,0), (4,0), (4,2), (2,2)]", new Poligono(input1).translacao(2,0).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        assertEquals("Quadrado: [(1,6), (3,6), (3,8), (1,8)]", new Poligono(input2).translacao(0,5).toString());
    }

    @Test
    public void testTranslateCentroide() {
        String input1 = "0 0 2 0 2 2 0 2";
        assertEquals("Quadrado: [(3,2), (5,2), (5,4), (3,4)]", new Quadrado(input1).translacaoCentroide(4,3).toString());
        String input2 = "1 1 3 1 3 3 1 3";
        assertEquals("Quadrado: [(6,1), (8,1), (8,3), (6,3)]", new Quadrado(input2).translacaoCentroide(7,2).toString());
    }

}
