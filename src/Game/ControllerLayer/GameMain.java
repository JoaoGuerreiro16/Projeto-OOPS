package Game.ControllerLayer;

import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Direcao;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GameRasterizer;

import java.util.Scanner;

public class GameMain {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Configuracoes config = Cliente.obterConfiguracoes();
        System.out.println("2");
        InicializaJogo inicializador = new InicializaJogo();
        
        ArenaDeJogo arena = inicializador.inicializaJogo(config);
        System.out.println("3");
        GameRasterizer rasterizer = new GameRasterizer(config.getAltura(), config.getLargura());

        while (arena.isJogoAtivo()) {
            arena.atualizarJogo();

            rasterizer.fillDisplay(arena.getSnake(), arena.getComida(), arena.getObstaculos());
            rasterizer.display();

            if (config.getMovementStrategy() instanceof MovimentoManual) {
                System.out.println("Digite a nova direção (W, A, S, D): ");

                String direcao = scanner.nextLine().toUpperCase();
                switch (direcao) {
                    case "W":
                        ((MovimentoManual) config.getMovementStrategy()).mudarDirecao(arena.getSnake(), Direcao.UP);
                        break;
                    case "S":
                        ((MovimentoManual) config.getMovementStrategy()).mudarDirecao(arena.getSnake(), Direcao.DOWN);
                        break;
                    case "A":
                        ((MovimentoManual) config.getMovementStrategy()).mudarDirecao(arena.getSnake(), Direcao.LEFT);
                        break;
                    case "D":
                        ((MovimentoManual) config.getMovementStrategy()).mudarDirecao(arena.getSnake(), Direcao.RIGHT);
                        break;
                }
            }
        }
    }
}
