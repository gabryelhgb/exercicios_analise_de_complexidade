import java.util.ArrayList;

public class Iterativo {

    public static void resolverHanoi(int quantidadeDiscos, char nomeOrigem, char nomeDestino, char nomeAuxiliar) {
        //três listas para representar os três pinos
        ArrayList<Integer> origem = new ArrayList<>();
        ArrayList<Integer> destino = new ArrayList<>();
        ArrayList<Integer> auxiliar = new ArrayList<>();

        // Adicionamos os discos no pino de origem, do maior (embaixo) para o menor (em cima)
        for (int i = quantidadeDiscos; i >= 1; i--) {
            origem.add(i);
        }

        // Calculamos o número total de movimentos necessários usando 2^n - 1; se forem 3 discos daria 8 - 1 = 7 movimentos
        int totalDeMovimentos = (1 << quantidadeDiscos) - 1;

        // Se o número de discos for par, trocamos o destino com o auxiliar
        // Isso é necessário porque a ordem dos movimentos muda quando o número de discos é par
        if (quantidadeDiscos % 2 == 0) {
            ArrayList<Integer> temporario = destino;
            destino = auxiliar;
            auxiliar = temporario;

            char tempChar = nomeDestino;
            nomeDestino = nomeAuxiliar;
            nomeAuxiliar = tempChar;
        }
        // Executamos os movimentos um por um
        for (int movimento = 1; movimento <= totalDeMovimentos; movimento++) {
            // A cada movimento, decidimos quais pinos vão interagir:
            if (movimento % 3 == 1) {
                mover(origem, destino, nomeOrigem, nomeDestino); //movimento 1
            } else if (movimento % 3 == 2) {
                mover(origem, auxiliar, nomeOrigem, nomeAuxiliar); //movimento 2
            } else {
                mover(auxiliar, destino, nomeAuxiliar, nomeDestino); //movimento 3
            }
        }
    }
    // realiza o movimento entre dois pinos, respeitando a regra de nunca colocar disco maior sobre disco menor
    private static void mover(ArrayList<Integer> pinoOrigem, ArrayList<Integer> pinoDestino, char nomeOrigem, char nomeDestino) {
        // Verificamos o disco no topo de cada pino
        // Se o pino estiver vazio, usamos um valor bem grande (Integer.MAX_VALUE) para evitar erro
        int topoOrigem = pinoOrigem.isEmpty() ? Integer.MAX_VALUE : pinoOrigem.get(pinoOrigem.size() - 1);
        int topoDestino = pinoDestino.isEmpty() ? Integer.MAX_VALUE : pinoDestino.get(pinoDestino.size() - 1);

        // Se o disco da origem for menor que o da destino, podemos mover
        if (topoOrigem < topoDestino) {
            // Remove o disco do topo da origem e adiciona no destino
            pinoDestino.add(pinoOrigem.remove(pinoOrigem.size() - 1));
            System.out.println("Mover disco do pino " + nomeOrigem + " para o pino " + nomeDestino);
        } else {
            // Caso contrário, movemos o disco do destino para a origem
            pinoOrigem.add(pinoDestino.remove(pinoDestino.size() - 1));
            System.out.println("Mover disco do pino " + nomeDestino + " para o pino " + nomeOrigem);
        }
    }
}
