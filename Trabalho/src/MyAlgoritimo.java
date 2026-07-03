import java.util.Queue;
import java.util.Random;

/**
 * Algoritmo my.
 * Funciona de forma similar à Segunda Chance, mas em vez de percorrer a fila sequencialmente
 * para escolher a vítima, ele sorteia aleatoriamente um quadro da memória.
 */
public class MyAlgoritimo extends Algoritimo {

    private final Random random;

    /**
     * Construtor do algoritmo personalizado, definindo uma semente fixa (42) para reprodutibilidade.
     */
    public MyAlgoritimo(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos) {
        super(quadrosDisponiveis, tempoClock, listaAcessos);
        this.random = new Random(42);
    }

    @Override
    protected void executaAcesso() {
        while (!listaAcessos.isEmpty()) {
            Acesso novoAcesso = listaAcessos.remove();
            int presente = verificaPresenca(novoAcesso);

            if (presente != -1) {
                listaQuadros.get(presente).setR(1);
            } else {
                pageFaults++;
                novoAcesso.setR(1);

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

            // Loop para exibir o estado atual dos quadros de memória após cada acesso
            for (Acesso pagina : listaQuadros) {
                System.out.println(pagina + " (R=" + pagina.getR() + ", M=" + pagina.getM() + ")");
            }
            System.out.println("----------------");
        }
    }
}
