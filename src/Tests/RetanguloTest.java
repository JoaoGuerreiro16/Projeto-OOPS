package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Game.ModelLayer.Ponto;
import Game.ModelLayer.Retangulo;
import java.util.ArrayList;

public class RetanguloTest {

    @Test
    public void testConstrutorValido() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(0, 4));
        Retangulo retangulo = new Retangulo(pontos);
        assertNotNull(retangulo);
        assertEquals(4, retangulo.getPontos().size());
        assertEquals("Retangulo: " + pontos.toString(), retangulo.toString());
    }


    @Test
    public void testRotacao() {
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
    public void testTranslacao() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(0, 4));
        Retangulo retangulo = new Retangulo(pontos);
        Retangulo transladado = retangulo.translacao(5, 5);
        assertNotNull(transladado);
        assertEquals(4, transladado.getPontos().size());
    }

    @Test
    public void testTranslacaoCentroide() {
        ArrayList<Ponto> pontos = new ArrayList<>();
        pontos.add(new Ponto(0, 0));
        pontos.add(new Ponto(4, 0));
        pontos.add(new Ponto(4, 4));
        pontos.add(new Ponto(0, 4));
        Retangulo retangulo = new Retangulo(pontos);
        Retangulo transladado = retangulo.translacaoCentroide(5, 5);
        assertNotNull(transladado);
        assertEquals(4, transladado.getPontos().size());
    }
}