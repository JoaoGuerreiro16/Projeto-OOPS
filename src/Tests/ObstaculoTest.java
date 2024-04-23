package Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import Game.Obstaculo;
import Game.Poligono;
import Game.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ObstaculoTest {
    private Obstaculo obstaculo;

    @BeforeEach
    public void setUp() {
        ArrayList<Ponto> pontos = new ArrayList<>(List.of(
                new Ponto(0, 0),
                new Ponto(0, 2),
                new Ponto(2, 2)
        ));
        Poligono poligono = new Poligono(pontos);
        obstaculo = new Obstaculo(poligono);
    }

    @Test
    public void testRotacaoPadrao() {
        obstaculo.rotacao(90); // Rotaciona em 90 graus
        assertEquals("(0, 0), (-2, 0), (-2, 2)", obstaculo.toString());
    }

    @Test
    public void testRotacaoCentroide() {
        obstaculo.rotacaoCentroide(90, new Ponto(1, 1)); // Rotaciona em 90 graus em torno do centroide
        assertEquals("(1, 2), (1, 0), (-1, 0)", obstaculo.toString());
    }
}
