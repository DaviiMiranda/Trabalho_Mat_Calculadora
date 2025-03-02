import java.util.Map;

public class Disjuncao extends Expressao {
    private Expressao esquerda;
    private Expressao direita;

    public Disjuncao(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean avaliar(Map<String, Boolean> valores) {
        return esquerda.avaliar(valores) || direita.avaliar(valores);
    }

    @Override
    public String toString() {
        return "(" + esquerda.toString() + " ∨ " + direita.toString() + ")";
    }
}
