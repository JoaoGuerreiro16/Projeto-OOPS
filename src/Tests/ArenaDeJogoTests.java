package Tests;

import static org.junit.Assert.*;

import Game.ModelLayer.*;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArenaDeJogoTests {

    private ArenaDeJogo arena;
    private Snake snake;
    private Comida comida;
    private List<Obstaculo> obstaculos;

    @Before
    public void setUp() {

        int altura = 600;
        int largura = 800;
        int pontuacaoComida = 10;
        double tamanhoComida = 20;
        String tipoComida = "quadrado";
        Quadrado cabecaSnake = new Quadrado(new Ponto(largura / 2, altura / 2), 30);
        snake = Snake.getInstance(cabecaSnake, new MovimentoManual());
        obstaculos = new ArrayList<>();
        Poligono poligono = new Poligono(
                new ArrayList<Ponto>() {{
                    add(new Ponto(largura / 2 + 30, altura / 2));
                    add(new Ponto(largura / 2 + 60, altura / 2));
                    add(new Ponto(largura / 2 + 60, altura / 2 + 30));
                    add(new Ponto(largura / 2 + 30, altura / 2 + 30));
                }}
        );
        obstaculos.add(new Obstaculo(poligono, false, 0, poligono.calcularCentro()));
        comida = new ComidaQuadrado(new Quadrado(new Ponto(100, 100), tamanhoComida), pontuacaoComida);
        arena = ArenaDeJogo.getInstance(altura, largura, snake, comida, obstaculos, pontuacaoComida, tamanhoComida, tipoComida);
    }

    @Test
    public void testColisaoComObstaculo() {

        snake.setDirecaoAtual(Direcao.RIGHT);
        arena.atualizarJogo();

        assertFalse(arena.isJogoAtivo());
    }

    @Test
    public void testColisaoComParede() {

        snake.setDirecaoAtual(Direcao.UP);
        for (int i = 0; i < 20; i++) {
            arena.atualizarJogo();
        }

        assertFalse(arena.isJogoAtivo());
    }

    @Test
    public void testColisaoConsigoMesma() {
        snake.cresceSnake();
        snake.cresceSnake();
        snake.cresceSnake();
        snake.setDirecaoAtual(Direcao.RIGHT);
        arena.atualizarJogo();
        snake.setDirecaoAtual(Direcao.DOWN);
        arena.atualizarJogo();
        snake.setDirecaoAtual(Direcao.LEFT);
        arena.atualizarJogo();

        assertFalse(arena.isJogoAtivo());
    }
        @Test
    public void testCriacaoDeNovaComida() {
        boolean comidaAdicionada = arena.adicionarComida();
        assertTrue( comidaAdicionada);
    }

    @Test
    public void testAtualizacaoDeObstaculos() {

        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.isDinamico()) {
                Poligono poligonoAntes = obstaculo.getPoligono();
                arena.atualizarObstaculos();
                Poligono poligonoDepois = obstaculo.getPoligono();

                assertNotEquals( poligonoAntes, poligonoDepois);
            }
        }
    }
}