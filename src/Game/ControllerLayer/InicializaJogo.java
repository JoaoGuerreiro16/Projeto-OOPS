package Game.ControllerLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.ModelLayer.ArenaDeJogo;
import Game.ModelLayer.Circulo;
import Game.ModelLayer.Comida;
import Game.ModelLayer.ComidaCirculo;
import Game.ModelLayer.ComidaQuadrado;

import Game.ModelLayer.Obstaculo;
import Game.ModelLayer.Poligono;
import Game.ModelLayer.Ponto;
import Game.ModelLayer.Quadrado;
import Game.ModelLayer.Snake;
import Game.ModelLayer.MovementStrategy.MovementStrategy;

/**
 * Classe responsável por inicializar e configurar o ambiente de jogo,
 * incluindo a criação da cobra, obstáculos, e a comida.
 * Utiliza as configurações fornecidas para definir as dimensões e elementos do jogo.
 *
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 
 */

public class InicializaJogo {

    private static Random random = new Random();
     private MovementStrategy movementStrategy;

     
/**
     * Inicializa o jogo criando os componentes principais como a arena, a cobra,
     * obstáculos, e a comida inicial.
     *
     * @param config Configurações do jogo que incluem detalhes como largura e altura da arena,
     *               tipos de movimento, e características de comida e obstáculos.
     * @return Uma instância configurada de ArenaDeJogo.
     */

    public ArenaDeJogo inicializaJogo(Configuracoes config) {
        int largura = config.getLargura();
        int altura = config.getAltura();

        Snake snake = criaSnake(config);
       
        List<Obstaculo> obstaculos = criaObstaculos(config, snake);

        Comida comidaInicial = criaComidaInicial(config, largura, altura, snake, obstaculos);
    

        return ArenaDeJogo.getInstance(altura, largura, snake, comidaInicial, obstaculos,config.getPontuacaoComida(), config.getTamanhoComida(), config.getTipoComida());


    }

    /**
     * Cria a cobra inicial no centro da arena.
     *
     * @param config Configurações do jogo que incluem detalhes como o tamanho da cabeça da cobra.
     * @return Uma instância de Snake.
     */

    public Snake criaSnake(Configuracoes config) {
        int xSnake = config.getLargura() / 2;
        int ySnake = config.getAltura() / 2;
     
        Quadrado cabecaSnake = new Quadrado(new Ponto(xSnake, ySnake), config.getTamanhoCabeca());
        

        return Snake.getInstance(cabecaSnake, config.getMovementStrategy());
    }

    /**
     * Cria uma lista de obstáculos dentro da arena, garantindo que não sobreponham a cobra
     * nem outros obstáculos previamente criados.
     *
     * @param config Configurações do jogo, incluindo o número de obstáculos e se são dinâmicos.
     * @param snake Instância da cobra para evitar sobreposição.
     * @return Lista de obstáculos criados.
     */

    public List<Obstaculo> criaObstaculos(Configuracoes config,Snake snake) {
        List<Obstaculo> obstaculos = new ArrayList<>();
        int tentativasMax = 100; 

        for (int i = 0; i < config.getNumeroObstaculos(); i++) {
            boolean obstaculoAdicionado = false;
            for (int tentativa = 0; tentativa < tentativasMax && !obstaculoAdicionado; tentativa++) {
                Poligono poligono = gerarPoligonoAleatorio(config);
                if (isPosicaoValidaParaObstaculos(poligono, obstaculos, config,snake)) {
                    obstaculos.add(new Obstaculo(poligono, config.isObstaculosDinamicos(), config.getAnguloRotacao(), poligono.calcularCentro()));
                    obstaculoAdicionado = true;
                }
            }
        }
        return obstaculos;
    }

    /**
     * Gera um polígono aleatório para uso como obstáculo dentro da arena.
     *
     * @param config Configurações do jogo que incluem a largura e altura da arena.
     * @return Um polígono aleatório.
     */

    public Poligono gerarPoligonoAleatorio(Configuracoes config) {
        double numVertices = random.nextInt(3) + 3; 
        ArrayList<Ponto> pontos = new ArrayList<>();
        double centerX = random.nextInt(config.getLargura());
        double centerY = random.nextInt(config.getAltura());
        double raio = random.nextInt(3) + 2; 

        for (int i = 0; i < numVertices; i++) {
            double angulo = 2 * Math.PI * i / numVertices;
            double x = centerX + (raio * Math.cos(angulo));
            double y = centerY + (raio * Math.sin(angulo));
            pontos.add(new Ponto(x, y));
        }
        return new Poligono(pontos);
    }

