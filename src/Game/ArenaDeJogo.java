package Game;

public class ArenaDeJogo {
    private int nLinhas;
    private int nColunas;
    private Celula[][] celulas;

    private Snake snake;

    private Comida[] comidas;

    private String tipoComida;

    public ArenaDeJogo(int nLinhas, int nColunas,String tipoComida) {
        this.nLinhas = nLinhas;
        this.nColunas = nColunas;
        this.celulas = new Celula[nLinhas][nColunas];
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < nColunas; j++) {
                celulas[i][j] = new Celula(i, j);
            }
        }
    }
}
