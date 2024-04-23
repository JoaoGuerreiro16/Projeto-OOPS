package Game;

public class ArenaDeJogo {
    private int nLinhas;
    private int nColunas;
    private Celula[][] celulas;

    private Snake snake;

    private Comida comidaAtual;

    private Obstaculo[] obstaculos;

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

    public Comida getComidaAtual() {
        return comidaAtual;
    }

    public void setComidaAtual(Comida comidaAtual) {
        this.comidaAtual = comidaAtual;
    }

    public Obstaculo[] getObstaculos() {
        return obstaculos;
    }

    public void setObstaculos(Obstaculo[] obstaculos) {
        this.obstaculos = obstaculos;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void addSnake(Snake snake) // TO DO
    {

    }
    public void addComida() // TO DO
    {

    }

    public void addObstaculos() // TO DO
    {

    }

    public void addObstaculo(Obstaculo obstaculo) // TO DO
    {

    }
    public boolean intercetaComida() // TO DO
    {
        return false;

    }

    public boolean intercetaObstaculo() // TO DO
    {
       return false;
    }

    public boolean intercetaParede() // TO DO
    {
       return false;
    }

    public void interceta() // TO DO
    {

    }

}
