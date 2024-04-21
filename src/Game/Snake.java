package Game;

import java.util.ArrayList;

public class Snake {

     private ArrayList<Quadrado> quadrados;

     public Snake(ArrayList<Quadrado> quadrados)
     {
         setQuadrados(quadrados);
     }

    public ArrayList<Quadrado> getQuadrados() {
        return quadrados;
    }

    public void setQuadrados(ArrayList<Quadrado> quadrados) {

        boolean ladosIguais = true;
        if (quadrados.size() > 1) {
            double lado = quadrados.get(0).getLista_segmentos().get(0).lenght();
            for (int i = 1; i < quadrados.size(); i++) {
                if (quadrados.get(i).getLista_segmentos().get(0).lenght() != lado) {
                    ladosIguais = false;
                    break;
                }
            }
        }
        if (ladosIguais) {
            this.quadrados = quadrados;
        } else {
            System.out.println("Snake:vi");
        }
    }

    @Override
    public String toString() {
        return "Snake: " + getQuadrados().toString();
    }
}
