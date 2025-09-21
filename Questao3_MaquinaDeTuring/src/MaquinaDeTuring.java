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
        List<Character> fita = new ArrayList<>();
        for (char c : entrada.toCharArray()) {
            fita.add(c);
        }
        
        /* entrada.toCharArray() -> converte a String entrada em um array de caracteres.
         * O for (char c : ...) percorre cada caractere da entrada, um por um.
         * A cada iteração, c recebe o caractere atual. 
         * Ou seja, cada caractere da string de entrada é colocado em uma célula da fita da MT.*/
        
        fita.add('_'); // espaço em branco no fim da fita

        int posicaoCabecote = 0;
        String estadoAtual = estadoInicial;

        while (true) {
            if (posicaoCabecote < 0) {
                fita.add(0, '_');
                posicaoCabecote = 0;
            }
            if (posicaoCabecote >= fita.size()) {
                fita.add('_');
            }

            char simboloAtual = fita.get(posicaoCabecote);
            Map<Character, Transicao> transicoesDoEstado = funcaoTransicao.get(estadoAtual);

            if (transicoesDoEstado == null) break;
            Transicao transicao = transicoesDoEstado.get(simboloAtual);
            if (transicao == null) break;

            fita.set(posicaoCabecote, transicao.simboloEscrever);
            estadoAtual = transicao.estadoDestino;

            if (transicao.direcao == 'R') posicaoCabecote++;
            else if (transicao.direcao == 'L') posicaoCabecote--;
        }

        // Agora, aceita apenas se terminou em "aceita"
        return estadoAtual.equals("aceita");
    }

    // ===================== Inicialização Linguagem 1 =====================
    public static MaquinaDeTuring criarMaquinaLinguagem1() {
        Set<String> finais = new HashSet<>();
        finais.add("aceita");
        finais.add("rejeita");
        finais.add("q5");
        
        MaquinaDeTuring mt = new MaquinaDeTuring("q0", finais);

        /* Lógica da Linguagem 1:
         * Marcar todos os 'a's como X e todos os 'b's como Y
         * Caso não consiga emparelhar a quantidade de 'a's e 'b's -> aceita
         * Caso consiga -> rejeita
         */
        
        //Estado atual -> Simbolo lido -> Próximo estado -> Simbolo escrito -> Direção
        
        // Transições do estado q0
        mt.adicionarTransicao("q0", 'y', "q0", 'y', 'R');
        mt.adicionarTransicao("q0", 'x', "q0", 'x', 'R');
        mt.adicionarTransicao("q0", 'a', "q1", 'x', 'R');
        mt.adicionarTransicao("q0", 'b', "q2", 'y', 'R');
        mt.adicionarTransicao("q0", '_', "q3", '_', 'R');

        // Transições do estado q1
        mt.adicionarTransicao("q1", 'x', "q1", 'x', 'R');
        mt.adicionarTransicao("q1", 'y', "q1", 'y', 'R');
        mt.adicionarTransicao("q1", 'a', "q1", 'a', 'R');
        mt.adicionarTransicao("q1", 'b', "q4", 'y', 'L');
        mt.adicionarTransicao("q1", '_', "q5", '_', 'R');

        // Transições do estado q2
        mt.adicionarTransicao("q2", 'y', "q2", 'y', 'R');
        mt.adicionarTransicao("q2", 'x', "q2", 'x', 'R');
        mt.adicionarTransicao("q2", 'b', "q2", 'b', 'R');
        mt.adicionarTransicao("q2", 'a', "q4", 'x', 'L');
        mt.adicionarTransicao("q2", '_', "q5", '_', 'R');

        // Transição do estado q3
        mt.adicionarTransicao("q3", 'y', "q3", 'y', 'L');
        mt.adicionarTransicao("q3", 'x', "q3", 'x', 'L');
        mt.adicionarTransicao("q3", 'b', "q5", 'b', 'R');
        mt.adicionarTransicao("q3", 'a', "q5", 'a', 'R');

        // Transições do estado q4
        mt.adicionarTransicao("q4", 'y', "q4", 'y', 'L');
        mt.adicionarTransicao("q4", 'x', "q4", 'x', 'L');
        mt.adicionarTransicao("q4", 'b', "q4", 'b', 'L');
        mt.adicionarTransicao("q4", 'a', "q4", 'a', 'L');
        mt.adicionarTransicao("q4", '_', "q0", '_', 'R');

        // Transições do estado q5
        mt.adicionarTransicao("q5", '_', "aceita", '_', 'R');

        return mt;
    }

    // ===================== Inicialização Linguagem 2 =====================
    public static MaquinaDeTuring criarMaquinaLinguagem2() {
        Set<String> finais = new HashSet<>();
        finais.add("aceita");
        finais.add("rejeita");
        finais.add("q3");
        MaquinaDeTuring mt = new MaquinaDeTuring("q0", finais);

        /* Lógica da Linguagem 2:
         * Emparelhar a=c e b=d
         * Aceita se quantidade de a=c e b=d
         */
        mt.adicionarTransicao("q0", 'x', "q0", 'x', 'R');
        mt.adicionarTransicao("q0", 'y', "q0", 'y', 'R');
        mt.adicionarTransicao("q0", 'b', "q0", 'b', 'R');
        mt.adicionarTransicao("q0", 'd', "q0", 'd', 'R');
        mt.adicionarTransicao("q0", 'a', "q1", 'x', 'R');
        mt.adicionarTransicao("q0", 'c', "q2", 'y', 'R');
        mt.adicionarTransicao("q0", '_', "q10", '_', 'L');

        mt.adicionarTransicao("q1", 'd', "q1", 'd', 'R');
        mt.adicionarTransicao("q1", 'b', "q1", 'b', 'R');
        mt.adicionarTransicao("q1", 'y', "q1", 'y', 'R');
        mt.adicionarTransicao("q1", 'x', "q1", 'x', 'R');
        mt.adicionarTransicao("q1", 'a', "q1", 'a', 'R');
        mt.adicionarTransicao("q1", 'c', "q4", 'y', 'L');

        mt.adicionarTransicao("q2", 'd', "q2", 'd', 'R');
        mt.adicionarTransicao("q2", 'b', "q2", 'b', 'R');
        mt.adicionarTransicao("q2", 'c', "q2", 'c', 'R');
        mt.adicionarTransicao("q2", 'y', "q2", 'y', 'R');
        mt.adicionarTransicao("q2", 'x', "q2", 'x', 'R');
        mt.adicionarTransicao("q2", 'a', "q4", 'x', 'L');

        mt.adicionarTransicao("q3", '_', "aceita", '_', 'R');

        mt.adicionarTransicao("q4", 'd', "q4", 'd', 'L');
        mt.adicionarTransicao("q4", 'b', "q4", 'b', 'L');
        mt.adicionarTransicao("q4", 'y', "q4", 'y', 'L');
        mt.adicionarTransicao("q4", 'c', "q4", 'c', 'L');
        mt.adicionarTransicao("q4", 'a', "q4", 'a', 'L');
        mt.adicionarTransicao("q4", 'x', "q4", 'x', 'L');
        mt.adicionarTransicao("q4", '_', "q0", '_', 'R');

        mt.adicionarTransicao("q5", 'y', "q5", 'y', 'R');
        mt.adicionarTransicao("q5", 'x', "q5", 'x', 'R');
        mt.adicionarTransicao("q5", 'b', "q5", 'b', 'R');
        mt.adicionarTransicao("q5", 'k', "q5", 'k', 'R');
        mt.adicionarTransicao("q5", 'l', "q5", 'l', 'R');
        mt.adicionarTransicao("q5", 'd', "q6", 'l', 'L');
        
        mt.adicionarTransicao("q6", 'y', "q6", 'y', 'L');
        mt.adicionarTransicao("q6", 'x', "q6", 'x', 'L');
        mt.adicionarTransicao("q6", 'd', "q6", 'd', 'L');
        mt.adicionarTransicao("q6", 'b', "q6", 'b', 'L');
        mt.adicionarTransicao("q6", 'l', "q6", 'l', 'L');
        mt.adicionarTransicao("q6", 'k', "q6", 'k', 'L');
        mt.adicionarTransicao("q6", '_', "q8", '_', 'R');
        
        
        mt.adicionarTransicao("q7", 'y', "q7", 'y', 'R');
        mt.adicionarTransicao("q7", 'x', "q7", 'x', 'R');
        mt.adicionarTransicao("q7", 'd', "q7", 'd', 'R');
        mt.adicionarTransicao("q7", 'l', "q7", 'l', 'R');
        mt.adicionarTransicao("q7", 'k', "q7", 'k', 'R');
        mt.adicionarTransicao("q7", 'b', "q6", 'k', 'L');
        
        mt.adicionarTransicao("q8", 'y', "q8", 'y', 'R');
        mt.adicionarTransicao("q8", 'x', "q8", 'x', 'R');
        mt.adicionarTransicao("q8", 'l', "q8", 'l', 'R');
        mt.adicionarTransicao("q8", 'k', "q8", 'k', 'R');
        mt.adicionarTransicao("q8", 'b', "q5", 'k', 'R');
        mt.adicionarTransicao("q8", '_', "q3", '_', 'R');
        
        mt.adicionarTransicao("q10", 'y', "q10", 'y', 'L');
        mt.adicionarTransicao("q10", 'x', "q10", 'x', 'L');
        mt.adicionarTransicao("q10", 'd', "q10", 'd', 'L');
        mt.adicionarTransicao("q10", 'c', "q10", 'c', 'L');
        mt.adicionarTransicao("q10", 'b', "q10", 'b', 'L');
        mt.adicionarTransicao("q10", 'a', "q10", 'a', 'L');
        mt.adicionarTransicao("q10", '_', "q8", '_', 'R');

        return mt;
    }
}