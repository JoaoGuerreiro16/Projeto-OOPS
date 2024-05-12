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

        assertEquals(0, pontuacao.getPontuacao());
    }

    @Test
    public void testSetPontuacao() {

        pontuacao.setPontuacao(100);
        assertEquals(100, pontuacao.getPontuacao());
    }

    @Test
    public void testIncrementaPontuacao() {

        pontuacao.incrementaPontuacao(50);
        assertEquals(50, pontuacao.getPontuacao());
    }

    @Test
    public void testIncrementaPontuacaoVariasVezes() {

        pontuacao.incrementaPontuacao(10);
        pontuacao.incrementaPontuacao(20);
        pontuacao.incrementaPontuacao(30);
        assertEquals(60, pontuacao.getPontuacao());
    }
    @Test
    public void testPontuacaoMaxima() {
        pontuacao.pontuacaoMaxima();
        assertEquals(Integer.MAX_VALUE, pontuacao.getPontuacao());
    }

}
