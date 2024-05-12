package Tests;

import static org.junit.jupiter.api.Assertions.*;


import Game.ModelLayer.Direcao;
import Game.ModelLayer.MovementStrategy.MovimentoAutomatico;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
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
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        String expected = "Snake: [" + cabeca + "]";
        assertEquals(expected, s.toString());
        Snake.resetInstance();

    }

        @Test
        public void movimentoSnakeRightCabecaTest() {
            Quadrado quadrado = new Quadrado("2 2 3 2 3 3 2 3");
            Snake s = Snake.getInstance(quadrado, new MovimentoManual());
            s.setDirecaoAtual(Direcao.RIGHT);
            s.movimentoSnake();
            Quadrado novaCabeca = s.getSnake().getFirst();
            Quadrado quadradoEsperado = new Quadrado("3 2 4 2 4 3 3 3");
            assertEquals(quadradoEsperado.toString(), novaCabeca.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeLeftCabecaTest() {
            Quadrado cabeca = new Quadrado("3 3 4 3 4 4 3 4");
            Snake snake = Snake.getInstance(cabeca, new MovimentoManual());
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
            Snake snake = Snake.getInstance(cabeca, new MovimentoManual());
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
            Snake snake = Snake.getInstance(cabeca, new MovimentoManual());
            snake.setDirecaoAtual(Direcao.DOWN);
            snake.movimentoSnake();
            Quadrado novaCabeca = snake.getSnake().getFirst();
            Quadrado cabecaEsperada = new Quadrado("3 4 4 4 4 5 3 5");
            assertEquals(cabecaEsperada.toString(), novaCabeca.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeRightTest() {
            Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
            Quadrado segundoSegmento = new Quadrado("1 2 2 2 2 3 1 3");
            Snake s = Snake.getInstance(cabeca, new MovimentoManual());
            s.getSnake().add(segundoSegmento);
            s.setDirecaoAtual(Direcao.RIGHT);
            s.movimentoSnake();
            assertEquals("Snake: [" + cabeca.toString() + ", " + segundoSegmento.toString() + "]", s.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeLeftTest() {
            Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
            Quadrado segundoSegmento = new Quadrado("3 2 4 2 4 3 3 3");
            Snake s = Snake.getInstance(cabeca, new MovimentoManual());
            s.getSnake().add(segundoSegmento);
            s.setDirecaoAtual(Direcao.LEFT);
            s.movimentoSnake();
            assertEquals("Snake: [" + cabeca.toString() + ", " + segundoSegmento.toString() + "]", s.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeUpTest() {
            Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
            Quadrado segundoSegmento = new Quadrado("2 3 3 3 3 4 2 4");
            Snake s = Snake.getInstance(cabeca, new MovimentoManual());
            s.getSnake().add(segundoSegmento);
            s.setDirecaoAtual(Direcao.UP);
            s.movimentoSnake();
            assertEquals("Snake: [" + cabeca + ", " + segundoSegmento + "]", s.toString());
            Snake.resetInstance();
        }

        @Test
        public void movimentoSnakeDownTest() {
            Quadrado cabeca = new Quadrado("2 2 3 2 3 3 2 3");
            Quadrado segundoSegmento = new Quadrado("2 1 3 1 3 2 2 2");
            Snake s = Snake.getInstance(cabeca, new MovimentoManual());
            s.getSnake().add(segundoSegmento);
            s.setDirecaoAtual(Direcao.DOWN);
            s.movimentoSnake();
            assertEquals("Snake: [" + cabeca + ", " + segundoSegmento + "]", s.toString());
            Snake.resetInstance();
        }
    @Test
    public void cresceSnakeTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        Quadrado segundoSegmento = new Quadrado("2 2 3 2 3 3 2 3");
        assertEquals("Snake: [Quadrado: [3 2, 4 2, 4 3, 3 3], Quadrado: [2 2, 3 2, 3 3, 2 3]]", s.toString());
        Snake.resetInstance();
    }

    @Test
    public void intercetaSnakeFalseTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Quadrado segundoSegmento = new Quadrado("1 2 2 2 2 3 1 3");
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
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
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        s.getSnake().add(segundoSegmento);
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        s.setDirecaoAtual(Direcao.LEFT);
        s.movimentoSnake();
        s.movimentoSnake();
        assertTrue(s.intercetaSnake());
        Snake.resetInstance();
    }

    @Test
    public void cresceSnakeVariasDirecoesTest() {
        Quadrado cabeca = new Quadrado("3 3 4 3 4 4 3 4");
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        s.setDirecaoAtual(Direcao.UP);
        s.cresceSnake();
        s.setDirecaoAtual(Direcao.LEFT);
        s.cresceSnake();
        assertEquals(4, s.getSnake().size());
        Snake.resetInstance();
    }


}
