package Tests;

import Game.Obstaculo;
import Game.Poligono;
import Game.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObstaculoTest {
    private Obstaculo obstaculo;

    @BeforeEach
    public void setUp() {
        ArrayList<Ponto> pontos = new ArrayList<>(List.of(
                new Ponto(12, 12),
                new Ponto(13, 14),
                new Ponto(14, 12)
        ));
        Poligono poligono = new Poligono(pontos);
        obstaculo = new Obstaculo(poligono, false, 0, new Ponto(0, 0));
    }

    @Test
    public void testRotacaoPadrao() {
        obstaculo.rotacao(90); // Rotaciona em 90 graus
        assertEquals("Obstaculo{forma:Poligono de 3 vertices: [(12,12), (13,14), (14,12)],é dinâmico:false,ângulo rotação:0,ponto rotação:(0,0)}", obstaculo.toString()); // O resultado depende de como o ajuste foi aplicado
    }

    @Test
    public void testRotacaoCentroide() {
        obstaculo.rotacaoCentroide(90, new Ponto(12, 12)); // Rotaciona em 90 graus em torno do centroide
        assertEquals("Obstaculo{forma:Poligono de 3 vertices: [(1,1), (1,3), (3,3)],é dinâmico:false,ângulo rotação:0,ponto rotação:(0,0)}", obstaculo.toString()); // O resultado ajustado para manter todos os pontos positivos
    }
}
