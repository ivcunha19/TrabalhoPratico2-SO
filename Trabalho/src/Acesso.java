import java.util.LinkedList;

public class Acesso {

    private int id;
    private char tipo;
    private 

    Acesso(int id, char tipo){
        this.id = id;
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        StringBuilder mensagem = new StringBuilder("Id: "+ id+ "Tipo: "+tipo);
        return mensagem.toString();
    }
}
