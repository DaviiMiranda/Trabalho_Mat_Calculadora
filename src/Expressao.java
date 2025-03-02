import java.util.Map;

public abstract class Expressao {
    // Método que recebe um mapeamento (por exemplo, variável "P" -> true/false)
    public abstract boolean avaliar(Map<String, Boolean> valores);

    // Opcional: para exibir a expressão em formato de string
    @Override
    public abstract String toString();
}