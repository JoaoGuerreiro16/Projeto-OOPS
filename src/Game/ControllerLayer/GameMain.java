package Game.ControllerLayer;

import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Direcao;
import Game.ModelLayer.GerenciadorPontuacao;
import Game.ModelLayer.Pontuacao;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GameRasterizer;
import Game.ViewLayer.UI;

import java.util.Scanner;

/**
 * Classe responsável pela execução principal do jogo, controlando o fluxo de jogo,
 * entrada de usuário e atualização dos estados de jogo.
 *
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 05/04/2024
 */

public class GameMain {

    /**
     * Inicia e executa o loop principal do jogo utilizando as configurações fornecidas.
     *
     * @param config Configurações do jogo, incluindo parâmetros como altura e largura da arena, estratégia de movimento, etc.
     */

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
                System.out.println("/n");
                System.out.println("Dir H: " + arena.getSnake().getDirecaoAtual() + "     Pontos: " + arena.getPontuacao().getPontuacao());
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
        GerenciadorPontuacao.salvarPontuacao(Pontuacao.getInstance().getPontuacao());
        GerenciadorPontuacao.mostrarRanking();
        scanner.close();
    }

    }


