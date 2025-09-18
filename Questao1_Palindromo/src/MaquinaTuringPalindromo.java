import java.util.*;

public class MaquinaTuringPalindromo {

    enum Movimento {ESQUERDA, DIREITA, PARADO}
    enum Estado {q0, q1, q2, q3, q4, q5, q6, q9, q10} // q10 = aceita, q9 = rejeita

    static class Transicao {
        Estado novoEstado;
        char escreve;
        Movimento move;

        Transicao(Estado novoEstado, char escreve, Movimento move) {
            this.novoEstado = novoEstado;
            this.escreve = escreve;
            this.move = move;
        }
    }
    

    private ArrayList<Character> fita;
    private int cabeca;
    private Estado estadoAtual;
    private Map<String, Transicao> delta;

    public MaquinaTuringPalindromo(String palavra) {
        fita = new ArrayList<>();
        // adiciona branco inicial para permitir movimento à esquerda seguro
        fita.add('_');
        for (char c : palavra.toCharArray()) {
            fita.add(c);
        }
        // branco final
        fita.add('_');
        // cabeça inicialmente no primeiro símbolo da palavra (posição 1)
        cabeca = 1;
        estadoAtual = Estado.q0;
        delta = new HashMap<>();
        inicializarTransicoes();
    }

    private String chave(Estado estado, char leitura) {
        return estado + "," + leitura;
    }

    private void inicializarTransicoes() {
        // q0: estado inicial - marca primeiro símbolo encontrado
        delta.put(chave(Estado.q0, 'a'), new Transicao(Estado.q1, 'X', Movimento.DIREITA));
        delta.put(chave(Estado.q0, 'b'), new Transicao(Estado.q2, 'X', Movimento.DIREITA));
        delta.put(chave(Estado.q0, 'X'), new Transicao(Estado.q0, 'X', Movimento.DIREITA)); // pula X's
        delta.put(chave(Estado.q0, '_'), new Transicao(Estado.q10, '_', Movimento.PARADO)); // aceita se só restou branco

        // q1: marcou 'a' no começo, vai à direita até encontrar branco (fim)
        delta.put(chave(Estado.q1, 'a'), new Transicao(Estado.q1, 'a', Movimento.DIREITA));
        delta.put(chave(Estado.q1, 'b'), new Transicao(Estado.q1, 'b', Movimento.DIREITA));
        delta.put(chave(Estado.q1, 'X'), new Transicao(Estado.q1, 'X', Movimento.DIREITA));
        delta.put(chave(Estado.q1, '_'), new Transicao(Estado.q3, '_', Movimento.ESQUERDA)); // chegou no fim, dai verifica o último

        // q2: marcou 'b' no começo, vai à direita até encontrar branco (fim)
        delta.put(chave(Estado.q2, 'a'), new Transicao(Estado.q2, 'a', Movimento.DIREITA));
        delta.put(chave(Estado.q2, 'b'), new Transicao(Estado.q2, 'b', Movimento.DIREITA));
        delta.put(chave(Estado.q2, 'X'), new Transicao(Estado.q2, 'X', Movimento.DIREITA));
        delta.put(chave(Estado.q2, '_'), new Transicao(Estado.q4, '_', Movimento.ESQUERDA)); // chegou no fim, dai verifica o último

        // q3: esperando encontrar último símbolo 'a' (ou só Xs -> voltar)
        delta.put(chave(Estado.q3, 'a'), new Transicao(Estado.q5, 'X', Movimento.ESQUERDA)); // marca e volta
        delta.put(chave(Estado.q3, 'X'), new Transicao(Estado.q3, 'X', Movimento.ESQUERDA)); // continua voltando
        delta.put(chave(Estado.q3, '_'), new Transicao(Estado.q0, '_', Movimento.DIREITA)); // só havia Xs, entao volta ao início 
        delta.put(chave(Estado.q3, 'b'), new Transicao(Estado.q9, 'b', Movimento.PARADO)); // rejeita

        // q4: esperando encontrar último símbolo 'b' (ou só Xs -> voltar)
        delta.put(chave(Estado.q4, 'b'), new Transicao(Estado.q6, 'X', Movimento.ESQUERDA)); // marca e volta
        delta.put(chave(Estado.q4, 'X'), new Transicao(Estado.q4, 'X', Movimento.ESQUERDA)); // continua voltando
        delta.put(chave(Estado.q4, '_'), new Transicao(Estado.q0, '_', Movimento.DIREITA)); // só havia Xs entao volta ao início
        delta.put(chave(Estado.q4, 'a'), new Transicao(Estado.q9, 'a', Movimento.PARADO)); // rejeita

        // q5: após marcar o último 'a', volta para o começo (até branco à esquerda) e vai para q0
        delta.put(chave(Estado.q5, 'a'), new Transicao(Estado.q5, 'a', Movimento.ESQUERDA));
        delta.put(chave(Estado.q5, 'b'), new Transicao(Estado.q5, 'b', Movimento.ESQUERDA));
        delta.put(chave(Estado.q5, 'X'), new Transicao(Estado.q5, 'X', Movimento.ESQUERDA));
        delta.put(chave(Estado.q5, '_'), new Transicao(Estado.q0, '_', Movimento.DIREITA));

        // q6: após marcar o último 'b', volta para o começo e vai para q0
        delta.put(chave(Estado.q6, 'a'), new Transicao(Estado.q6, 'a', Movimento.ESQUERDA));
        delta.put(chave(Estado.q6, 'b'), new Transicao(Estado.q6, 'b', Movimento.ESQUERDA));
        delta.put(chave(Estado.q6, 'X'), new Transicao(Estado.q6, 'X', Movimento.ESQUERDA));
        delta.put(chave(Estado.q6, '_'), new Transicao(Estado.q0, '_', Movimento.DIREITA));

        // q9: estado de rejeição (travado)
        delta.put(chave(Estado.q9, 'a'), new Transicao(Estado.q9, 'a', Movimento.PARADO));
        delta.put(chave(Estado.q9, 'b'), new Transicao(Estado.q9, 'b', Movimento.PARADO));
        delta.put(chave(Estado.q9, 'X'), new Transicao(Estado.q9, 'X', Movimento.PARADO));
        delta.put(chave(Estado.q9, '_'), new Transicao(Estado.q9, '_', Movimento.PARADO));
    }

    public boolean VerificaTuring() {
        while (estadoAtual != Estado.q10 && estadoAtual != Estado.q9) {
            char leitura = fita.get(cabeca);
            Transicao t = delta.get(chave(estadoAtual, leitura));

            if (t == null) {
                // transição faltante -> considera como rejeição
                estadoAtual = Estado.q9;
                break;
            }

            // escreve na fita
            fita.set(cabeca, t.escreve);
            // atualiza estado
            estadoAtual = t.novoEstado;

            // move a cabeça com proteção e extensão à direita
            if (t.move == Movimento.DIREITA) {
                cabeca++;
                if (cabeca >= fita.size()) {
                    fita.add('_');
                }
            } else if (t.move == Movimento.ESQUERDA) {
                if (cabeca > 0) {
                    cabeca--;
                } else {
                    // não deveria ocorrer graças ao branco inicial, mas por segurança:
                    estadoAtual = Estado.q9;
                    break;
                }
            } else {
                // PARADO -> sai do loop naturalmente se chegou a q10/q9
            }
        }
        return estadoAtual == Estado.q10;
    }
}
