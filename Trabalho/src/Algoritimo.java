import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe abstrata base para a implementação dos algoritmos de substituição de páginas.
 */
public abstract class Algoritimo {

    // Quantidade total de quadros de memória física disponíveis
    protected int quadrosDisponiveis;
    protected int tempoClock;
    // Fila de acessos à memória carregados do arquivo
    protected Queue<Acesso> listaAcessos;
    // Lista dinâmica que representa os quadros de memória física atualmente ocupados
    LinkedList<Acesso> listaQuadros = new LinkedList<Acesso>();

    // Contador de page faults ocorridas durante a simulação
    protected int pageFaults = 0;

    /**
     * Construtor base para os algoritmos de substituição de página.
     */
    Algoritimo (int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        this.quadrosDisponiveis = quadrosDisponiveis;
        this.tempoClock = tempoClock;
        this.listaAcessos = listaAcessos;
    }

    /**
     * Método abstrato a ser implementado pelas subclasses para rodar a simulação do algoritmo.
     */
    protected void executaAcesso(){};

    /**
     * Verifica se uma determinada página já está presente em algum quadro da memória.
     */
    protected int verificaPresenca(Acesso novoAcesso) {
        for (int i = 0; i < listaQuadros.size(); i++) {
            if (listaQuadros.get(i).getId() == novoAcesso.getId()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna a quantidade total de faltas de página acumuladas.
     */
    public int getPageFaults() {
        return pageFaults;
    }
    
}
