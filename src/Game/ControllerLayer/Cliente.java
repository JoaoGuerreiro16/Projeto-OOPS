package Game.ControllerLayer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Game.ControllerLayer.Configuracoes;
import Game.ModelLayer.MovementStrategy.MovimentoAutomatico;
import Game.ModelLayer.MovementStrategy.MovimentoManual;
import Game.ViewLayer.GraphicUI;
import Game.ViewLayer.TextUI;
import Game.ViewLayer.UI;
import Game.ModelLayer.MovementStrategy.MovementStrategy;
import Game.ModelLayer.MovementStrategy.MovimentoAutomatico;
import Game.ModelLayer.MovementStrategy.MovimentoManual;

public class Cliente {
        public static Configuracoes obterConfiguracoes() {
            Scanner scanner = new Scanner(System.in);


                System.out.println("Digite a largura da arena:");
                int largura = scanner.nextInt();

                System.out.println("Digite a altura da arena:");
                int altura = scanner.nextInt();

                System.out.println("Digite o tamanho do lado da cabeça da snake:");
                int tamanhoCabeca = scanner.nextInt();

                System.out.println("Digite o tipo de comida ('quadrado' ou 'circulo'):");
                String tipoComida = scanner.next();
                if(!tipoComida.equals("quadrado") && !tipoComida.equals("circulo")){
                    throw new IllegalArgumentException("A comida só pode ser quadrada ou circular");
            }

                System.out.println("Digite a dimensão da comida (menor que a dimensão da cabeça):");
                double tamanhoComida = scanner.nextDouble();
                if(tamanhoComida >= tamanhoCabeca){
                    throw new IllegalArgumentException("Comida tem de ser mais pequena que a cabeça da snake");
                }

                System.out.println("Digite a pontuação por comida:");
                int pontuacaoComida = scanner.nextInt();

                System.out.println("Digite o número de obstáculos(1 a 5):");
                int numeroObstaculos = scanner.nextInt();
                if(numeroObstaculos < 1 || numeroObstaculos > 5){
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
                if(!tipoMovimento.equals("manual") && !tipoMovimento.equals("automatico")){
                    throw new IllegalArgumentException("O movimento só pode ser manual ou automático");
            }

            MovementStrategy strategy;
            if (tipoMovimento.equalsIgnoreCase("manual"))
            {
                strategy = new MovimentoManual();
            } else
            {
            strategy = new MovimentoAutomatico();
            }

                System.out.println("Escolha o tipo de UI: 'textual' ou 'grafica' (apenas a UI textual se encontra disponivel de momento)");

                String tipoUI = scanner.next();
                assert(tipoUI.equals("textual") || tipoMovimento.equals("grafica")) : "Só exite movimento manual ou automático";
            UI ui;
            if (tipoUI.equalsIgnoreCase("textual"))
            {
                ui = new TextUI();
            } else
            {
               ui = new GraphicUI();
            }
                return new Configuracoes(largura, altura, tamanhoCabeca, tipoComida, tamanhoComida, pontuacaoComida, numeroObstaculos, obstaculosDinamicos, anguloRotacao,strategy,ui);
            }

            
           
    public static void main(String[] args) {
        Configuracoes config = obterConfiguracoes();
        GameMain main = new GameMain();
        main.executaJogo(config);
    }

}
