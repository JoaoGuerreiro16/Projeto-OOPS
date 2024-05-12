package Tests;

import	static	org.junit.jupiter.api.Assertions.*;

import Game.ModelLayer.Ponto;
import Game.ModelLayer.Triangulo;
import	org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TrianguloTest
{
    @Test
    public void testConstrutorValido() {
        ArrayList<Ponto> pontosValidos = new ArrayList<>();
        pontosValidos.add(new Ponto(0,0));
        pontosValidos.add(new Ponto(2,0));
        pontosValidos.add(new Ponto(1,2));
        assertDoesNotThrow(() -> new Triangulo(pontosValidos));
    }


    @Test
    public void testToString()
    {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0,0));
        pontos.add(new Ponto(2,0));
        pontos.add(new Ponto(1,2));

        Triangulo triangulo = new Triangulo(pontos);
        assertEquals("Triangulo: [(0.0,0.0), (2.0,0.0), (1.0,2.0)]",triangulo.toString());

    }

    @Test
    public void testRotacao() {
        String input = "1 1 3 1 2 4";
        Triangulo t = new Triangulo(input);

        assertEquals("Triangulo: [(3.0,1.0), (3.0,3.0), (0.0,2.0)]", t.rotacao(90, t.calcularCentro()).toString());
    }

    @Test
    public void testTranslacao() {
        String input1 = "2 1 4 1 3 4";
        assertEquals("Triangulo: [(2.0,1.0), (4.0,1.0), (3.0,4.0)]", new Triangulo(input1).translacao(0,0).toString());
        String input2 = "5 5 8 5 8 7";
        assertEquals("Triangulo: [(8.0,12.0), (11.0,12.0), (11.0,14.0)]", new Triangulo(input2).translacao(3,7).toString());
    }
    @Test
    public void testTranslacaoCentroide() {

        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(2, 1));
        pontos.add(new Ponto(4, 1));
        pontos.add(new Ponto(4, 3));
        Triangulo triangulo = new Triangulo(pontos);
        Triangulo trianguloTransladado1 = triangulo.translacaoCentroide(7, 1);
        Ponto centroide1 = trianguloTransladado1.calcularCentro();
        assertEquals(7, centroide1.getX(), 0.01);
        assertEquals(1, centroide1.getY(), 0.01);

    }


}