import java.util.*;

public class MaquinaDeTuring {

    // Estrutura da transição: estado destino, símbolo a escrever e direção do movimento
    static class Transicao {
        String estadoDestino;
        char simboloEscrever;
        char direcao; // 'L' = esquerda, 'R' = direita

        public Transicao(String estadoDestino, char simboloEscrever, char direcao) {
            this.estadoDestino = estadoDestino;
            this.simboloEscrever = simboloEscrever;
            this.direcao = direcao;
            
            /* Construtor da classe INTERNA "Transicao".
             * A classe Transicao foi criada dentro de outra classe,
             * porque só faz sentido ela existir dentro deste contexto,
             * ela não vai executar nenhuma outra função no projeto */
        }
    }

    // Função de transição: cada estado tem um mapa que associa símbolos a transições
    private final Map<String, Map<Character, Transicao>> funcaoTransicao = new HashMap<>();
    
    /* O primeiro Map têm como parâmetro a String do estado atual.
     * O outro Map tem o simbolo lido na fita e o estado para qual ir depois (Transicao)
     * E depois inicializa com new HashMap para garantir que esteja vazio. */

    private final String estadoInicial;
    private final Set<String> estadosFinais;
    /* Amigos, o Set<String> é para indicar que 
     * se a máquina parar nele, a entrada é aceita
     * e o "final" serve para dizer que não pode ser alterado
     * 
     * EX: se parar em q1 -> Aceita.
     * porque estava dentro desse Set que q1 era o estado final, */

    public MaquinaDeTuring(String estadoInicial, Set<String> estadosFinais) {
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
    }

    //Adiciona uma transição na função de transição da máquina de Turing com os parametros
    public void adicionarTransicao(String estadoAtual, char simboloLido,
                                   String estadoDestino, char simboloEscrever, char direcao) {
        // Verifica se já existe um mapa de transições para o estado atual
        Map<Character, Transicao> transicoesDoEstado = funcaoTransicao.get(estadoAtual);

        if (transicoesDoEstado == null) {
            // Se não existe, cria um novo mapa
            transicoesDoEstado = new HashMap<>();
            funcaoTransicao.put(estadoAtual, transicoesDoEstado);
        }

        // Agora adiciona a transição para o símbolo lido
        transicoesDoEstado.put(simboloLido,
                new Transicao(estadoDestino, simboloEscrever, direcao));
    }

    // Executa a máquina de Turing em uma entrada fornecida
    public boolean executar(String entrada) {
        // Inicializa a fita com os símbolos da entrada
        List<Character> fita = new ArrayList<>();
        for (char c : entrada.toCharArray()) {
            fita.add(c);
        }
        /* entrada.toCharArray() -> converte a String entrada em um array de caracteres.
         * O for (char c : ...) percorre cada caractere da entrada, um por um.
         * A cada iteração, c recebe o caractere atual. 
         * Ou seja, cada caractere da string de entrada é colocado em uma célula da fita da MT.*/

        // Adiciona um espaço em branco no fim da fita (símbolo vazio)
        fita.add('_');

        int posicaoCabecote = 0; // posição inicial do cabeçote de leitura
        String estadoAtual = estadoInicial;

        while (true) {
            // Se o cabeçote sair da fita para a esquerda, adicionamos um espaço
            if (posicaoCabecote < 0) {
                fita.add(0, '_');
                posicaoCabecote = 0;
            }
            // Se o cabeçote ultrapassar o tamanho da fita, adicionamos espaço no fim
            if (posicaoCabecote >= fita.size()) {
                fita.add('_');
            }

            char simboloAtual = fita.get(posicaoCabecote);

            // Recupera as transições para o estado atual
            Map<Character, Transicao> transicoesDoEstado = funcaoTransicao.get(estadoAtual);

            if (transicoesDoEstado == null) {
                // Se não existe nenhuma transição para este estado -> a máquina para
                break;
            }

            Transicao transicao = transicoesDoEstado.get(simboloAtual);
            // Está abrindo o Map que contém as transições e está procurando o simbolo atual

            if (transicao == null) {
                // Se não há transição definida para este símbolo → a máquina para
                break;
            }

            // Executa a transição:
            // 1. Escreve o símbolo na fita
            fita.set(posicaoCabecote, transicao.simboloEscrever);

            // 2. Atualiza o estado atual
            estadoAtual = transicao.estadoDestino;

            // 3. Move o cabeçote
            if (transicao.direcao == 'R') {
                posicaoCabecote++;
            } else if (transicao.direcao == 'L') {
                posicaoCabecote--;
            }
        }

        // A palavra é aceita se a máquina parou em um estado final
        return estadosFinais.contains(estadoAtual);
    }

