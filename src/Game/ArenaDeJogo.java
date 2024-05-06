package Game;

import java.util.List;

public class ArenaDeJogo {
    private int comprimento;
    private int largura;

    private Snake snake;

    private List<Comida> comida;

    private List<Obstaculo> obstaculos;


    public ArenaDeJogo(int comprimento, int largura) {

        this.comprimento = comprimento;
        this.largura = largura;

    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public List<Comida> getComida() {
        return comida;
    }

    public void setComida(List<Comida> comida) {
        this.comida = comida;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public void setObstaculos(List<Obstaculo> obstaculos) {
        this.obstaculos = obstaculos;
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
