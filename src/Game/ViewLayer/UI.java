package Game.ViewLayer;

/**
 * Interface UI define o contrato para classes de interface de usuário dentro do jogo.
 * Esta interface garante que qualquer classe de interface de usuário, como TextUI ou GraphicUI,
 * implementará o método display necessário para visualizar o estado do jogo.
 * 
 * As classes que implementam esta interface podem interagir com um objeto RasterizadordeJogo para
 * acessar e exibir o estado atual do jogo, permitindo diferentes formas de apresentação,
 * como texto ou gráficos.
 * 
 * @author Tomás Luz & Joao Guerreiro
 * @version 1.0 
 */
public interface UI {
    public void display(RasterizadordeJogo rasterizer);
}