    // ===================== Inicialização Linguagem 1 =====================
    public static MaquinaDeTuring criarMaquinaLinguagem1() {
        Set<String> finais = new HashSet<>();
        finais.add("aceita");
        finais.add("rejeita");
        MaquinaDeTuring mt = new MaquinaDeTuring("q0", finais);

        /* Lógica da Linguagem 1:
         * Marcar todos os 'a's como X e todos os 'b's como Y
         * Caso não consiga emparelhar a quantidade de 'a's e 'b's -> aceita
         * Caso consiga -> rejeita
         */
        mt.adicionarTransicao("q0", 'a', "q1", 'X', 'R');
        mt.adicionarTransicao("q0", 'b', "q2", 'Y', 'R');
        mt.adicionarTransicao("q0", 'X', "q0", 'X', 'R');
        mt.adicionarTransicao("q0", 'Y', "q0", 'Y', 'R');
        mt.adicionarTransicao("q0", '_', "rejeita", '_', 'R');

        mt.adicionarTransicao("q1", 'a', "q1", 'a', 'R');
        mt.adicionarTransicao("q1", 'b', "q0", 'Y', 'R');
        mt.adicionarTransicao("q1", 'X', "q1", 'X', 'R');
        mt.adicionarTransicao("q1", 'Y', "q1", 'Y', 'R');
        mt.adicionarTransicao("q1", '_', "aceita", '_', 'R');

        mt.adicionarTransicao("q2", 'b', "q2", 'b', 'R');
        mt.adicionarTransicao("q2", '_', "aceita", '_', 'R');

        return mt;
    }

    // ===================== Inicialização Linguagem 2 =====================
    public static MaquinaDeTuring criarMaquinaLinguagem2() {
        Set<String> finais = new HashSet<>();
        finais.add("aceita");
        finais.add("rejeita");
        MaquinaDeTuring mt = new MaquinaDeTuring("q0", finais);

        /* Lógica da Linguagem 2:
         * Emparelhar a↔c e b↔d
         * Aceita se quantidade de a=c e b=d
         */
        mt.adicionarTransicao("q0", 'a', "q1", 'X', 'R');
        mt.adicionarTransicao("q0", 'b', "q3", 'Y', 'R');
        mt.adicionarTransicao("q0", 'X', "q0", 'X', 'R');
        mt.adicionarTransicao("q0", 'Y', "q0", 'Y', 'R');
        mt.adicionarTransicao("q0", '_', "aceita", '_', 'R');

        mt.adicionarTransicao("q1", 'c', "q2", 'X', 'L');
        mt.adicionarTransicao("q1", 'a', "q1", 'a', 'R');
        mt.adicionarTransicao("q1", 'b', "q1", 'b', 'R');
        mt.adicionarTransicao("q1", 'X', "q1", 'X', 'R');
        mt.adicionarTransicao("q1", 'Y', "q1", 'Y', 'R');
        mt.adicionarTransicao("q1", '_', "rejeita", '_', 'R');

        mt.adicionarTransicao("q2", 'a', "q2", 'a', 'L');
        mt.adicionarTransicao("q2", 'b', "q2", 'b', 'L');
        mt.adicionarTransicao("q2", 'X', "q0", 'X', 'R');
        mt.adicionarTransicao("q2", 'Y', "q2", 'Y', 'L');

        mt.adicionarTransicao("q3", 'b', "q4", 'Y', 'R');
        mt.adicionarTransicao("q3", 'Y', "q3", 'Y', 'R');

        mt.adicionarTransicao("q4", 'd', "q5", 'Y', 'L');
        mt.adicionarTransicao("q4", 'b', "q4", 'b', 'R');

        mt.adicionarTransicao("q5", 'b', "q5", 'b', 'L');
        mt.adicionarTransicao("q5", 'Y', "q3", 'Y', 'R');
        mt.adicionarTransicao("q5", '_', "aceita", '_', 'R');

        return mt;
    }
}