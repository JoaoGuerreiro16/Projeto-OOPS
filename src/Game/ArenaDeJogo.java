package Game;

import java.util.ArrayList;
import java.util.List;

public class ArenaDeJogo {
    private int altura;
    private int largura;

    private Pontuacao pontuacao;

    private Snake snake;

    private List<Comida> comida;

    private List<Obstaculo> obstaculos;

    private boolean jogoAtivo;


    public ArenaDeJogo(int altura, int largura, Snake snake, List<Comida> comidas, List<Obstaculo> obstaculos) {

        this.altura = altura;
        this.largura = largura;
        this.pontuacao = new Pontuacao();
        this.snake = snake;
        this.comida = comidas;
        this.obstaculos = obstaculos;
        this.jogoAtivo = true;


    }

    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public void atualizarJogo(){

        if(!jogoAtivo){

            return;
        }
        snake.movimentoSnake();
        colisoes();


        if(!jogoAtivo){
            System.out.println("GAME OVER! A sua pontuação foi" + pontuacao.getPontuacao());
        }
    }
    public void colisaoObstaculo(){

        Quadrado cabeca = snake.getSnake().getFirst();
        for(Obstaculo obstaculo: obstaculos){
                if(cabeca.intercetaPoligono(obstaculo.getPoligono())){
                    jogoAtivo = false;
                    System.out.println("GAME OVER!! Colisão com obstáculo");
                    break;
                }
            }
        }

     public void colisaoConsigoMesma(){

        if(snake.intercetaSnake()){
            jogoAtivo = false;
            System.out.println("GAME OVER!! Colisão consigo mesma");
        }
     }

        public void colisaoParede(){
        Quadrado cabeca = snake.getSnake().getFirst();
        Ponto centro = cabeca.calcularCentro();
        double metadeLado = cabeca.getLado() / 2;
        if(centro.getX() - metadeLado < 0 || centro.getX() + metadeLado > largura ||
                centro.getY() - metadeLado < 0 || centro.getY() + metadeLado > altura){
            jogoAtivo = false;
            System.out.println("GAME OVER!! Colisão com parede");
        }

        }
        public void colisoes(){
        colisaoConsigoMesma();
        colisaoObstaculo();
        colisaoParede();
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



}
