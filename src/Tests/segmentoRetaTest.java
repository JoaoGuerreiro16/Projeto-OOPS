package Tests;
import org.junit.jupiter.api.Test;
import Game.Ponto;
import Game.SegmentoReta;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoRetaTest {

    @Test
    public void testeConstrutorComPontosValidos() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        SegmentoReta segmento = new SegmentoReta(ponto1, ponto2);
        assertNotNull(segmento);
        assertEquals(ponto1, segmento.getPonto1());
        assertEquals(ponto2, segmento.getPonto2());
    }

    @Test
    public void testeSegmentosIntersetam() {
        // Criar dois segmentos de reta que se cruzam
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(5, 1);
        Ponto ponto3 = new Ponto(3, 0);
        Ponto ponto4 = new Ponto(3, 3);
        SegmentoReta segmento1 = new SegmentoReta(ponto1, ponto2);
        SegmentoReta segmento2 = new SegmentoReta(ponto3, ponto4);

        // Verificar se os segmentos se cruzam
        assertTrue(segmento1.arestasCruzam(segmento2));
    }

    @Test
    public void testeSegmentosNaoIntersetam() {
        // Criar dois segmentos de reta que não se cruzam
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 4);
        Ponto ponto3 = new Ponto(6, 4);
        Ponto ponto4 = new Ponto(6, 1);
        SegmentoReta segmento1 = new SegmentoReta(ponto1, ponto2);
        SegmentoReta segmento2 = new SegmentoReta(ponto3, ponto4);

        // Verificar se os segmentos não se cruzam
        assertFalse(segmento1.arestasCruzam(segmento2));
    }

}
