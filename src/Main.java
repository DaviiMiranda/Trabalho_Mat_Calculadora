import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Solicita a entrada no formato: {P -> Q, R ^ S, S -> Q}
        System.out.println("Digite as proposições (ex: {P -> Q, R ^ S, S -> Q}): ");
        String entrada = scan.nextLine();

        // Remove espaços e as chaves inicial e final
        String conteudo = entrada.trim();
        if (conteudo.startsWith("{") && conteudo.endsWith("}")) {
            conteudo = conteudo.substring(1, conteudo.length() - 1);
        }

        // Divide as operações pela vírgula
        String[] operacoes = conteudo.split(",");

        // Expressão regular para capturar: variavel1, operador e variavel2
        Pattern pattern = Pattern.compile("\\s*(\\w+)\\s*(->|\\^|\\|)\\s*(\\w+)\\s*");

        // Lista para armazenar cada expressão (ex: (P -> Q), (R ^ S), etc.)
        List<Expressao> expList = new ArrayList<>();
        // Conjunto para coletar os nomes das variáveis (sem repetição)
        Set<String> varNames = new HashSet<>();

        // Processa cada operação lida
        for (String op : operacoes) {
            op = op.trim(); // Remove espaços extras
            Matcher matcher = pattern.matcher(op);
            if (matcher.matches()) {
                String var1 = matcher.group(1);
                String operador = matcher.group(2);
                String var2 = matcher.group(3);

                // Adiciona os nomes das variáveis
                varNames.add(var1);
                varNames.add(var2);

                // Cria o objeto Expressao de acordo com o operador
                Expressao expr = null;
                if (operador.equals("->")) {
                    expr = new Implicacao(new Variavel(var1), new Variavel(var2));
                } else if (operador.equals("^")) {
                    expr = new Conjuncao(new Variavel(var1), new Variavel(var2));
                } else if (operador.equals("|")) {
                    expr = new Disjuncao(new Variavel(var1), new Variavel(var2));
                } else {
                    System.out.println("Operador inválido: " + operador);
                    continue;
                }
                expList.add(expr);
            } else {
                System.out.println("Formato inválido na operação: " + op);
            }
        }

        // Converte o conjunto de nomes das variáveis em uma lista de Variavel
        List<Variavel> variaveis = new ArrayList<>();
        for (String nome : varNames) {
            variaveis.add(new Variavel(nome));
        }

        // Gera a tabela verdade: primeiro os termos e depois a avaliação individual das expressões.
        TabelaVerdade tabela = new TabelaVerdade();
        tabela.gerarTabela(variaveis, expList);
    }
}
