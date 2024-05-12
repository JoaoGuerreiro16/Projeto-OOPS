package Tests;



import Game.ModelLayer.Pontuacao;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class PontuacaoTest {

    private Pontuacao pontuacao;

    @BeforeEach
    public void setUp() {
        pontuacao = Pontuacao.getInstance();
    }
    @Test
    public void testGetPontuacao() {
        setUp();
        assertEquals(0, pontuacao.getPontuacao());
        Pontuacao.resetInstance();
    }

    @Test
    public void testSetPontuacao() {
        setUp();
        pontuacao.setPontuacao(100);
        assertEquals(100, pontuacao.getPontuacao());
        Pontuacao.resetInstance();
    }

    @Test
    public void testIncrementaPontuacao() {
        setUp();
        pontuacao.incrementaPontuacao(50);
        assertEquals(50, pontuacao.getPontuacao());
        Pontuacao.resetInstance();
    }

    @Test
    public void testIncrementaPontuacaoVariasVezes() {
        setUp();
        pontuacao.incrementaPontuacao(10);
        pontuacao.incrementaPontuacao(20);
        pontuacao.incrementaPontuacao(30);
        assertEquals(60, pontuacao.getPontuacao());
        Pontuacao.resetInstance();
    }
    @Test
    public void testPontuacaoMaxima() {
        setUp();
        pontuacao.pontuacaoMaxima();
        assertEquals(Integer.MAX_VALUE, pontuacao.getPontuacao());
        Pontuacao.resetInstance();

    }

}
