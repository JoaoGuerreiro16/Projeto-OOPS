package Game.ControllerLayer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Direcao;
import Game.ModelLayer.GerenciadorPontuacao;
import Game.ModelLayer.Pontuacao;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GraphicUI;
import Game.ViewLayer.UI;

public class GameMainGrafica {

    public void executaJogo(Configuracoes config) {
        InicializaJogo inicializador = new InicializaJogo();
        ArenaDeJogo arena = inicializador.inicializaJogo(config);

        UI ui = config.getUi();
        if (ui instanceof GraphicUI) {
            GraphicUI graphicUI = (GraphicUI) ui;
            graphicUI.setArena(arena);
            JFrame frame = (JFrame) graphicUI;
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (config.getMovementStrategy() instanceof MovimentoManual) {
                        MovimentoManual movimentoManual = (MovimentoManual) config.getMovementStrategy();
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_W:
                                movimentoManual.mudarDirecao(arena.getSnake(), Direcao.UP);
                                break;
                            case KeyEvent.VK_S:
                                movimentoManual.mudarDirecao(arena.getSnake(), Direcao.DOWN);
                                break;
                            case KeyEvent.VK_A:
                                movimentoManual.mudarDirecao(arena.getSnake(), Direcao.LEFT);
                                break;
                            case KeyEvent.VK_D:
                                movimentoManual.mudarDirecao(arena.getSnake(), Direcao.RIGHT);
                                break;
                        }
                    }
                }
            });
        }

        while (arena.isJogoAtivo()) {
            try {
                Thread.sleep(500); // Pausa para controlar a velocidade da cobra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Atualizar lógica de movimento e renderização dentro do loop principal
            ui.display(null);
            arena.atualizarJogo();
    

            if (!arena.isJogoAtivo()) {
                if (arena.verificarSeGanhou()) {
                    System.out.println("Parabéns, ganhou o jogo! \nA sua pontuação foi máxima: " + arena.getPontuacao().getPontuacao());
                } else {
                    System.out.println("GAME OVER!!\nA sua pontuação foi: " + arena.getPontuacao().getPontuacao());
                }
            }
        }

        GerenciadorPontuacao.salvarPontuacao(Pontuacao.getInstance().getPontuacao());
        GerenciadorPontuacao.mostrarRanking();
    }
}

