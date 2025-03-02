import java.util.Map;

public class Variavel extends Expressao {
    private String nome;

    // Construtor que recebe o nome da variável (por exemplo, "P")
    public Variavel(String nome) {
        this.nome = nome;
    }

    // Método para obter o nome da variável
    public String getNome() {
        return nome;
    }

    /**
     * Avalia a variável com base no mapa de valores.
     * @param valores Um Map que associa nomes de variáveis a valores booleanos.
     * @return O valor booleano associado a essa variável.
     */
    @Override
    public boolean avaliar(Map<String, Boolean> valores) {
        if (!valores.containsKey(nome)) {
            throw new IllegalArgumentException("Valor não definido para a variável: " + nome);
        }
        return valores.get(nome);
    }

    @Override
    public String toString() {
        return "";
    }
}
