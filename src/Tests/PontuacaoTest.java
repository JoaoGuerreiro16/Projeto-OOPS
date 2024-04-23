package Tests;

import Game.Pontuacao;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PontuacaoTest {
    @Test
    public void testGetPontuacao() {
        Pontuacao pontuacao = new Pontuacao();
        assertEquals(0, pontuacao.getPontuacao());
    }

    @Test
    public void testSetPontuacao() {
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.setPontuacao(100);
        assertEquals(100, pontuacao.getPontuacao());
    }

    @Test
    public void testIncrementaPontuacao() {
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.incrementaPontuacao(50);
        assertEquals(50, pontuacao.getPontuacao());
    }

    @Test
    public void testIncrementaPontuacaoVariasVezes() {
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.incrementaPontuacao(10);
        pontuacao.incrementaPontuacao(20);
        pontuacao.incrementaPontuacao(30);
        assertEquals(60, pontuacao.getPontuacao());
    }
}
