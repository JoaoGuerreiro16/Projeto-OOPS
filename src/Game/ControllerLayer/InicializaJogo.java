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


public class InicializaJogo {

    private static Random random = new Random();
     private MovementStrategy movementStrategy;

    public ArenaDeJogo inicializaJogo(Configuracoes config) {
        int largura = config.getLargura();
        int altura = config.getAltura();

        // Criar a Snake
        Snake snake = criaSnake(config);
       
        // Criar obstáculos
        List<Obstaculo> obstaculos = criaObstaculos(config, snake);
        
        // Criar comida inicial verificando a validade da posição
        Comida comidaInicial = criaComidaInicial(config, largura, altura, snake, obstaculos);
       
        // Criar a arena de jogo

        return ArenaDeJogo.getInstance(altura, largura, snake, comidaInicial, obstaculos,config.getPontuacaoComida(), config.getTamanhoComida(), config.getTipoComida());


    }

    public Snake criaSnake(Configuracoes config) {
        int xSnake = config.getLargura() / 2;
        int ySnake = config.getAltura() / 2;
     
        Quadrado cabecaSnake = new Quadrado(new Ponto(xSnake, ySnake), config.getTamanhoCabeca());
        

        return Snake.getInstance(cabecaSnake, config.getMovementStrategy());
    }

    public List<Obstaculo> criaObstaculos(Configuracoes config,Snake snake) {
        List<Obstaculo> obstaculos = new ArrayList<>();
        int tentativasMax = 100; // Número de tentativas para colocar um obstáculo sem sobreposição

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

    public Poligono gerarPoligonoAleatorio(Configuracoes config) {
        int numVertices = random.nextInt(3) + 3; // Triângulo a pentágono
        ArrayList<Ponto> pontos = new ArrayList<>();
        int centerX = random.nextInt(config.getLargura());
        int centerY = random.nextInt(config.getAltura());
        int raio = random.nextInt(5) + 2; // Tamanho aleatório do polígono

        for (int i = 0; i < numVertices; i++) {
            double angulo = 2 * Math.PI * i / numVertices;
            int x = centerX + (int) (raio * Math.cos(angulo));
            int y = centerY + (int) (raio * Math.sin(angulo));
            pontos.add(new Ponto(x, y));
        }
        return new Poligono(pontos);
    }

    public boolean isPosicaoValidaParaObstaculos(Poligono poligono, List<Obstaculo> obstaculos, Configuracoes config,Snake snake) {
        if (!isDentroDaArena(poligono, config.getLargura(), config.getAltura())) {
            return false;
        }

        for (Obstaculo obst : obstaculos) {
            if (poligono.intercetaPoligono(obst.getPoligono())) {
                return false;
            }
        }

        if (poligono.intercetaPoligono(snake.getSnake().getFirst())) {
            return false;
        }

        return true;
    }

    public Comida criaComidaInicial(Configuracoes config, int largura, int altura, Snake snake, List<Obstaculo> obstaculos) {
        int tentativasMax = 100;
        Comida comida;
        int x;
        int y;
        String tipoComida;
        Ponto posicao;
        for (int tentativas = 0; tentativas < tentativasMax; tentativas++) {
             x = random.nextInt(largura);
             y = random.nextInt(altura);
             posicao = new Ponto(x, y);
             tipoComida = config.getTipoComida();
            
            if(tipoComida.equals("quadrado")){
               comida = new ComidaQuadrado(new Quadrado(posicao, config.getTamanhoComida()), config.getPontuacaoComida()); 
            } else{
               comida = new ComidaCirculo(new Circulo(posicao, config.getTamanhoComida() / 2.0), config.getPontuacaoComida());
            }
        

            if (isPosicaoValidaParaComida(comida, snake, obstaculos, largura, altura)) {
                return comida;
            }
        }
        throw new RuntimeException("Não foi possível inicializar a comida em uma posição válida.");
    }

    public boolean isPosicaoValidaParaComida(Comida comida, Snake snake, List<Obstaculo> obstaculos, int largura, int altura) {
        if (!isDentroDaArena(comida, largura, altura)) {
            return false;
        }

        for (Quadrado parte : snake.getSnake()) {
            if (comida.interceta(parte)) {
                return false;
            }
        }

        for (Obstaculo obst : obstaculos) {
            if (comida.interceta(obst.getPoligono())) {
                return false;
            }
        }
        return true;
    }

    public boolean isDentroDaArena(Object object, int largura, int altura) {
        if (object instanceof Poligono) {
            return isDentroDaArena((Poligono)object, largura, altura);
        } else if (object instanceof Quadrado) {
            return isDentroDaArena((Quadrado)object, largura, altura);
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

    public boolean isDentroDaArena(Poligono poligono, int largura, int altura) {
        for (Ponto ponto : poligono.getPontos()) {
            if (!isDentroDosLimites(ponto, largura , altura)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDentroDaArena(Quadrado quadrado, int largura, int altura) {
        for (Ponto ponto : quadrado.getPontos()) {
            if (!isDentroDosLimites(ponto, largura, altura)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDentroDaArena(Circulo circulo, int largura, int altura) {
        Quadrado quadradoProtetor = circulo.criaQuadradoProtetor(circulo);
        return isDentroDaArena(quadradoProtetor, largura, altura);
    }

    public boolean isDentroDosLimites(Ponto ponto, int largura, int altura) {
        return ponto.getX() >= largura/10 && ponto.getX() <= largura - (largura/10)&& ponto.getY() >= altura/10 && ponto.getY() <= altura - (altura/10);
    }
    
}
