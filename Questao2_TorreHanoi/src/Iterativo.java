public class Iterativo {

    // Método iterativo que resolve as Torres de Hanói
    public static void resolverHanoi(int quantidadeDiscos, char pinoOrigem, char pinoDestino, char pinoAuxiliar) {
        int totalDeMovimentos = (int) (Math.pow(2, quantidadeDiscos) - 1);

        // Se a quantidade de discos for par, inverte destino e auxiliar
        if (quantidadeDiscos % 2 == 0) {
            char temporario = pinoDestino;
            pinoDestino = pinoAuxiliar;
            pinoAuxiliar = temporario;
        }

        // Representação dos pinos em um array
        char[] pinos = {pinoOrigem, pinoDestino, pinoAuxiliar};

        for (int movimento = 1; movimento <= totalDeMovimentos; movimento++) {
            int origem, destino;

            if (movimento % 3 == 1) {
                origem = 0; destino = 1;
            } else if (movimento % 3 == 2) {
                origem = 0; destino = 2;
            } else {
                origem = 2; destino = 1;
            }

            System.out.println("Mover disco do pino " + pinos[origem] + " para o pino " + pinos[destino]);
        }
    }
}