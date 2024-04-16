import	static	org.junit.jupiter.api.Assertions.*; 
import	org.junit.jupiter.api.Test; 

public class segmentoRetaTest {

    @Test
    public void testSegmentoRetaComPontosDiferentes() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(2, 2);

        assertDoesNotThrow(() -> new SegmentoReta(ponto1, ponto2));
    }

    @Test
    public void testSegmentoRetaComPontosIguais() {
        Ponto ponto1 = new Ponto(1, 1);

        assertThrows(IllegalArgumentException.class, () -> new SegmentoReta(ponto1, ponto1));
    }

    @Test
    public void testProdutoVetorial() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(2, 2);
        Ponto ponto3 = new Ponto(3, 3);

        SegmentoReta segmentoReta = new SegmentoReta(ponto1, ponto2);

        assertEquals(0.0, segmentoReta.produtoVetorial(ponto1, ponto2, ponto3));
    }

    @Test
    public void testArestasNaoCruzam() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(2, 2);
        Ponto ponto3 = new Ponto(1, 0);
        Ponto ponto4 = new Ponto(3, 2);

        SegmentoReta segmentoReta1 = new SegmentoReta(ponto1, ponto2);
        SegmentoReta segmentoReta2 = new SegmentoReta(ponto3, ponto4);

        assertFalse(segmentoReta1.arestasCruzam(segmentoReta2));
    }

    @Test
    public void testArestasCruzam() {
        Ponto ponto1 = new Ponto(0, 0);
        Ponto ponto2 = new Ponto(1, 1);
        Ponto ponto3 = new Ponto(1, 0);
        Ponto ponto4 = new Ponto(0, 1);

        SegmentoReta segmentoReta1 = new SegmentoReta(ponto1, ponto2);
        SegmentoReta segmentoReta2 = new SegmentoReta(ponto3, ponto4);

        assertTrue(segmentoReta1.arestasCruzam(segmentoReta2));
}
}