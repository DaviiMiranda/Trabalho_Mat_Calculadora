    import java.util.Map;

    public class Negacao extends Expressao{
        private Expressao operando;

        public Negacao(Expressao operando) {
            this.operando = operando;
        }

        @Override
        public boolean avaliar(Map<String, Boolean> valores) {
            return !operando.avaliar(valores);
        }

        @Override
        public String toString() {
            return "¬(" + operando.toString() + ")";
        }
    }
