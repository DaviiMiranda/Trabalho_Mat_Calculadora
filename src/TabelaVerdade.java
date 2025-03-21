import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaVerdade {

    /**
     * Gera a tabela verdade com:
     * 1. Tabela dos Termos (valores das variáveis)
     * 2. Tabela das Expressões (avaliação de cada expressão)
     * 3. Classificação da última expressão (tautologia, contingência ou contradição)
     *
     * @param variaveis Lista de objetos Variavel (nomes das variáveis)
     * @param expList Lista de Expressao (como P->Q)
     */
    public void gerarTabela(List<Variavel> variaveis, List<Expressao> expList) {
        int numVariaveis = variaveis.size();
        int totalLinhas = (int) Math.pow(2, numVariaveis);

        List<Map<String, Boolean>> combinacoes = new ArrayList<>();

        System.out.println("Tabela dos Termos:");
        for (Variavel v : variaveis) {
            System.out.print(v.getNome() + "\t");
        }
        System.out.println();

        // Geração das combinações dos valores (agora começando com "V" no topo)
        for (int i = totalLinhas - 1; i >= 0; i--) {
            Map<String, Boolean> valores = new HashMap<>();
            for (int j = 0; j < numVariaveis; j++) {
                boolean valor = ((i >> (numVariaveis - j - 1)) & 1) == 1;
                valores.put(variaveis.get(j).getNome(), valor);
                System.out.print((valor ? "V" : "F") + "\t");
            }
            combinacoes.add(valores);
            System.out.println();
        }

        System.out.println("\nTabela das Expressões:");
        for (Variavel v : variaveis) {
            System.out.print(v.getNome() + "\t");
        }
        for (Expressao exp : expList) {
            System.out.print("| " + exp.toString() + "\t");
        }
        System.out.println();

        // Variáveis para classificação da última expressão
        int totalVerdadeiro = 0;
        int totalFalso = 0;

        for (Map<String, Boolean> valores : combinacoes) {
            for (Variavel v : variaveis) {
                System.out.print((valores.get(v.getNome()) ? "V" : "F") + "\t");
            }

            for (int i = 0; i < expList.size(); i++) {
                boolean resultado = expList.get(i).avaliar(valores);
                System.out.print("| " + (resultado ? "V" : "F") + "\t");

                // Contabiliza os valores da última expressão
                if (i == expList.size() - 1) {
                    if (resultado) {
                        totalVerdadeiro++;
                    } else {
                        totalFalso++;
                    }
                }
            }
            System.out.println();
        }

        // Classificação da última expressão
        String classificacao;
        if (totalVerdadeiro == totalLinhas) {
            classificacao = "Tautologia ";
        } else if (totalFalso == totalLinhas) {
            classificacao = "Contradição ";
        } else {
            classificacao = "Contingência ";
        }

        System.out.println("\nResultado: " + classificacao);
    }
}
