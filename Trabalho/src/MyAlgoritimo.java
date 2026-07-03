import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MyAlgoritimo extends Algoritimo {

    private final LinkedList<Acesso> listaQuadros;
    private final Random random;

    public MyAlgoritimo(int quadrosDisponiveis, int tempoClock, Queue<Acesso> listaAcessos) {
        super(quadrosDisponiveis, tempoClock, listaAcessos);
        this.listaQuadros = new LinkedList<>();
        this.random = new Random(42); // Seed fixa para reprodutibilidade nos testes comparativos
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
                // HIT: Atualiza os bits da página existente
                Acesso pagina = listaQuadros.get(presente);
                pagina.setR(1);
                if (novoAcesso.getTipo() == 'W') {
                    pagina.setM(1);
                }
            } else {
                // MISS (Page Fault)
                pageFaults++;
                
                // Configura os bits iniciais da nova página
                novoAcesso.setR(1);
                novoAcesso.setM(novoAcesso.getTipo() == 'W' ? 1 : 0);

                if (listaQuadros.size() < quadrosDisponiveis) {
                    // Ainda há quadros livres
                    listaQuadros.add(novoAcesso);
                } else {
                    // Memória cheia: escolhe uma vítima com Segunda Chance Randomizada
                    boolean acessoInserido = false;
                    while (!acessoInserido) {
                        // Sorteia um quadro aleatoriamente
                        int idxSorteado = random.nextInt(listaQuadros.size());
                        Acesso paginaSorteada = listaQuadros.get(idxSorteado);

                        if (paginaSorteada.getR() == 0) {
                            // Substitui a página vítima
                            listaQuadros.set(idxSorteado, novoAcesso);
                            acessoInserido = true;
                        } else {
                            // Dá uma segunda chance, zerando o bit R
                            paginaSorteada.setR(0);
                        }
                    }
                }
            }

            // Exibe o estado da memória
            for (Acesso pagina : listaQuadros) {
                System.out.println(pagina + " (R=" + pagina.getR() + ", M=" + pagina.getM() + ")");
            }
            System.out.println("----------------");
        }
    }
}
