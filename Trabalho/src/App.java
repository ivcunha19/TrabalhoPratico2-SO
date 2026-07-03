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
        
        System.out.println("Quantidade de Quadros: " + Quadros);
        System.out.println("Tempo de Clock: " + tempoClock);
        System.out.println("Total de Acessos carregados: " + listaAcessos.size());
        System.out.println();

        // 1. Segunda Chance
        LinkedList<Acesso> acessosSC = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: SEGUNDA CHANCE");
        System.out.println("==========================================");
        Algoritimo sc = new SegundaChance(Quadros, tempoClock, acessosSC);
        sc.executaAcesso();
        int pfSC = sc.getPageFaults();
        System.out.println("Faltas de Página (Segunda Chance): " + pfSC);
        System.out.println();

        // 2. FIFO
        LinkedList<Acesso> acessosFIFO = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: FIFO");
        System.out.println("==========================================");
        Algoritimo fifo = new FIFO(Quadros, tempoClock, acessosFIFO);
        fifo.executaAcesso();
        int pfFIFO = fifo.getPageFaults();
        System.out.println("Faltas de Página (FIFO): " + pfFIFO);
        System.out.println();

        // 3. MyAlgoritimo (Random-R)
        LinkedList<Acesso> acessosMY = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: MY ALGORITIMO (Random-R)");
        System.out.println("==========================================");
        Algoritimo my = new MyAlgoritimo(Quadros, tempoClock, acessosMY);
        my.executaAcesso();
        int pfMY = my.getPageFaults();
        System.out.println("Faltas de Página (MY - Random-R): " + pfMY);
        System.out.println();

        // Relatório Comparativo
        System.out.println("==========================================");
        System.out.println("           RELATÓRIO COMPARATIVO          ");
        System.out.println("==========================================");
        System.out.printf("Total de Acessos: %d\n", listaAcessos.size());
        System.out.printf("Segunda Chance : %d Faltas de Página (Taxa: %.2f%%)\n", pfSC, ((double)pfSC / listaAcessos.size()) * 100);
        System.out.printf("FIFO           : %d Faltas de Página (Taxa: %.2f%%)\n", pfFIFO, ((double)pfFIFO / listaAcessos.size()) * 100);
        System.out.printf("MY (Random-R)  : %d Faltas de Página (Taxa: %.2f%%)\n", pfMY, ((double)pfMY / listaAcessos.size()) * 100);
        System.out.println("==========================================");
    }

    private static LinkedList<Acesso> clonarAcessos(LinkedList<Acesso> original) {
        LinkedList<Acesso> copia = new LinkedList<>();
        for (Acesso a : original) {
            copia.add(new Acesso(a.getId(), a.getTipo()));
        }
        return copia;
    }
}
