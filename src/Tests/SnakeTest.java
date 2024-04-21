package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Game.Quadrado;
import Game.Snake;

import java.util.ArrayList;

public class SnakeTest {

    @Test
    public void constructorTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(2,2), (3,2), (3,3), (2,3)], Quadrado: [(1,2), (2,2), (2,3), (1,3)]]",s.toString());

    }

    @Test
    public void translacaoxTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.translacao(1,0).toString());
    }

    @Test
    public void translacaoyTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(2,3), (3,3), (3,4), (2,4)], Quadrado: [(1,3), (2,3), (2,4), (1,4)]]",s.translacao(0,1).toString());
    }
}