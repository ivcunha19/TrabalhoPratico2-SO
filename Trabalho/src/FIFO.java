import java.util.LinkedList;
import java.util.Queue;

public class FIFO extends Algoritimo{

    LinkedList<Acesso> listaQuadros = new LinkedList<Acesso>();

    FIFO(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        super(quadrosDisponiveis, tempoClock, listaAcessos);
        listaQuadros = new LinkedList<Acesso>();
    }

    protected int verificaPresenca(Acesso novoAcesso){
        for(int i = 0; i < listaQuadros.size(); i++){
            if(listaQuadros.get(i).getId() == novoAcesso.getId()){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void executaAcesso() {

        while(listaAcessos.isEmpty()){
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);
            if(presente == -1){
                if(listaQuadros.size() < quadrosDisponiveis){
                    listaQuadros.add(novoAcesso);
                }else{
                    listaQuadros.remove();
                    listaQuadros.add(novoAcesso);
                }
            }
            for(Acesso pagina : listaQuadros){
                System.out.println(pagina);
            }
            System.out.println("----------------");
        }
    }
}
