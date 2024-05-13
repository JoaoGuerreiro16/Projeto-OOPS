package Tests;

import static org.junit.jupiter.api.Assertions.*;
import Game.ModelLayer.Direcao;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Snake;
import org.junit.jupiter.api.Test;

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
    public void cresceSnakeTest() {
        Quadrado cabeca = new Quadrado("3 2 4 2 4 3 3 3");
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        assertEquals("Snake: [Quadrado: [(3.0,2.0), (4.0,2.0), (4.0,3.0), (3.0,3.0)], Quadrado: [(2.0,2.0), (3.0,2.0), (3.0,3.0), (2.0,3.0)]]", s.toString());
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
        // Inicializa a cabeça da cobra
        Quadrado cabeca = new Quadrado(new Ponto(3, 2), 1);
        Snake s = Snake.getInstance(cabeca, new MovimentoManual());
        s.setDirecaoAtual(Direcao.RIGHT);
        s.cresceSnake();
        s.setDirecaoAtual(Direcao.DOWN);
        s.cresceSnake();
        s.movimentoSnake();
        s.setDirecaoAtual(Direcao.LEFT);
        s.cresceSnake();
        s.movimentoSnake();
        s.setDirecaoAtual(Direcao.UP);
        s.cresceSnake();
        s.movimentoSnake();
        s.setDirecaoAtual(Direcao.RIGHT);
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
