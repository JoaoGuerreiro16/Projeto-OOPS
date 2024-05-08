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


    public ArenaDeJogo(int altura, int largura, Snake snake, Comida comida, List<Obstaculo> obstaculos, int pontuacaoComida, double tamanhoComida) {

        this.altura = altura;
        this.largura = largura;
        this.pontuacao = new Pontuacao();
        this.snake = snake;
        this.comida = comida;
        this.obstaculos = obstaculos;
        this.jogoAtivo = true;
        this.pontuacaoComida = pontuacaoComida;
        this.tamanhoComida = tamanhoComida;
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
        colisoes();


        if(!jogoAtivo){
            System.out.println("GAME OVER! A sua pontuação foi" + pontuacao.getPontuacao());
            System.exit(0);
        }

        // Verifica se a comida foi consumida pela snake.
        if (comida.isConsumed(snake)) {
            pontuacao.incrementaPontuacao(comida.getPontuacao()); // Aumenta a pontuação baseada na comida consumida.
            snake.cresceSnake(); // Faz a snake crescer.
            adicionarComida();
        }

        // Se após o processamento de colisões o jogo não estiver mais ativo, encerra o jogo.
        if (!jogoAtivo) {
            System.out.println("GAME OVER! A sua pontuação foi " + pontuacao.getPontuacao());
            System.exit(0);
        }
    }
    public void colisaoObstaculo(){

        Quadrado cabeca = snake.getSnake().getFirst();
        for(Obstaculo obstaculo: obstaculos){
                if(cabeca.intercetaPoligono(obstaculo.getPoligono())){
                    jogoAtivo = false;
                    System.out.println("GAME OVER!! Colisão com obstáculo");
                    System.exit(0);
                }
            }
        }

     public void colisaoConsigoMesma(){

        if(snake.intercetaSnake()){
            jogoAtivo = false;
            System.out.println("GAME OVER!! Colisão consigo mesma");
            System.exit(0);
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
            System.exit(0);
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

        public Ponto gerarComidaAleatoria(){
            int tentativasMaximas = 100;
            for (int tentativas = 0; tentativas < tentativasMaximas; tentativas++) {
                int x = rand.nextInt(largura);
                int y = rand.nextInt(altura);
                Ponto novaPosicao = new Ponto(x, y);

                if (isPosicaoValida(novaPosicao)) {
                    return novaPosicao;  // Retorna o ponto válido encontrado
                }
            }
            return null;  // Retorna null se nenhuma posição válida foi encontrada
        }

    public void adicionarComida()
    {
        Ponto posicaoValida = gerarComidaAleatoria();
        if (posicaoValida == null) {
            System.out.println("Não foi possível adicionar nova comida após várias tentativas.");
            jogoAtivo = false;
            System.out.println("GAME OVER! falta de espaço para nova comida.");
        } else {
            if (tipoComida.equals("quadrado")) {
                comida = new ComidaQuadrado(new Quadrado(posicaoValida, tamanhoComida), pontuacaoComida);
            } else {  // "circulo"
                comida = new ComidaCirculo(new Circulo(posicaoValida, tamanhoComida / 2.0), pontuacaoComida);
            }
            System.out.println("Nova comida adicionada com sucesso em " + posicaoValida);
        }

    }

    public void adicionarSnake(Snake snake) // TO DO
    {

    }


    public void adicionarObstaculos() // TO DO
    {

    }

    public void inicializaArena()
    {

    }

}
