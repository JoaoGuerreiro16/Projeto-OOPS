import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Constructor<?> constructor;
        Class<?> cl;
        Poligono p;
        String s;
        String[] aos;
        String[] aos1 = new String[2];
        List<Poligono> poligonos = new ArrayList<>();
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
            s = sc.nextLine();
            aos1 = s.split(" ",2);
        }

        sc.close();
            if(aos1.length == 1)
            {
                Poligono poligonoRodado = poligonos.get(0).poligonRotation(Integer.parseInt(aos1[0]),poligonos.get(0).calcularCentro());
                if(poligonos.get(0).equals(poligonoRodado))
                {
                    System.out.print("Duplicado");
                    System.exit(0);
                }

                System.out.println(poligonoRodado.toString());
            }
            else
            {
                String [] parts = aos1[1].split(" ");
                Ponto pivot = new Ponto(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
                Poligono poligonoRodado = poligonos.get(0).poligonRotation(Integer.parseInt(aos1[0]),pivot);
                if(poligonos.get(0).equals(poligonoRodado))
                {
                    System.out.print("Duplicado");
                    System.exit(0);
                }

                System.out.println(poligonoRodado.toString());
            }
    }
        }