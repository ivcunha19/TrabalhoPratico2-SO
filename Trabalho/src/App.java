import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class App {
    static LinkedList<Acesso> listaAcessos = new LinkedList<>();
    static ConfigMemoria configs;

    static public void leArquivo(){
        try (BufferedReader br = new BufferedReader(new FileReader("referencias.txt"))) {
            String line;
            int Quadros = 0;
            int tempoClock = 0;
            if ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                Quadros = partes[1].charAt(0);
            }
            if ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                tempoClock = partes[1].charAt(0);
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
            configs = new ConfigMemoria(Quadros, tempoClock,listaAcessos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        
        leArquivo();
        for (Acesso acesso : listaAcessos) {
            System.out.println(acesso.toString());
        }
    }
}
