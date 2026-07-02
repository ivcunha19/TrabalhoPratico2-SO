import java.util.LinkedList;

public class ConfigMemoria {
    
    private int quadrosDisponiveis;
    private int tempoClock;
    private LinkedList<Acesso> listaAcessos;
    private Acesso[] quadros;

    ConfigMemoria(int quadrosDisponiveis, int tempoClock, LinkedList<Acesso> listaAcessos){
        this.quadrosDisponiveis = quadrosDisponiveis;
        this.tempoClock = tempoClock;
        this.listaAcessos = listaAcessos;
        quadros = new Acesso[quadrosDisponiveis];
    }
}
