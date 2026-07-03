import java.util.LinkedList;
import java.util.Queue;

public class SegundaChance extends Algoritimo{

    LinkedList<Acesso> listaQuadros;

    SegundaChance(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos){
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

        while(!listaAcessos.isEmpty()){
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);
            if(presente != -1){
                listaQuadros.get(presente).setR(1);
                if(novoAcesso.getTipo() == 'W'){
                    listaQuadros.get(presente).setM(1);
                }
            }else{
                pageFaults++;
                if(listaQuadros.size() < quadrosDisponiveis){
                    novoAcesso.setR(1);
                    if(novoAcesso.getTipo() == 'W'){
                        novoAcesso.setM(1);
                    }else{
                        novoAcesso.setM(0);
                    }
                    listaQuadros.add(novoAcesso);
                }else{
                    boolean acessoInserido = false;
                    while(!acessoInserido){
                        Acesso pagina = listaQuadros.removeFirst();
                        if(pagina.getR() == 0){
                            novoAcesso.setR(1);
                            if(novoAcesso.getTipo() == 'W'){
                                novoAcesso.setM(1);
                            }else{
                                novoAcesso.setM(0);
                            }
                            listaQuadros.addLast(novoAcesso);
                            acessoInserido = true;
                        }else{
                            pagina.setR(0);
                            listaQuadros.addLast(pagina);
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