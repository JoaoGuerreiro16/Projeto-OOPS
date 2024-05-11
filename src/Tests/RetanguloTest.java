package Tests;

import	static	org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import	org.junit.jupiter.api.Test;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Retangulo;

public class RetanguloTest {
    @Test
    public void testConstrutor0() {
        String input = "4 0 4 2 0 2 2 0";
        new Retangulo(input);
    }

    @Test
    public void testConstrutor1() {
        String input = "4 0 4 2 0 2";
        new Retangulo(input);
    }

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

        assertEquals("Retangulo: [(4,2), (4,4), (0,4), (0,2)]",r.rotacao(90,r.calcularCentro()).toString());
    }
    @Test
    public void testTranslate() {
        String input1 = "1 1 3 1 3 5 1 5";
        assertEquals("Retangulo: [(3,3), (5,3), (5,7), (3,7)]",new Retangulo(input1).translacao(2,2).toString());
        String input2 = "1 1 3 1 3 5 1 5";
        assertEquals("Retangulo: [(2,2), (4,2), (4,6), (2,6)]", new Retangulo(input2).translacao(1,1).toString());
    }

    @Test
    public void testTranslateCentroide() {
        String input1 = "1 1 3 1 3 5 1 5";
        assertEquals("Retangulo: [(5,2), (7,2), (7,6), (5,6)]",new Retangulo(input1).translacaoCentroide(6,4).toString());
        String input2 = "1 1 3 1 3 5 1 5";
        assertEquals("Retangulo: [(6,0), (8,0), (8,4), (6,4)]", new Retangulo(input2).translacaoCentroide(7,2).toString());
    }

}
