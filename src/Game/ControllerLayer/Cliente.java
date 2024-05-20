package Game.ControllerLayer;

import java.util.Scanner;
import Game.ModelLayer.MovementStrategy.MovimentoAutomatico;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GraphicUI;
import Game.ViewLayer.TextUI;
import Game.ViewLayer.UI;
import Game.ModelLayer.MovementStrategy.MovementStrategy;

public class Cliente {
    public static Configuracoes obterConfiguracoes() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo de UI: 'textual' ou 'grafica'");
        String tipoUI = scanner.next();
        if (!tipoUI.equals("textual") && !tipoUI.equals("grafica")) {
            throw new IllegalArgumentException("A UI só pode ser textual ou gráfica");
        }

        UI ui;
        Configuracoes config;

        if (tipoUI.equalsIgnoreCase("textual")) {
            System.out.println("Digite a largura da arena:");
            int largura = scanner.nextInt();

            System.out.println("Digite a altura da arena:");
            int altura = scanner.nextInt();

            System.out.println("Digite o tamanho do lado da cabeça da snake:");
            int tamanhoCabeca = scanner.nextInt();

            System.out.println("Digite o tipo de comida ('quadrado' ou 'circulo'):");
            String tipoComida = scanner.next();
            if (!tipoComida.equals("quadrado") && !tipoComida.equals("circulo")) {
                throw new IllegalArgumentException("A comida só pode ser quadrada ou circular");
            }

            System.out.println("Digite a dimensão da comida (menor que a dimensão da cabeça):");
            double tamanhoComida = scanner.nextDouble();
            if (tamanhoComida >= tamanhoCabeca) {
                throw new IllegalArgumentException("Comida tem de ser mais pequena que a cabeça da snake");
            }

            System.out.println("Digite a pontuação por comida:");
            int pontuacaoComida = scanner.nextInt();

            System.out.println("Digite o número de obstáculos(1 a 5):");
            int numeroObstaculos = scanner.nextInt();
            if (numeroObstaculos < 1 || numeroObstaculos > 5) {
                throw new IllegalArgumentException("A arena so tem entre 1 a 5 obstáculos");
            }

            System.out.println("Os obstáculos são dinâmicos? (sim/nao):");
            boolean obstaculosDinamicos = scanner.next().equalsIgnoreCase("sim");

            int anguloRotacao = 0;
            if (obstaculosDinamicos) {
                System.out.println("Digite o ângulo de rotação para obstáculos dinâmicos:");
                anguloRotacao = scanner.nextInt();
            }

            System.out.println("Escolha o tipo de movimento: 'manual' ou 'automatico'");
            String tipoMovimento = scanner.next();
            if (!tipoMovimento.equals("manual") && !tipoMovimento.equals("automatico")) {
                throw new IllegalArgumentException("O movimento só pode ser manual ou automático");
            }

            MovementStrategy strategy;
            if (tipoMovimento.equalsIgnoreCase("manual")) {
                strategy = new MovimentoManual();
            } else {
                strategy = new MovimentoAutomatico();
            }

            ui = new TextUI();
            config = new Configuracoes(largura, altura, tamanhoCabeca, tipoComida, tamanhoComida, pontuacaoComida, numeroObstaculos, obstaculosDinamicos, anguloRotacao, strategy, ui);

        } else {
            int largura = 1000;
            int altura = 1000;
            int tamanhoCabeca = 50;
            String tipoComida = "quadrado";
            double tamanhoComida = 25.0;
            int pontuacaoComida = 10;
            int numeroObstaculos = 3;
            boolean obstaculosDinamicos = false;
            int anguloRotacao = 45;

            MovementStrategy strategy = new MovimentoManual();
            ui = new GraphicUI();
            config = new Configuracoes(largura, altura, tamanhoCabeca, tipoComida, tamanhoComida, pontuacaoComida, numeroObstaculos, obstaculosDinamicos, anguloRotacao, strategy, ui);
        }

        return config;
    }

    public static void main(String[] args) {
        Configuracoes config = obterConfiguracoes();

        if (config.getUi() instanceof TextUI) {
            GameMain main = new GameMain();
            main.executaJogo(config);
        } else if (config.getUi() instanceof GraphicUI) {
            GameMainGrafica main = new GameMainGrafica();
            main.executaJogo(config);
        }
    }
}
