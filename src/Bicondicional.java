import java.util.Map;

public class Bicondicional extends Expressao{

    private Expressao esquerda;
    private Expressao direita;

    public Bicondicional(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }


    @Override
    public boolean avaliar(Map<String, Boolean> valores) {
        // Implicação é falsa somente quando antecedente é true e consequente é false.
        return esquerda.avaliar(valores) == direita.avaliar(valores);
    }

    @Override
    public String toString() {
        return "(" + esquerda + " <-> " + direita + ")";
    }
}
