import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class App {
    static LinkedList<Acesso> listaAcessos = new LinkedList<>();
    static int Quadros = 0;
    static int tempoClock = 0;

    static public void leArquivo(){
        try (BufferedReader br = new BufferedReader(new FileReader("referencias.txt"))) {
            Quadros = 0;
            tempoClock = 0;
            String line;
            if ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                Quadros = Integer.parseInt(partes[1]);
            }

            if ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                tempoClock = Integer.parseInt(partes[1]);
            }
            LinkedList<String> listaString = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                listaString.add(line);
            }
            int id;
            char tipo;
            for (String string : listaString) {
                String[] partes = string.split(";");
                id = Integer.parseInt(partes[0]);
                tipo = partes[1].charAt(0);
                Acesso novo = new Acesso(id, tipo);
                listaAcessos.add(novo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        
        leArquivo();
        System.out.println(Quadros);
        Algoritimo algoritimo = new SegundaChance(Quadros, tempoClock, listaAcessos);
        algoritimo.executaAcesso();
    }
}
