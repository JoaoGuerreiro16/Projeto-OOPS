package Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class InicializaJogo {

    private static Random random = new Random();

    public ArenaDeJogo inicializaJogo(Configuracoes config){

        int largura = config.getLargura();
        int altura = config.getAltura();
        criaSnake(config);

    }

    public void criaSnake(Configuracoes config){

        int xSnake = config.getLargura() / 2;
        int ySnake = config.getAltura() / 2;

        Quadrado cabecaSnake = new Quadrado(new Ponto(xSnake, ySnake), config.getTamanhoCabeca());
        Snake snake = Snake.getInstance(cabecaSnake);
    }
}
