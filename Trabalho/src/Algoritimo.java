import java.util.Queue;

public abstract class Algoritimo {

    protected int quadrosDisponiveis;
    protected int tempoClock;
    protected Queue<Acesso> listaAcessos;
    protected Acesso[] quadros;

    Algoritimo (int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        this.quadrosDisponiveis = quadrosDisponiveis;
        this.tempoClock = tempoClock;
        this.listaAcessos = listaAcessos;
        quadros = new Acesso[quadrosDisponiveis];
    }
    protected void executaAcesso(){};
    
}
