import	static	org.junit.jupiter.api.Assertions.*; 
import	org.junit.jupiter.api.Test; 

public class RetaTest {

    @Test
    public void testGetP1() {
        Ponto ponto1 = new Ponto(1, 2);
        Ponto ponto2 = new Ponto(3, 4);
        Reta reta = new Reta(ponto1, ponto2);
        assertEquals(ponto1, reta.getP1());
    }

    @Test
    public void testSetP1() {
        Ponto ponto1 = new Ponto(1, 2);
        Ponto ponto2 = new Ponto(3, 4);
        Reta reta = new Reta(ponto1, ponto2);

        Ponto novoPonto = new Ponto(5, 6);
        reta.setP1(novoPonto);

        assertEquals(novoPonto, reta.getP1());
    }

    @Test
    public void testGetP2() {
        Ponto ponto1 = new Ponto(1, 2);
        Ponto ponto2 = new Ponto(3, 4);
        Reta reta = new Reta(ponto1, ponto2);
        assertEquals(ponto2, reta.getP2());
    }

    @Test
    public void testSetP2() {
        Ponto ponto1 = new Ponto(1, 2);
        Ponto ponto2 = new Ponto(3, 4);
        Reta reta = new Reta(ponto1, ponto2);

        Ponto novoPonto = new Ponto(5, 6);
        reta.setP2(novoPonto);

        assertEquals(novoPonto, reta.getP2());
    }

}
