import java.util.Queue;

/**
 * Algoritmo de substituição de páginas Segunda Chance (Second Chance / Clock).
 * Inspeciona o bit de Referência (R);
 * Se o bit R for 1, a página recebe uma "segunda chance", seu bit R é zerado (0) 
 * e ela vai para o fim da fila de quadros em vez de ser removida.
 */
public class SegundaChance extends Algoritimo{

    SegundaChance(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
        super(quadrosDisponiveis, tempoClock, listaAcessos);
    }

    @Override
    protected void executaAcesso() {

        while(!listaAcessos.isEmpty()){
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);
            if(presente != -1){
                listaQuadros.get(presente).setR(1);
            }else{
                pageFaults++;
                if(listaQuadros.size() < quadrosDisponiveis){
                    novoAcesso.setR(1);
                    listaQuadros.add(novoAcesso);
                }else{
                    boolean acessoInserido = false;
                    while(!acessoInserido){
                        Acesso pagina = listaQuadros.removeFirst();
                        if(pagina.getR() == 0){
                            novoAcesso.setR(1);
                            listaQuadros.add(novoAcesso);
                            acessoInserido = true;
                        }else{
                            pagina.setR(0);
                            listaQuadros.add(pagina);
                        }
                    }
                }
            }
            
            // Loop para exibir o estado atual dos quadros de memória após cada acesso
            for(Acesso pagina : listaQuadros){
                System.out.println(pagina);
            }
            System.out.println("----------------");
        }
    }

}