    /**
     * Verifica se a posição do polígono é válida para ser adicionada como obstáculo na arena.
     *
     * @param poligono O polígono que potencialmente será um obstáculo.
     * @param obstaculos Lista atual de obstáculos para garantir que não haja sobreposição.
     * @param config Configurações do jogo que incluem a largura e altura da arena.
     * @param snake A cobra, para garantir que obstáculos não se sobreponham a ela.
     * @return true se a posição for válida, false caso contrário.
     */

    public boolean isPosicaoValidaParaObstaculos(Poligono poligono, List<Obstaculo> obstaculos, Configuracoes config,Snake snake) {
        if (!isDentroDaArena(poligono, config.getLargura(), config.getAltura())) {
            return false;
        }

        for (Obstaculo obst : obstaculos) {
            if (poligono.contains(obst.getPoligono())) {
                return false;
            }
        }

        if (poligono.contains(snake.getSnake().getFirst())) {
            return false;
        }

        return true;
    }

    /**
 * Cria a comida inicial para o jogo, garantindo que ela seja colocada em uma posição válida
 * que não intercepte a cobra ou obstáculos. Este método tenta várias vezes até encontrar
 * uma localização adequada dentro dos limites da arena.
 *
 * @param config Configurações do jogo que determinam o tipo e tamanho da comida, bem como
 *               as dimensões da arena.
 * @param largura Largura da arena de jogo para garantir que a comida esteja dentro dos limites.
 * @param altura Altura da arena de jogo para garantir que a comida esteja dentro dos limites.
 * @param snake A cobra no jogo, usada para verificar a sobreposição da comida com a cobra.
 * @param obstaculos Lista de obstáculos presentes na arena para evitar sobreposição com a comida.
 * @return Uma nova instância de Comida colocada em uma posição válida na arena.
 * @throws RuntimeException Se após várias tentativas não for possível encontrar uma posição válida para a comida.
 */

    public Comida criaComidaInicial(Configuracoes config, int largura, int altura, Snake snake, List<Obstaculo> obstaculos) {
        int tentativasMax = 100;
        Comida comida = null;

        for (int tentativas = 0; tentativas < tentativasMax && comida == null; tentativas++) {
            int x = random.nextInt(largura);
            int y = random.nextInt(altura);
            Ponto posicao = new Ponto(x, y);
            String tipoComida = config.getTipoComida();

            if (tipoComida.equals("quadrado")) {
                ComidaQuadrado comidaPotencial = new ComidaQuadrado(new Quadrado(posicao, config.getTamanhoComida()), config.getPontuacaoComida());
                if (isPosicaoValidaParaComidaQuadrada(comidaPotencial, snake, obstaculos, largura, altura)) {
                    comida = comidaPotencial;
                }
            } else {
                ComidaCirculo comidaPotencial = new ComidaCirculo(new Circulo(posicao, config.getTamanhoComida() / 2.0), config.getPontuacaoComida());
                if (isPosicaoValidaParaComidaCirculo(comidaPotencial, snake, obstaculos, largura, altura)) {
                    comida = comidaPotencial;
                }
            }
        }

        if (comida == null) {
            throw new RuntimeException("Não foi possível inicializar a comida em uma posição válida após " + tentativasMax + " tentativas.");
        }

        return comida;
    }

    /**
 * Verifica se a posição da comida é válida na arena, assegurando que não haja interceptação
 * com a cobra ou obstáculos e que esteja dentro dos limites da arena.
 *
 * @param comida O objeto Comida a ser verificado.
 * @param snake O objeto Snake para verificar a interceptação.
 * @param obstaculos Lista de obstáculos para verificar a interceptação.
 * @param largura Largura da arena de jogo.
 * @param altura Altura da arena de jogo.
 * @return Verdadeiro se a comida estiver em uma posição válida, falso caso contrário.
 */

