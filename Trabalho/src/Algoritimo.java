import java.util.Queue;

public abstract class Algoritimo {

    protected int quadrosDisponiveis;
    protected int tempoClock;
    protected Queue<Acesso> listaAcessos;

    protected int pageFaults = 0;

    Algoritimo (int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        this.quadrosDisponiveis = quadrosDisponiveis;
        this.tempoClock = tempoClock;
        this.listaAcessos = listaAcessos;
    }
    protected void executaAcesso(){};

    public int getPageFaults() {
        return pageFaults;
    }
    
}
