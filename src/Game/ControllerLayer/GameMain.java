package Game.ControllerLayer;

import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Direcao;
import Game.ModelLayer.Pontuacao;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GameRasterizer;
import Game.ViewLayer.UI;

import java.util.Scanner;

public class GameMain {

    public void executaJogo(Configuracoes config)
    {
        Scanner scanner = new Scanner(System.in);
    
        InicializaJogo inicializador = new InicializaJogo();
        
        ArenaDeJogo arena = inicializador.inicializaJogo(config);

         UI ui = config.getUi();
        
        GameRasterizer rasterizer = new GameRasterizer(config.getAltura(), config.getLargura());

        while (arena.isJogoAtivo()) {
            arena.atualizarJogo();

            rasterizer.fillDisplay(arena.getSnake(), arena.getComida(), arena.getObstaculos());
            ui.display(rasterizer);

            if (config.getMovementStrategy() instanceof MovimentoManual) {
                System.out.println("Pontuacao: " + Pontuacao.getInstance().getPontuacao());
                System.out.println("A direção atual é " + arena.getSnake().getDirecaoAtual());
                System.out.println("Digite a nova direção (W, A, S, D): ");

                String direcao = scanner.next().toUpperCase();
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
        scanner.close();
    }

    }


