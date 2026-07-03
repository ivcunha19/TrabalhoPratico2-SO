import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MyAlgoritimo extends Algoritimo {

    private final LinkedList<Acesso> listaQuadros;
    private final Random random;

    public MyAlgoritimo(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos) {
        super(quadrosDisponiveis, tempoClock, listaAcessos);
        this.listaQuadros = new LinkedList<>();
        this.random = new Random(42);
    }

    protected int verificaPresenca(Acesso novoAcesso) {
        for (int i = 0; i < listaQuadros.size(); i++) {
            if (listaQuadros.get(i).getId() == novoAcesso.getId()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void executaAcesso() {
        while (!listaAcessos.isEmpty()) {
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);

            if (presente != -1) {
                Acesso pagina = listaQuadros.get(presente);
                pagina.setR(1);
                if (novoAcesso.getTipo() == 'W') {
                    pagina.setM(1);
                }
            } else {
                pageFaults++;
                novoAcesso.setR(1);
                novoAcesso.setM(novoAcesso.getTipo() == 'W' ? 1 : 0);

                if (listaQuadros.size() < quadrosDisponiveis) {
                    listaQuadros.add(novoAcesso);
                } else {
                    boolean acessoInserido = false;
                    while (!acessoInserido) {
                        int idxSorteado = random.nextInt(listaQuadros.size());
                        Acesso paginaSorteada = listaQuadros.get(idxSorteado);
                        if (paginaSorteada.getR() == 0) {
                            listaQuadros.set(idxSorteado, novoAcesso);
                            acessoInserido = true;
                        } else {
                            paginaSorteada.setR(0);
                        }
                    }
                }
            }

            for (Acesso pagina : listaQuadros) {
                System.out.println(pagina + " (R=" + pagina.getR() + ", M=" + pagina.getM() + ")");
            }
            System.out.println("----------------");
        }
    }
}
