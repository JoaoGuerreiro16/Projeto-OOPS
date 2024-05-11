package Tests;

import	static	org.junit.jupiter.api.Assertions.*;

import Game.ModelLayer.Ponto;
import Game.ModelLayer.Triangulo;
import	org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TrianguloTest
{
    @Test
    public void testConstrutor0() {
        String input = "2 0 4 0 6 0";
        new Triangulo(input);
    }

    @Test
    public void testConstrutor1() {
        String input = "2 0 4 0 6 0 6 0";
        new Triangulo(input);
    }

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
    public void testRotation() {
        String input = "1 1 3 1 2 4";
        Triangulo t = new Triangulo(input);

        assertEquals("Triangulo: [(3,1), (3,3), (0,2)]", t.rotacao(90, t.calcularCentro()).toString());
    }

    @Test
    public void testTranslate() {
        String input1 = "2 1 4 1 3 4";
        assertEquals("Triangulo: [(2,1), (4,1), (3,4)]", new Triangulo(input1).translacao(0,0).toString());
        String input2 = "5 5 8 5 8 7";
        assertEquals("Triangulo: [(8,12), (11,12), (11,14)]", new Triangulo(input2).translacao(3,7).toString());
    }
    @Test
    public void testTranslateCentroide() {
        String input1 = "2 1 4 1 4 3";
        assertEquals("Triangulo: [(6,1), (8,1), (8,3)]", new Triangulo(input1).translacaoCentroide(7,1).toString());
        assertEquals("Triangulo: [(2,4), (4,4), (4,6)]", new Triangulo(input1).translacaoCentroide(3,4).toString());
    }

}