import java.util.LinkedList;
import java.util.Queue;

public abstract class Algoritimo {

    protected int quadrosDisponiveis;
    protected int tempoClock;
    protected Queue<Acesso> listaAcessos;
    LinkedList<Acesso> listaQuadros = new LinkedList<Acesso>();

    protected int pageFaults = 0;

    Algoritimo (int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        this.quadrosDisponiveis = quadrosDisponiveis;
        this.tempoClock = tempoClock;
        this.listaAcessos = listaAcessos;
    }
    protected void executaAcesso(){};

    protected int verificaPresenca(Acesso novoAcesso) {
        for (int i = 0; i < listaQuadros.size(); i++) {
            if (listaQuadros.get(i).getId() == novoAcesso.getId()) {
                return i;
            }
        }
        return -1;
    }

    public int getPageFaults() {
        return pageFaults;
    }
    
}
