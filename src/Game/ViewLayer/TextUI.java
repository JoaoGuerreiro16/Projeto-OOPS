package Game.ViewLayer;

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
