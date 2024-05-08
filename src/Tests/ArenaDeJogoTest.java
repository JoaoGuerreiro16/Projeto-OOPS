package Tests;

import Game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArenaDeJogoTest {

    @Test
    public void addSnakeTest() {
        LinkedList<Quadrado> quadrados = new LinkedList<>();
        quadrados.add(new Quadrado("2 2 3 2 3 3 2 3"));  // Usando string para vértices
        Snake s = new Snake(quadrados.getFirst());
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, s, comidas, obstaculos);
        Assertions.assertNotNull(arena.getSnake());
    }

    @Test
    public void addComidaQuadradoTest() {
        Snake s = new Snake(new Quadrado("0 0 1 0 1 1 0 1"));
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, s, comidas, obstaculos);
        Comida comida = new ComidaQuadrado(new Quadrado("10 10 11 10 11 11 10 11"), 20); // Usando string para vértices
        arena.getComida().add(comida);
        Assertions.assertFalse(arena.getComida().isEmpty());
    }

    @Test
    public void addObstaculoTest() {
        Snake s = new Snake(new Quadrado("0 0 1 0 1 1 0 1"));
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, s, comidas, obstaculos);
        Obstaculo obstaculo = new Obstaculo(new Quadrado("10 10 11 10 11 11 10 11"), false, 0, new Ponto(0, 0)); // Usando string para vértices
        arena.getObstaculos().add(obstaculo);
        Assertions.assertFalse(arena.getObstaculos().isEmpty());
    }

    @Test
    public void intercetaObstaculoTrueTest() {
        LinkedList<Quadrado> quadrados = new LinkedList<>();
        quadrados.add(new Quadrado("2 2 3 2 3 3 2 3")); // Quadrado na mesma posição que o obstáculo
        Snake snake = new Snake(quadrados.getFirst());
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        obstaculos.add(new Obstaculo(new Quadrado("2 2 3 2 3 3 2 3"), false, 0, new Ponto(0, 0)));
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, snake, comidas, obstaculos);
        arena.colisaoObstaculo();
        Assertions.assertFalse(arena.isJogoAtivo());
    }

    @Test
    public void intercetaObstaculoFalseTest() {
        LinkedList<Quadrado> quadrados = new LinkedList<>();
        quadrados.add(new Quadrado("2 2 3 2 3 3 2 3")); // Quadrado longe do obstáculo
        Snake snake = new Snake(quadrados.getFirst());
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        obstaculos.add(new Obstaculo(new Quadrado("10 10 11 10 11 11 10 11"), false, 0, new Ponto(0, 0)));
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, snake, comidas, obstaculos);
        arena.colisaoObstaculo();
        Assertions.assertTrue(arena.isJogoAtivo());
    }

    @Test
    public void intercetaParedeTest() {
        LinkedList<Quadrado> quadrados = new LinkedList<>();
        quadrados.add(new Quadrado("0 0 1 0 1 1 0 1"));
        Snake snake = new Snake(quadrados.getFirst());
        List<Comida> comidas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();
        int pontuacaoComida = 100;
        ArenaDeJogo arena = new ArenaDeJogo(100, 100, snake, comidas, obstaculos);
        arena.colisaoParede();
        Assertions.assertFalse(arena.isJogoAtivo());
    }
}