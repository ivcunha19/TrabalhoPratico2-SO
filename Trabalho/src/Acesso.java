public class Acesso {

    private int id;
    private char tipo;
    //R é o bit correspondente ao uso da pagina
    private int R;
    //M é o bit correspondente a Modificação
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
