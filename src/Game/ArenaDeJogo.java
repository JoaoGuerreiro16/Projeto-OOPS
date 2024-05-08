package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaDeJogo {

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


    public ArenaDeJogo(int altura, int largura, Snake snake, Comida comida, List<Obstaculo> obstaculos, int pontuacaoComida, double tamanhoComida, String tipoComida) {

        this.altura = altura;
        this.largura = largura;
        this.pontuacao = new Pontuacao();
        this.snake = snake;
        this.comida = comida;
        this.obstaculos = obstaculos;
        this.jogoAtivo = true;
        this.pontuacaoComida = pontuacaoComida;
        this.tamanhoComida = tamanhoComida;
        this.tipoComida = tipoComida;
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
        public boolean isPosicaoValida(Ponto ponto){

            if(ponto.getX() < 0 || ponto.getX() > largura || ponto.getY() < 0 || ponto.getY() > altura){
                return false;
            }

            for(Quadrado parteCorpo: snake.getSnake()){
                if(parteCorpo.containsPonto(ponto)){
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



    public boolean adicionarComida() {

        int tentativasMaximas = 100;
        for (int tentativas = 0; tentativas < tentativasMaximas; tentativas++) {
            int x = rand.nextInt(largura);
            int y = rand.nextInt(altura);
            Ponto novaPosicao = new Ponto(x, y);

            if (isPosicaoValida(novaPosicao)) {
                if (tipoComida.equals("quadrado")) {
                    comida = new ComidaQuadrado(new Quadrado(novaPosicao, tamanhoComida), pontuacaoComida);
                    return true;
                } else {
                    comida = new ComidaCirculo(new Circulo(novaPosicao, tamanhoComida / 2.0), pontuacaoComida);
                    return true;
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