 public boolean isPosicaoValidaParaComidaQuadrada(ComidaQuadrado comida, Snake snake, List<Obstaculo> obstaculos, int largura, int altura) {
    if (!isDentroDaArena(comida, largura, altura)) {
        return false;
    }


    for (Quadrado parte : snake.getSnake()) {
        if (comida.getQuadrado().contains(parte)) {
            return false;
        }
    }

    for (Obstaculo obst : obstaculos) {
        if (comida.getQuadrado().contains(obst.getPoligono())) {
            return false;
        }
    }
    return true;
}
public boolean isPosicaoValidaParaComidaCirculo(ComidaCirculo comida, Snake snake, List<Obstaculo> obstaculos, int largura, int altura) {
   Quadrado quadradoProtetor = comida.getCirculo().criaQuadradoProtetor();

    if (!isDentroDaArena(comida, largura, altura)) {
        return false;
    }


    for (Quadrado parte : snake.getSnake()) {
        if (quadradoProtetor.contains(parte)) {
            return false;
        }
    }

    for (Obstaculo obst : obstaculos) {
        if (quadradoProtetor.contains(obst.getPoligono())) {
            return false;
        }
    }
    return true;
}
    


    /**
 * Verifica se um objeto está completamente dentro dos limites da arena de jogo.
 *
 * @param object O objeto a ser verificado.
 * @param largura Largura da arena de jogo.
 * @param altura Altura da arena de jogo.
 * @return Verdadeiro se o objeto estiver completamente dentro dos limites, falso caso contrário.
 */

    public boolean isDentroDaArena(Object object, int largura, int altura) {
        if (object instanceof Poligono) {
            return isDentroDaArena((Poligono)object, largura, altura);
        } else if (object instanceof Circulo) {
            return isDentroDaArena((Circulo)object, largura, altura);
        } else if (object instanceof Comida) {
        
            if (object instanceof ComidaQuadrado) {
                return isDentroDaArena(((ComidaQuadrado)object).getQuadrado(), largura, altura);
            } else if (object instanceof ComidaCirculo) {
                return isDentroDaArena(((ComidaCirculo)object).getCirculo(), largura, altura);
            }
        }
        return false;
    }

    /**
 * Verifica se um polígono está completamente dentro dos limites da arena de jogo.
 *
 * @param poligono O polígono a ser verificado.
 * @param largura Largura da arena.
 * @param altura Altura da arena.
 * @return Verdadeiro se todos os pontos do polígono estiverem dentro dos limites da arena, falso caso contrário.
 */

    public boolean isDentroDaArena(Poligono poligono, int largura, int altura) {
        for (Ponto ponto : poligono.getPontos()) {
            if (!isDentroDosLimites(ponto, largura , altura)) {
                return false;
            }
        }
        return true;
    }

    /**
 * Verifica se um quadrado está completamente dentro dos limites da arena de jogo.
 *
 * @param quadrado O quadrado a ser verificado.
 * @param largura Largura da arena.
 * @param altura Altura da arena.
 * @return Verdadeiro se todos os pontos do quadrado estiverem dentro dos limites da arena, falso caso contrário.
 */
    public boolean isDentroDaArena(Quadrado quadrado, int largura, int altura) {
        for (Ponto ponto : quadrado.getPontos()) {
            if (!isDentroDosLimites(ponto, largura, altura)) {
                return false;
            }
        }
        return true;
    }

    /**
 * Verifica se um círculo está completamente dentro dos limites da arena de jogo.
 *
 * @param circulo O círculo a ser verificado.
 * @param largura Largura da arena.
 * @param altura Altura da arena.
 * @return Verdadeiro se o círculo (representado por um quadrado protetor) estiver dentro dos limites da arena, falso caso contrário.
 */

    public boolean isDentroDaArena(Circulo circulo, int largura, int altura) {
        Quadrado quadradoProtetor = circulo.criaQuadradoProtetor();
        return isDentroDaArena(quadradoProtetor, largura, altura);
    }

    /**
 * Verifica se um ponto específico está dentro dos limites específicos de largura e altura.
 *
 * @param ponto O ponto a ser verificado.
 * @param largura Largura máxima permitida.
 * @param altura Altura máxima permitida.
 * @return Verdadeiro se o ponto estiver dentro dos limites, falso caso contrário.
 */
    public boolean isDentroDosLimites(Ponto ponto, int largura, int altura) {
        return ponto.getX() >= (double) largura /10 && ponto.getX() <= largura - ((double) largura /10)&& ponto.getY() >= (double) altura /10 && ponto.getY() <= altura - ((double) altura /10);
    }
    

}
