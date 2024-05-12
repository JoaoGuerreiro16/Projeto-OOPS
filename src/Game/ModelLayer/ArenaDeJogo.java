package Game.ModelLayer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.ModelLayer.Pontuacao;

public class ArenaDeJogo {

    private static ArenaDeJogo instance;
    private int altura;
    private int largura;
    private Pontuacao pontuacao;
    private Snake snake;
    private Comida comida;
    private int pontuacaoComida;
    private String tipoComida;
    private double tamanhoComida;
    private List<Obstaculo> obstaculos;
    private boolean jogoAtivo;
    private Random rand = new Random();


    private ArenaDeJogo(int altura, int largura, Snake snake, Comida comida, List<Obstaculo> obstaculos, int pontuacaoComida, double tamanhoComida, String tipoComida) {

        this.altura = altura;
        this.largura = largura;
        this.pontuacao = Pontuacao.getInstance();
        this.snake = snake;
        this.comida = comida;
        this.obstaculos = obstaculos;
        this.jogoAtivo = true;
        this.pontuacaoComida = pontuacaoComida;
        this.tamanhoComida = tamanhoComida;
        this.tipoComida = tipoComida;
    }

    public static ArenaDeJogo getInstance(int altura, int largura, Snake snake, Comida comida, List<Obstaculo> obstaculos, int pontuacaoComida, double tamanhoComida, String tipoComida){

        if(instance == null){
            instance = new ArenaDeJogo(altura, largura, snake, comida, obstaculos,pontuacaoComida, tamanhoComida,tipoComida);
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public Snake getSnake() {
        return snake;
    }


    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public void setObstaculos(List<Obstaculo> obstaculos) {
        this.obstaculos = obstaculos;
    }

    public boolean isJogoAtivo() {
        return jogoAtivo;
    }

    public void atualizarJogo(){
        if(!jogoAtivo){
            return;
        }
        snake.movimentoSnake();
        atualizarObstaculos();
        colisoes();
        if(!jogoAtivo){
            return;
        }
        if (comida.isConsumed(snake)) {
            pontuacao.incrementaPontuacao(comida.getPontuacao());
            snake.cresceSnake();
            boolean haEspaco = adicionarComida();
            if(!haEspaco){
                pontuacao.pontuacaoMaxima();
                ganhouJogo();
            }

        }

    }
    public void colisaoObstaculo(){

        Quadrado cabeca = snake.getSnake().getFirst();
        for(Obstaculo obstaculo: obstaculos){
                if(cabeca.intercetaPoligono(obstaculo.getPoligono())){
                    perdeuJogo("A snake colidiu com um obstáculo");
                }
            }
        }

     public void colisaoConsigoMesma(){

        if(snake.intercetaSnake()){
            perdeuJogo("A snake colidiu consigo mesma");
        }
     }

        public void colisaoParede(){
        Quadrado cabeca = snake.getSnake().getFirst();
        Ponto centro = cabeca.calcularCentro();
        double metadeLado = cabeca.getLado() / 2;
        if(centro.getX() - metadeLado < 0 || centro.getX() + metadeLado > largura ||
                centro.getY() - metadeLado < 0 || centro.getY() + metadeLado > altura){
            perdeuJogo("A snake colidiu com a parede");
        }

        }
        public void colisoes(){
        colisaoConsigoMesma();
        colisaoObstaculo();
        colisaoParede();
        }

        /** provavelmente tem bug**/
        public boolean isPosicaoValidaPonto(Ponto ponto){

            if(ponto.getX() < (double) largura /10 || ponto.getX() > largura - ((double) largura /10)|| ponto.getY() < (double) altura /10 || ponto.getY() > altura - (altura/10)){
                return false;
            }

            for(Quadrado parteCorpo: snake.getSnake()){
                if(parteCorpo.containsPonto(ponto) ){
                    return false;
                }
            }

            for(Obstaculo obstaculo: obstaculos){
                if(obstaculo.getPoligono().containsPonto(ponto)){
                    return false;
                }
            }


            return true;
        }

    private boolean interceptaObstaculosOuSnake(Comida comida) {

        for (Obstaculo obstaculo : obstaculos) {
            if (comida.interceta(obstaculo.getPoligono())) {
                return true;
            }
        }

        for(Quadrado corpoSnake : snake.getSnake()){
            if(comida.interceta(corpoSnake)){
                return true;
            }
        }

        return false;
    }

    public boolean adicionarComida() {

        int tentativasMaximas = 100;
        int x;
        int y;
        Ponto novaPosicao;
        Comida testComida;
        for (int tentativas = 0; tentativas < tentativasMaximas; tentativas++) {
             x = rand.nextInt(largura);
             y = rand.nextInt(altura);
             novaPosicao = new Ponto(x, y);

            if (isPosicaoValidaPonto(novaPosicao)) {
                if (tipoComida.equals("quadrado")) {
                     testComida = new ComidaQuadrado(new Quadrado(novaPosicao, tamanhoComida), pontuacaoComida);
                    if(!interceptaObstaculosOuSnake(testComida)){
                        comida = testComida;
                        return true;
                    }

                } else {
                     testComida = new ComidaCirculo(new Circulo(novaPosicao, tamanhoComida / 2.0), pontuacaoComida);
                    if(!interceptaObstaculosOuSnake(testComida)){
                        comida = testComida;
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public void atualizarObstaculos() {
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.isDinamico()) {
                obstaculo.rotacao(obstaculo.getAnguloRotacao(), obstaculo.getPontoRotacao());
            }
        }
    }

    public void ganhouJogo(){
            jogoAtivo = false;
        System.out.println("Parabéns, ganhou o jogo! \nA sua pontuação foi máxima:" + pontuacao.getPontuacao());
    }

    public void perdeuJogo(String mensagem){
        jogoAtivo = false;
        System.out.println("GAME OVER! " + mensagem + "\nSua pontuação foi: " + pontuacao.getPontuacao());
    }

}
