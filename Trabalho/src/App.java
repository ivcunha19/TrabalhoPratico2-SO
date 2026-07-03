import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Classe principal do sistema que gerencia a leitura das referências 
 * e executa a comparação entre os algoritmos de substituição de páginas.
 */
public class App {
    // Lista global que armazena os acessos carregados do arquivo referencias.txt
    static LinkedList<Acesso> listaAcessos = new LinkedList<>();
    // Quantidade total de quadros de memória física disponíveis
    static int quadros = 0;
    // Intervalo de clock para redefinir bits dos algoritmos (quando aplicável)
    static int tempoClock = 0;

    /**
     * Lê as configurações de simulação e os acessos de página a partir de um arquivo.
     */
    static public void leArquivo(){
        try (BufferedReader br = new BufferedReader(new FileReader("Trabalho/referencias.txt"))) {
            quadros = 0;
            tempoClock = 0;
            String line;
            if ((line = br.readLine()) != null) {
                String[] partes = line.split(";");
                quadros = Integer.parseInt(partes[1]);
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
        
        System.out.println("Quantidade de Quadros: " + quadros);
        System.out.println("Tempo de Clock: " + tempoClock);
        System.out.println("Total de Acessos carregados: " + listaAcessos.size());
        System.out.println();

        LinkedList<Acesso> acessosFIFO = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: FIFO");
        System.out.println("==========================================");
        Algoritimo fifo = new FIFO(quadros, tempoClock, acessosFIFO);
        fifo.executaAcesso(); // Executa o algoritmo FIFO
        int pfFIFO = fifo.getPageFaults();
        System.out.println("Faltas de Página (FIFO): " + pfFIFO);
        System.out.println();

        LinkedList<Acesso> acessosSC = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: SEGUNDA CHANCE");
        System.out.println("==========================================");
        Algoritimo sc = new SegundaChance(quadros, tempoClock, acessosSC);
        sc.executaAcesso(); // Executa o algoritmo de Segunda Chance
        int pfSC = sc.getPageFaults();
        System.out.println("Faltas de Página (Segunda Chance): " + pfSC);
        System.out.println();

        LinkedList<Acesso> acessosMY = clonarAcessos(listaAcessos);
        System.out.println("==========================================");
        System.out.println("Executando: MY");
        System.out.println("==========================================");
        Algoritimo my = new MyAlgoritimo(quadros, tempoClock, acessosMY);
        my.executaAcesso(); // Executa o algoritmo my
        int pfMY = my.getPageFaults();
        System.out.println("Faltas de Página (MY): " + pfMY);
        System.out.println();

        // Exibição do Relatório Comparativo Final
        System.out.println("==========================================");
        System.out.println("           RELATÓRIO COMPARATIVO          ");
        System.out.println("==========================================");
        System.out.printf("Total de Acessos: %d\n", listaAcessos.size());
        System.out.printf("FIFO           : %d Faltas de Página (Taxa: %.2f%%)\n", pfFIFO, ((double)pfFIFO / listaAcessos.size()) * 100);
        System.out.printf("Segunda Chance : %d Faltas de Página (Taxa: %.2f%%)\n", pfSC, ((double)pfSC / listaAcessos.size()) * 100);
        System.out.printf("MY             : %d Faltas de Página (Taxa: %.2f%%)\n", pfMY, ((double)pfMY / listaAcessos.size()) * 100);
        System.out.println("==========================================");
    }

    /**
     * Realiza a clonagem da lista de acessos.
     * Isso evita que alterações feitas por um algoritmo afetem o outro.
     */
    private static LinkedList<Acesso> clonarAcessos(LinkedList<Acesso> original) {
        LinkedList<Acesso> copia = new LinkedList<>();
        // Loop percorrendo cada elemento da lista original para criar instâncias separadas
        for (Acesso a : original) {
            copia.add(new Acesso(a.getId(), a.getTipo()));
        }
        return copia;
    }
}
