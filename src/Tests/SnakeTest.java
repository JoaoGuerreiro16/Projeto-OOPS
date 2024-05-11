package Tests;

import static org.junit.jupiter.api.Assertions.*;


import Game.ModelLayer.Direcao;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Snake;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.LinkedList;

public class SnakeTest {

    @Test
    public void constructorTest()
    {
        Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
        Snake s = Snake.getInstance(cabeca);
        String expected = "Snake: [" + cabeca + "]";
        assertEquals(expected, s.toString());
        Snake.resetInstance();

    }


    @Test
    public void movimentoSnakeRightCabecaTest()
    {
        Quadrado quadrado = new Quadrado("2 2 3 2 3 3 2 3");
        Snake s = Snake.getInstance(quadrado);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.movimentoSnake();
        Quadrado novaCabeca = s.getSnake().getFirst();
        Quadrado quadradoEsperado = new Quadrado("3 2 4 2 4 3 3 3");
        assertEquals(quadradoEsperado.toString(), novaCabeca.toString());
        Snake.resetInstance();
    }

    @Test
    public void movimentoSnakeLeftCabecaTest()
    {
        Quadrado cabeca = new Quadrado("3 3 4 3 4 4 3 4");
        Snake snake = Snake.getInstance(cabeca);
        snake.setDirecaoAtual(Direcao.LEFT);
        snake.movimentoSnake();
        Quadrado novaCabeca = snake.getSnake().getFirst();
        Quadrado cabecaEsperada = new Quadrado("2 3 3 3 3 4 2 4");
        assertEquals(cabecaEsperada.toString(), novaCabeca.toString());
        Snake.resetInstance();
    }


        @Test
        public void movimentoSnakeUpCabecaTest() {

            Quadrado cabeca = new Quadrado("3 3 4 3 4 4 3 4");
            Snake snake = Snake.getInstance(cabeca);
            snake.setDirecaoAtual(Direcao.UP);
            snake.movimentoSnake();
            Quadrado novaCabeca = snake.getSnake().getFirst();
            Quadrado cabecaEsperada = new Quadrado("3 2 4 2 4 3 3 3");
            assertEquals(cabecaEsperada.toString(), novaCabeca.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeDownCabecaTest() {

            Quadrado cabeca = new Quadrado("3 3 4 3 4 4 3 4");
            Snake snake = Snake.getInstance(cabeca);
            snake.setDirecaoAtual(Direcao.DOWN);
            snake.movimentoSnake();
            Quadrado novaCabeca = snake.getSnake().getFirst();
            Quadrado cabecaEsperada = new Quadrado("3 4 4 4 4 5 3 5");
            assertEquals(cabecaEsperada.toString(), novaCabeca.toString());
        }

    @Test
    public void movimentoSnakeRightTest() {
        Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
        Quadrado segundoSegmento = new Quadrado("1 2 2 2 2 3 1 3");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.movimentoSnake();
        assertEquals("Snake: [Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]", s.toString());
        Snake.resetInstance();
    }

    @Test
    public void movimentoSnakeLeftTest() {
        Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
        Quadrado segundoSegmento = new Quadrado("3 2 4 2 4 3 3 3");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.LEFT);
        s.movimentoSnake();
        assertEquals("Snake: [Quadrado: [(1,2), (2,2), (2,3), (1,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]", s.toString());
        Snake.resetInstance();
    }

    @Test
    public void movimentoSnakeUpTest() {
        Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
        Quadrado segundoSegmento = new Quadrado("2 3 3 3 3 4 2 4");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.UP);
        s.movimentoSnake();
        assertEquals("Snake: [Quadrado: [(2,1), (3,1), (3,2), (2,2)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]", s.toString());
        Snake.resetInstance();
    }

    @Test
    public void movimentoSnakeDownTest() {
        Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
        Quadrado segundoSegmento = new Quadrado("2 1 3 1 3 2 2 2");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.DOWN);
        s.movimentoSnake();
        assertEquals("Snake: [Quadrado: [(2,3), (3,3), (3,4), (2,4)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]", s.toString());
        Snake.resetInstance();
    }
    @Test
    public void cresceSnakeTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Snake s = Snake.getInstance(cabeca);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        assertEquals("Snake: [Quadrado: [(3,2), (4,2), (4,3), (3,3)], Quadrado: [(2,2), (3,2), (3,3), (2,3)]]", s.toString());
        Snake.resetInstance();
    }

    @Test
    public void intercetaSnakeFalseTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Quadrado segundoSegmento = new Quadrado("1 2 2 2 2 3 1 3");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.movimentoSnake();
        assertFalse(s.intercetaSnake());
        Snake.resetInstance();
    }

    @Test
    public void intercetaSnakeTrueTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Quadrado segundoSegmento = new Quadrado("1 2 2 2 2 3 1 3");
        Snake s = Snake.getInstance(cabeca);
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        s.setDirecaoAtual(Direcao.LEFT);
        s.movimentoSnake();
        s.movimentoSnake();
        assertTrue(s.intercetaSnake());
        Snake.resetInstance();
    }


}
