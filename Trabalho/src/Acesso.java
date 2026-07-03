/**
 * Classe que representa um acesso à memória por uma página.
 */
public class Acesso {

    // Identificador único da página
    private int id;
    // Tipo de acesso
    private char tipo;
    // Bit de Referência 
    private int R;
    // Bit de Modificação 
    private int M;

    /**
     * Construtor para inicializar um acesso com ID e Tipo.
     */
    Acesso(int id, char tipo){
        this.id = id;
        this.tipo = tipo;
    }
    
    /**
     * Retorna uma representação em texto do acesso para exibição.
     */
    @Override
    public String toString() {
        StringBuilder mensagem = new StringBuilder("Id: "+ id+ " Tipo: "+tipo);
        return mensagem.toString();
    }

    // Métodos getters e setters para acessar e modificar as propriedades da página
    public int getId() {
        return id;
    }

    public int getR() {
        return R;
    }

    public char getTipo() {
        return tipo;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }
    
    public void setR(int r) {
        R = r;
    }
}
