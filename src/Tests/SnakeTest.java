package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Game.Quadrado;
import Game.Snake;

import java.util.ArrayList;
import java.util.LinkedList;

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

    @Test
    public void movimentoSnakeRightTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.movimentoSnake(1,0).toString());

    }

    @Test
    public void movimentoSnakeLeftTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();

        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));

        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(1,2), (2,2), (2,3), (1,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.movimentoSnake(-1,0).toString());
    }

    @Test
    public void movimentoSnakeUpTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(3,3), (4,3), (4,4), (3,4)], Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.movimentoSnake(0,1).toString());
    }

    @Test
    public void movimentoSnakeDownTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(3,1), (4,1), (4,2), (3,2)], Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.movimentoSnake(0,-1).toString());
    }

    @Test
    public void cresceSnakeTest()
    {
        ArrayList<Quadrado> Quadrados = new LinkedList<>();
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));
        Snake s = new Snake(Quadrados);

        assertEquals("Snake: [Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]",s.cresceSnake().toString());

    }

    @Test
    public void intercetaSnakeFalseTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("4 2 5 2 5 3 4 3"));
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        assertFalse(s.intercetaSnake());
    }

    @Test
    public void intercetaSnakeTrueTest()
    {
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("4 2 5 2 5 3 4 3"));
        Quadrados.add(new Quadrado("3 2 4 2 4 3 3 3"));
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);

        s.movimentoSnake(0,1);
        s.movimentoSnake(-1,0);
        s.movimentoSnake(0,-1);


        assertTrue(s.intercetaSnake());
    }
}
