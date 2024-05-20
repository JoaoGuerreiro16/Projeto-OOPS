package Game.ViewLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Comida;
import Game.ModelLayer.ComidaCirculo;
import Game.ModelLayer.ComidaQuadrado;
import Game.ModelLayer.Circulo;
import Game.ModelLayer.Obstaculo;
import Game.ModelLayer.Poligono;
import Game.ModelLayer.Quadrado;

public class GraphicUI extends JFrame implements UI {
    private GamePanel gamePanel;
    private Timer timer;
    private ArenaDeJogo arena;
    private Color backgroundColor = new Color(0, 0, 0); // Preto como fundo


    public GraphicUI() {
        setTitle("Snake Game");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        

        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arena != null) {
                    gamePanel.repaint();
                }
            }
        });
        timer.start();
    }

    public void setArena(ArenaDeJogo arena) {
        this.arena = arena;
    }

    @Override
    public void display(RasterizadordeJogo rasterizer) {
        gamePanel.repaint();
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            
                g.setColor(backgroundColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            

            if (arena != null) {
                draw(g);
            }
        }

        private void draw(Graphics g) {
            // Desenhar a cobra
            Color corpoCor = new Color(0, 100, 0); // Verde escuro
            Color cabecaCor = new Color(144, 238, 144); // Verde claro

            for (int i = 0; i < arena.getSnake().getSnake().size(); i++) {
                Quadrado parte = arena.getSnake().getSnake().get(i);
                int x = (int) parte.getPontos().get(0).getX();
                int y = (int) parte.getPontos().get(0).getY();
                int lado = (int) parte.getLado();

                if (i == 0) { // Cabeça da cobra
                    g.setColor(cabecaCor);
                } else { // Corpo da cobra
                    g.setColor(corpoCor);
                }

                g.fillRect(x, y, lado, lado);
            }

            // Desenhar a comida
            Comida comida = arena.getComida();
            if (comida instanceof ComidaQuadrado) {
                g.setColor(Color.RED);
                Quadrado q = ((ComidaQuadrado) comida).getQuadrado();
                int x = (int) q.getPontos().get(0).getX();
                int y = (int) q.getPontos().get(0).getY();
                int lado = (int) q.getLado();
                g.fillRect(x, y, lado, lado);
            } else if (comida instanceof ComidaCirculo) {
                g.setColor(Color.RED);
                Circulo c = ((ComidaCirculo) comida).getCirculo();
                int x = (int) (c.getCentro().getX() - c.getRaio());
                int y = (int) (c.getCentro().getY() - c.getRaio());
                int diametro = (int) (c.getRaio() * 2);
                g.fillOval(x, y, diametro, diametro);
            }

            // Desenhar os obstáculos
            g.setColor(Color.YELLOW);
            for (Obstaculo obstaculo : arena.getObstaculos()) {
                Poligono poligono = obstaculo.getPoligono();
                int[] xPoints = poligono.getPontos().stream().mapToInt(p -> (int) p.getX()).toArray();
                int[] yPoints = poligono.getPontos().stream().mapToInt(p -> (int) p.getY()).toArray();
                g.fillPolygon(xPoints, yPoints, poligono.getPontos().size());
            }
        }
    }
}
