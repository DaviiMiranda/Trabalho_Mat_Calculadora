import java.util.Stack;

public class Analizador{

    /**
     * Verifica se a fórmula é uma Fórmula Bem Formada (FBF).
     */
    public static boolean ehFBF(String formula) {
        formula = formula.replaceAll("\\s+", ""); // Remove espaços

        if (formula.isEmpty()) return false;

        Stack<Character> pilha = new Stack<>();
        boolean ultimoFoiOperador = true;
        boolean ultimoFoiParentesesAberto = false;
        boolean temLetra = false;

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);

            if (Character.isLetter(c)) {
                if (!ultimoFoiOperador && !ultimoFoiParentesesAberto) return false;
                ultimoFoiOperador = false;
                ultimoFoiParentesesAberto = false;
                temLetra = true;
            } else if (c == '(') {
                pilha.push(c);
                ultimoFoiOperador = true;
                ultimoFoiParentesesAberto = true;
            } else if (c == ')') {
                if (pilha.isEmpty() || ultimoFoiOperador) return false;
                pilha.pop();
                ultimoFoiOperador = false;
                ultimoFoiParentesesAberto = false;
            } else if ("∧∨→".indexOf(c) != -1) {
                if (ultimoFoiOperador || i == 0 || i == formula.length() - 1) return false;
                ultimoFoiOperador = true;
            } else if (c == '¬') {
                if (!ultimoFoiOperador && !ultimoFoiParentesesAberto) return false;
            } else {
                return false;
            }
        }

        return pilha.isEmpty() && temLetra;
    }
}
