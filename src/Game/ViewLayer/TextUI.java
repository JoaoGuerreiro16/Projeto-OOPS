package Game.ViewLayer;

/**
 * Classe responsável pela interface de usuário textual para visualizar o estado do jogo.
 * Esta classe implementa a interface UI e define como o jogo é exibido no console.
 * A classe busca as informações de um objeto GameRasterizer para obter os estados das células do jogo
 * e as imprime em formato de texto.
 *
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 
 */

public class TextUI implements UI {


    public void display(GameRasterizer rasterizer) {
        for (int i = 0; i < rasterizer.getAltura(); i++) {
            for (int j = 0; j < rasterizer.getLargura(); j++) {
                System.out.print(rasterizer.getGrid()[i][j].getEstado().symbol() + " ");
            }
            System.out.println();
        }
    }
}
