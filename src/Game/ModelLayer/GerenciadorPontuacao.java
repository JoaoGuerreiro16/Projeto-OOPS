package Game.ModelLayer;

import java.io.*;
import java.util.*;

public class GerenciadorPontuacao {

    private static final String ARQUIVO_PONTUACOES = "pontuacoes.txt";

    public static void salvarPontuacao(int pontuacao) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pontuacoes.txt", true)))) {
            out.println(pontuacao);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de pontuações.");
            e.printStackTrace();
        }
    }

    public static List<Integer> carregarPontuacoes() {
        List<Integer> pontuacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PONTUACOES))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pontuacoes.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler pontuações do arquivo.");
        }
        Collections.sort(pontuacoes, Collections.reverseOrder());
        return pontuacoes;
    }

    public static void mostrarRanking() {
        List<Integer> pontuacoes = carregarPontuacoes();
        System.out.println("Top pontuações:");
        int rank = 1;
        for (int pontuacao : pontuacoes) {
            System.out.println(rank++ + ". " + pontuacao);
            if (rank > 10) break; 
        }
    }
}
