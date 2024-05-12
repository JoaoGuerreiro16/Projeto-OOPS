package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Game.ModelLayer.Poligono;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Triangulo;

import java.util.ArrayList;
import java.util.List;
class PoligonoTest {

    @Test
    public void testeConstrutorComPontosValidos() {
        ArrayList<Ponto> pontos = new ArrayList<>(List.of(
                new Ponto(0, 0),
                new Ponto(0, 2),
                new Ponto(2, 2)
        ));
        Poligono poligono = new Poligono(pontos);
        assertNotNull(poligono);
        assertEquals(pontos, poligono.getPontos());
    }

    @Test
    public void testeCalculoPerimetro() {
        ArrayList<Ponto> pontos = new ArrayList<>(List.of(
                new Ponto(0, 0),
                new Ponto(0, 2),
                new Ponto(2, 2),
                new Ponto(2, 0)
        ));
        Poligono poligono = new Poligono(pontos);
        assertEquals(8, poligono.perimetro(pontos));
    }

    @Test
    public void testToString0() {
        String input1 = "4 5 5 8 6 8 7 5 7";
        assertEquals("Poligono de 4 vertices: [(5,5), (8,6), (8,7), (5,7)]", new Poligono(input1).toString());
        String input2 = "3 9 3 7 1 9 1";
        assertEquals("Poligono de 3 vertices: [(9,3), (7,1), (9,1)]", new Poligono(input2).toString());
        String input3 = "4 1 2 5 6 8 7 12 14";
        assertEquals("Poligono de 4 vertices: [(1,2), (5,6), (8,7), (12,14)]", new Poligono(input3).toString());
    }

    @Test
    public void testEquals() {
        assertTrue(new Poligono("3 9 3 7 1 9 1").equals(new Triangulo("7 1 9 1 9 3")));
        assertTrue(new Quadrado("1 1 1 4 4 4 4 1").equals(new Quadrado("4 1 1 1 1 4 4 4")));
        assertFalse(new Poligono("4 1 1 3 2 5 2 7 1").equals(new Poligono("4 1 2 2 3 4 4 7 1")));
        assertFalse(new Triangulo("0 0 3 3 6 0").equals(new Triangulo("1 1 3 3 5 1")));
    }

    @Test
    public void testCentroide() {
        String input1 = "4 5 5 8 6 8 7 5 7";
        Poligono poligono1 = new Poligono(input1);
        assertEquals(6.5, poligono1.calcularCentro().getX());
        assertEquals(6.25, poligono1.calcularCentro().getY());

        String input2 = "3 9 3 7 1 9 1";
        Poligono poligono2 = new Poligono(input2);
        assertEquals(8.333333333333334, poligono2.calcularCentro().getX());
        assertEquals(1.6666666666666667, poligono2.calcularCentro().getY());

        String input3 = "4 1 2 5 6 8 7 12 14";
        Poligono poligono3 = new Poligono(input3);
        assertEquals(6.5, poligono3.calcularCentro().getX());
        assertEquals(7.25, poligono3.calcularCentro().getY());
    }

    @Test
    public void testRotatao()
    {
        String input = "4 1 1 3 1 3 5 1 5";
        Poligono p = new Poligono(input);

        assertEquals("Poligono de 4 vertices: [(4,2), (4,4), (0,4), (0,2)]",p.rotacao(90,p.calcularCentro()).toString());
    }

    @Test
    public void testTranslacao() {
        String input1 = "4 1 2 5 6 8 7 12 14";
        assertEquals("Poligono de 4 vertices: [(0,5), (4,9), (7,10), (11,17)]",new Poligono(input1).translacao(-1, 3).toString());
        String input2 = "3 2 2 3 4 4 2";
        assertEquals("Poligono de 3 vertices: [(5,5), (6,7), (7,5)]", new Poligono(input2).translacao(3,3).toString());
    }

    @Test
    public void testTranslacaoCentroide() {
        String input1 = "4 1 3 1 1 5 1 5 3";
        assertEquals("Poligono de 4 vertices: [(6,3), (6,1), (10,1), (10,3)]",new Poligono(input1).translacao(8, 2).toString());
        String input2 = "3 2 2 4 4 4 2";
        assertEquals("Poligono de 3 vertices: [(2,4), (4,6), (4,4)]", new Poligono(input2).translacaoCentroide(4,5).toString());
    }

    @Test
    public void testContainsPonto() {
        Poligono quadrado = new Poligono("4 0 0 4 0 4 4 0 4");
        Ponto pontoDentro = new Ponto(2, 2);
        assertTrue( quadrado.containsPonto(pontoDentro));
        Ponto pontoInterseta = new Ponto(4, 2);
        assertTrue( quadrado.containsPonto(pontoInterseta));
        Ponto pontoFora = new Ponto(5, 5);
        assertFalse("Ponto fora deve retornar false", quadrado.containsPonto(pontoFora));
        Ponto pontoCanto = new Ponto(0, 0);
        assertTrue( quadrado.containsPonto(pontoCanto));
    }


}