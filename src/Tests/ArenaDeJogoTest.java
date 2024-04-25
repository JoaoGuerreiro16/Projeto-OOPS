package Tests;

import Game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArenaDeJogoTest {

    @Test
    public void addSnakeTest()
    {
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "circulo");
        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake s = new Snake(Quadrados);
        arena.addSnake(s);
        Assertions.assertNotNull(arena.getSnake());
    }
    @Test
    public void addComidaQuadradoTest()
    {
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "quadrado");
        arena.addComida();
        Assertions.assertNotNull(arena.getComidaAtual());
    }

    @Test
    public void addComidaCirculoTest()
    {
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "circulo");
        arena.addComida();
        Assertions.assertNotNull(arena.getComidaAtual());
    }

    @Test
    public void addObstaculo()
    {
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "Quadrado");
        arena.addObstaculos();
        Assertions.assertNotNull(arena.getObstaculos());
    }

    @Test
    public void intercetaObstaculoTrueTest()
    {

        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "quadrado");

        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake snake = new Snake(Quadrados);

        arena.addSnake(snake);

        Poligono poligono = new Poligono("2 2 3 2 3 3 2 3");

        Obstaculo obstaculo = new Obstaculo(poligono);
        arena.addObstaculo(obstaculo);

        boolean interceta = arena.intercetaObstaculo();

        assertTrue(interceta);

    }

    @Test
    public void intercetaObstaculoFalseTest()
    {

        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "quadrado");

        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake snake = new Snake(Quadrados);

        arena.addSnake(snake);

        Poligono poligono = new Poligono("5 5 6 5 6 6 5 6");

        Obstaculo obstaculo = new Obstaculo(poligono);
        arena.addObstaculo(obstaculo);

        boolean interceta = arena.intercetaObstaculo();

        assertFalse(interceta);

    }

    @Test
    public void intercetaParede()
    {
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, "quadrado");

        ArrayList<Quadrado> Quadrados = new ArrayList<>();
        Quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));
        Quadrados.add(new Quadrado("1 2 2 2 2 3 1 3"));
        Snake snake = new Snake(Quadrados);

        arena.addSnake(snake);

        boolean interceta = arena.intercetaParede();

        assertFalse(interceta);

    }


}
