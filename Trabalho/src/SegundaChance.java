import java.util.Queue;

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
            for(Acesso pagina : listaQuadros){
                System.out.println(pagina);
            }
            System.out.println("----------------");
        }
    }

}