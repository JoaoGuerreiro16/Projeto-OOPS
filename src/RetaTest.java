
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetaTest {

    @Test
    public void testeConstrutorComPontosValidos() {
        Ponto ponto1 = new Ponto(1, 1);
        Ponto ponto2 = new Ponto(3, 3);
        Reta reta = new Reta(ponto1, ponto2);
        assertNotNull(reta);
        assertEquals(ponto1, reta.getPonto1());
        assertEquals(ponto2, reta.getPonto2());
    }

}
