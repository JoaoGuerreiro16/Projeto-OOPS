package Game.ModelLayer;

import java.io.*;
import java.util.*;

/**
 * Classe GerenciadorPontuacao gerencia o armazenamento e recuperação das pontuações do jogo.
 *
 * Esta classe oferece métodos para salvar pontuações em um arquivo, carregar pontuações do arquivo,
 * e exibir as pontuações em ordem decrescente. É essencial para manter um registro persistente das
 * melhores pontuações dos jogadores.
 *
 * @author Tomas Luz & Joao Guerreiro
 * @version 1.0
 */

public class GerenciadorPontuacao {

    private static final String ARQUIVO_PONTUACOES = "pontuacoes.txt";


    /**
     * Salva a pontuação atual em um arquivo de texto.
     *
     * Este método adiciona a pontuação fornecida ao final do arquivo de pontuações.
     * Caso ocorra um erro durante a escrita, uma mensagem de erro é exibida no console.
     *
     * @param pontuacao A pontuação a ser salva.
     */

    public static void salvarPontuacao(int pontuacao) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pontuacoes.txt", true)))) {
            out.println(pontuacao);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de pontuações.");
            e.printStackTrace();
        }
    }

    /**
     * Carrega as pontuações do arquivo e retorna uma lista ordenada em ordem decrescente.
     *
     * Este método lê todas as pontuações do arquivo, converte-as para inteiros,
     * e ordena-as em ordem decrescente. Se houver um erro durante a leitura do arquivo,
     * uma mensagem de erro é exibida.
     *
     * @return Uma lista das pontuações em ordem decrescente.
     */

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


    /**
     * Mostra o ranking das top 10 pontuações no console.
     *
     * Este método carrega as pontuações, ordena-as e exibe as 10 melhores no console.
     * Cada pontuação é numerada de acordo com sua posição no ranking.
     */

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
