package Game;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Poligono> poligonos = new ArrayList<>();
        List<Poligono> poligonosTr = new ArrayList<>();
        Constructor<?> constructor;
        Class<?> cl;
        Poligono p;
        String s;
        String[] aos;
        int x;
        int y;
        while (sc.hasNextLine()) {
            s = sc.nextLine();
            if (s.isEmpty())
                break;
            aos = s.split(" ", 2);

            try {
                cl = Class.forName(capital(aos[0]));
                constructor = cl.getConstructor(String.class);
                p = (Poligono) constructor.newInstance(aos[1]);
                poligonos.add(p);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            x = sc.nextInt();
            y = sc.nextInt();

            for(Poligono poligono : poligonos)
            {
                poligonosTr.add(poligono.translacaoCentroide(x,y));
            }
        }
        sc.close();
        if(poligonos.size() > 1) {
            for (int i = 0; i < poligonos.size(); i++) {
                if (poligonos.get(i).equals(poligonos.get((i + 1) % poligonos.size()))) {
                    System.out.println("Duplicado");
                    System.exit(0);
                }
            }
        }
        for (Poligono poligono : poligonosTr) {
            System.out.println(poligono.toString());
        }
    }

}