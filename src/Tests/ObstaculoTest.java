package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Game.ModelLayer.Obstaculo;
import Game.ModelLayer.Poligono;
import Game.ModelLayer.Ponto;

import java.util.ArrayList;

public class ObstaculoTest {

    @Test
    public void testConstrutorObstaculo() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(1, 0));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(0, 1));
        Poligono poligono = new Poligono(pontos);

        Obstaculo obstaculo = new Obstaculo(poligono, true, 90, new Ponto(0.5, 0.5));
        assertNotNull(obstaculo);
        assertTrue(obstaculo.isDinamico());
        assertEquals(90, obstaculo.getAnguloRotacao());
        assertEquals(new Ponto(0.5, 0.5), obstaculo.getPontoRotacao());
    }
    @Test
    public void testRotacaoObstaculoNaoDinamico() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(1, 0));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(0, 1));
        Poligono poligono = new Poligono(pontos);
        Ponto centro = new Ponto(0.5, 0.5);
        Obstaculo obstaculo = new Obstaculo(poligono, false, 90, centro);
        obstaculo.rotacao(90, centro);
        Poligono rotatedPolygon = obstaculo.getPoligono();
        assertEquals(poligono, rotatedPolygon, "O polígono não deve mudar em um obstáculo não dinâmico");
        assertEquals(poligono.getPontos().get(0), new Ponto(0, 0));
        assertEquals(poligono.getPontos().get(1), new Ponto(1, 0));
        assertEquals(poligono.getPontos().get(2), new Ponto(1, 1));
        assertEquals(poligono.getPontos().get(3), new Ponto(0, 1));
    }

    @Test
    public void testRotacaoObstaculoDinamico() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(1, 0));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(0, 1));
        Poligono poligono = new Poligono(pontos);
        Ponto centro = new Ponto(1, 1);

        Obstaculo obstaculo = new Obstaculo(poligono, true, 90, centro);
        obstaculo.rotacao(90, centro);

        Poligono rotatedPolygon = obstaculo.getPoligono();
        assertEquals(new Ponto(2.0, 0.0), rotatedPolygon.getPontos().get(0));
        assertEquals(new Ponto(2.0, 1.0), rotatedPolygon.getPontos().get(1));
        assertEquals(new Ponto(1.0, 1.0), rotatedPolygon.getPontos().get(2));
        assertEquals(new Ponto(1.0, 0.0), rotatedPolygon.getPontos().get(3));
    }


    @Test
    public void testToString() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(1, 0));
        pontos.add(new Ponto(1, 1));
        pontos.add(new Ponto(0, 1));
        Poligono poligono = new Poligono(pontos);

        Obstaculo obstaculo = new Obstaculo(poligono, false, 0, new Ponto(0.5, 0.5));
        String expectedString = "Obstaculo{forma:Poligono de 4 vertices: [(0.0,0.0), (1.0,0.0), (1.0,1.0), (0.0,1.0)],é dinâmico:false,ângulo rotação:0,ponto rotação:(0.5,0.5)}";
        assertEquals(expectedString, obstaculo.toString());
    }
}