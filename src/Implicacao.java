import java.util.Map;

public class Implicacao extends Expressao {
    private Expressao antecedente;
    private Expressao consequente;

    public Implicacao(Expressao antecedente, Expressao consequente) {
        this.antecedente = antecedente;
        this.consequente = consequente;
    }

    @Override
    public boolean avaliar(Map<String, Boolean> valores) {
        // Implicação é falsa somente quando antecedente é true e consequente é false.
        return !antecedente.avaliar(valores) || consequente.avaliar(valores);
    }

    @Override
    public String toString() {
        return "(" + antecedente.toString() + " → " + consequente.toString() + ")";
    }
}
