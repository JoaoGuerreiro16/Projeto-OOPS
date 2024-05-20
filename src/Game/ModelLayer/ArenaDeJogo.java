package Game.ModelLayer;
import java.io.ObjectInputFilter.Config;
import java.util.List;
import java.util.Random;

import Game.ControllerLayer.Configuracoes;

/**
 * Classe que gerencia o ambiente de jogo, incluindo a configuração do jogo,
 * estado do jogo, e progressão do jogo através de interações do jogador e eventos automáticos.
 *
 * @author Tomás Luz e Joao Guerreiro
 * @version 1.0
 */

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
    

   /**
     * Construtor privado para a classe ArenaDeJogo para garantir o uso do padrão Singleton.
     *
     * @param altura Altura da arena.
     * @param largura Largura da arena.
     * @param snake Referência à cobra no jogo.
     * @param comida Comida inicial na arena.
     * @param obstaculos Lista inicial de obstáculos na arena.
     * @param pontuacaoComida Valor de pontuação para cada comida consumida.
     * @param tamanhoComida Tamanho da comida colocada na arena.
     * @param tipoComida Tipo da comida.
     */

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

    /**
     * Método estático para obter a instância única da classe ArenaDeJogo.
     *
     * @return A única instância de ArenaDeJogo.
     */

    public static ArenaDeJogo getInstance(int altura, int largura, Snake snake, Comida comida, List<Obstaculo> obstaculos, int pontuacaoComida, double tamanhoComida, String tipoComida){

        if(instance == null){
            instance = new ArenaDeJogo(altura, largura, snake, comida, obstaculos,pontuacaoComida, tamanhoComida,tipoComida);
        }
        return instance;
    }

    public static ArenaDeJogo getInstance() {return instance;}
    public int getAltura() {return altura;}
    public int getLargura() {return largura;}
    public Pontuacao getPontuacao() {return pontuacao;}
    public int getPontuacaoComida() {return pontuacaoComida;}
    public String getTipoComida() {return tipoComida;}
    public double getTamanhoComida() {return tamanhoComida;}
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

    /**
 * Atualiza o estado do jogo em cada ciclo do loop principal. Este método gerencia o movimento da cobra,
 * verifica colisões, consome comida e adiciona nova comida se necessário.
 */
    
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
                finalizaJogo();
            }

        }

    }

    /**
 * Verifica se a cabeça da cobra colidiu com algum dos obstáculos presentes na arena.
 * Esta função é chamada a cada atualização do jogo para garantir que as colisões com obstáculos sejam tratadas imediatamente.
 */

    public void colisaoObstaculo(){

        for(Obstaculo obstaculo: obstaculos){
            for (Quadrado parte : snake.getSnake()) {
                if(parte.intercetaPoligono(obstaculo.getPoligono())){
                    finalizaJogo();
                } 
            }
            }
        }

        /**
 * Verifica se a cobra colidiu consigo mesma.
 * Este método é chamado a cada atualização do jogo para garantir que a auto-colisão seja tratada imediatamente.
 * A auto-colisão ocorre quando a cabeça da cobra intercepta qualquer parte de seu próprio corpo.
 */

     public void colisaoConsigoMesma(){

        if(snake.intercetaSnake()){
           finalizaJogo();
        }
     }

     /**
 * Verifica se a cabeça da cobra colidiu com as paredes da arena.
 * Este método é utilizado para garantir que as colisões com as bordas da arena sejam tratadas imediatamente,
 * prevenindo que a cobra "escape" do espaço de jogo definido.
 */

        public void colisaoParede(){
        Quadrado cabeca = snake.getSnake().getFirst();
        Ponto centro = cabeca.calcularCentro();
        double metadeLado = cabeca.getLado() / 2;
        if(centro.getX() - metadeLado < 0 || centro.getX() + metadeLado > largura ||
                centro.getY() - metadeLado < 0 || centro.getY() + metadeLado > altura){
            finalizaJogo();
        }

        }

        /**
 * Agrupa as verificações de todas as possíveis colisões: consigo mesma, com obstáculos e com as paredes da arena.
 * Este método é chamado em cada ciclo de atualização do jogo para verificar todas as colisões possíveis.
 */

        public void colisoes(){
        colisaoConsigoMesma();
        colisaoObstaculo();
        colisaoParede();
        }

        /**
     * Verifica se um determinado ponto é uma posição válida para adicionar comida, considerando
     * a proximidade com a borda da arena, obstáculos e a cobra.
     *
     * @param ponto O ponto a ser verificado.
     * @return true se o ponto é válido para colocar comida, false caso contrário.
     */

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

        /**
     * Verifica se a comida colide com qualquer obstáculo ou parte da cobra.
     *
     * @param comida A comida a ser verificada.
     * @return true se a comida interceta algum obstáculo ou a cobra, false caso contrário.
     */


    public boolean interceptaObstaculosOuSnake(Comida comida) {

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

    /**
     * Tenta adicionar uma nova comida à arena verificando se a posição escolhida é válida.
     *
     * @return true se a comida foi adicionada com sucesso, false se não foi possível adicionar após várias tentativas.
     */

    public boolean adicionarComida() {

        int tentativasMaximas = 10000;
        int x;
        int y;
        Ponto novaPosicao;
        Comida testComida;
      
        
        for (int tentativas = 0; tentativas < tentativasMaximas; tentativas++) {
            x = (int) (Math.random() * (largura - tamanhoComida));
            y = (int) (Math.random() * (altura - tamanhoComida));
             boolean reacheableSnake = (x % snake.getSnake().getFirst().getLado() == 0) && (y % snake.getSnake().getFirst().getLado() == 0);
             novaPosicao = new Ponto(x, y);

            if (isPosicaoValidaPonto(novaPosicao) && reacheableSnake) {
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

    /**
     * Atualiza a posição dos obstáculos dinâmicos, aplicando rotação conforme definido.
     */

    public void atualizarObstaculos() {
        for (Obstaculo obstaculo : obstaculos) {
            if (obstaculo.isDinamico()) {
                obstaculo.rotacao(obstaculo.getAnguloRotacao(), obstaculo.getPontoRotacao());
            }
        }
    }


    public void finalizaJogo() {
        jogoAtivo = false;
    }

    public boolean verificarSeGanhou() {
        return pontuacao.getPontuacao() == Integer.MAX_VALUE;
    }

}
