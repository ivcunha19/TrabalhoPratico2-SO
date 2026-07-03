import java.util.Queue;

/**
 * Algoritmo de substituição de páginas FIFO (First-In, First-Out).
 * A primeira página a entrar na memória é a primeira a ser removida quando ocorre falta de página.
 */
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
            
            // Loop para exibir o estado atual dos quadros de memória após o acesso
            for(Acesso pagina : listaQuadros){
                System.out.println(pagina);
            }
            System.out.println("----------------");
        }
    }
}
