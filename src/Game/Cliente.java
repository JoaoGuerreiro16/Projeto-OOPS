package Game;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Game.ControllerLayer.Configuracoes;
import Game.ModelLayer.MovementStrategy.AutomatedMovement;
import Game.ModelLayer.MovementStrategy.ManualMovement;
import Game.ModelLayer.MovementStrategy.MovementStrategy;

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
                assert(tipoComida.equals("quadrado") || tipoComida.equals("circulo")): "A comida só pode ser circular ou quadrada";

                System.out.println("Digite a dimensão da comida (menor que a dimensão da cabeça):");
                double tamanhoComida = scanner.nextDouble();
                assert(tamanhoComida >= tamanhoCabeca) : "A comida tem de ser menor que a cabeça";

                System.out.println("Digite a pontuação por comida:");
                int pontuacaoComida = scanner.nextInt();

                System.out.println("Digite o número de obstáculos(1 a 5):");
                int numeroObstaculos = scanner.nextInt();
                assert (numeroObstaculos >= 1 && numeroObstaculos <= 5) : "Número de obstáculos fora do intervalo permitido (1 a 5).";

                System.out.println("Os obstáculos são dinâmicos? (sim/não):");
                boolean obstaculosDinamicos = scanner.next().equalsIgnoreCase("sim");

                int anguloRotacao = 0;
                if (obstaculosDinamicos) {
                    System.out.println("Digite o ângulo de rotação para obstáculos dinâmicos:");
                    anguloRotacao = scanner.nextInt();
                }

                System.out.println("Escolha o tipo de movimento: 'manual' ou 'automatico'");
            String tipoMovimento = scanner.next();
            MovementStrategy strategy;
            if (tipoMovimento.equalsIgnoreCase("manual")) 
            {
                strategy = new ManualMovement();
            } else 
            {
            strategy = new AutomatedMovement();
            }

            scanner.close();

                return new Configuracoes(largura, altura, tamanhoCabeca, tipoComida, tamanhoComida, pontuacaoComida, numeroObstaculos, obstaculosDinamicos, anguloRotacao,strategy);
            }

    public static void main(String[] args) {
        Configuracoes config = obterConfiguracoes();
        System.out.println("Configurações do jogo coletadas com sucesso!");
    }

}
