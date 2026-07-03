import java.util.Queue;

public class FIFO extends Algoritimo{

    

    FIFO(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        super(quadrosDisponiveis, tempoClock, listaAcessos);
    }

    @Override
    protected void executaAcesso() {

        while(!listaAcessos.isEmpty()){
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);
            if(presente == -1){
                pageFaults++;
                if(listaQuadros.size() < quadrosDisponiveis){
                    listaQuadros.add(novoAcesso);
                }else{
                    listaQuadros.removeFirst();
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
