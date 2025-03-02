import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaVerdade {

    /**
     * Gera a tabela verdade em duas partes:
     * 1. Tabela dos Termos (combinações V/F para as variáveis)
     * 2. Tabela das Expressões (avaliação individual de cada expressão para cada combinação)
     *
     * @param variaveis Lista de objetos Variavel (cada um com um nome único)
     * @param expList Lista de Expressao, cada uma representando uma operação como (P->Q)
     */
    public void gerarTabela(List<Variavel> variaveis, List<Expressao> expList) {
        int numVariaveis = variaveis.size();
        int totalLinhas = (int) Math.pow(2, numVariaveis);

        // Armazena todas as combinações dos valores dos termos
        List<Map<String, Boolean>> combinacoes = new ArrayList<>();

        // --- Parte 1: Tabela dos Termos ---
        System.out.println("Tabela dos Termos:");
        // Cabeçalho com os nomes das variáveis
        for (Variavel v : variaveis) {
            System.out.print(v.getNome() + "\t");
        }
        System.out.println();

        // Geração e impressão das combinações de valores
        for (int i = 0; i < totalLinhas; i++) {
            Map<String, Boolean> valores = new HashMap<>();
            for (int j = 0; j < numVariaveis; j++) {
                // Usa operações bit a bit para definir o valor da variável
                boolean valor = ((i >> (numVariaveis - j - 1)) & 1) == 1;
                valores.put(variaveis.get(j).getNome(), valor);
                System.out.print((valor ? "V" : "F") + "\t");
            }
            combinacoes.add(valores);
            System.out.println();
        }

        // --- Parte 2: Tabela das Expressões ---
        System.out.println("\nTabela das Expressões:");
        // Cabeçalho: variáveis e depois cada expressão (supondo que cada expressão implementa um bom toString)
        for (Variavel v : variaveis) {
            System.out.print(v.getNome() + "\t");
        }
        for (Expressao exp : expList) {
            System.out.print("| " + exp.toString() + "\t");
        }
        System.out.println();

        // Para cada combinação de termos, avalia cada expressão individualmente
        for (Map<String, Boolean> valores : combinacoes) {
            // Imprime os valores dos termos
            for (Variavel v : variaveis) {
                System.out.print((valores.get(v.getNome()) ? "V" : "F") + "\t");
            }
            // Avalia e imprime o resultado de cada expressão para a combinação atual
            for (Expressao exp : expList) {
                boolean result = exp.avaliar(valores);
                System.out.print("| " + (result ? "V" : "F") + "\t");
            }
            System.out.println();
        }
    }
}
