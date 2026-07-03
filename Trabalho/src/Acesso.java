public class Acesso {

    private int id;
    private char tipo;
    private int R;
    private int M;

    Acesso(int id, char tipo){
        this.id = id;
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        StringBuilder mensagem = new StringBuilder("Id: "+ id+ " Tipo: "+tipo);
        return mensagem.toString();
    }

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
