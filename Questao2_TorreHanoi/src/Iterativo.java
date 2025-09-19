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
            	origem = 1; destino = 2;
            }
            
            /*Precisa resolver esse algoritmo, a sequencia de if else está fazendo a colocar disco maior
            Em cima de disco menor, entretanto precisamos que ele não coloque discos maiores sobre menores
            É possível resolver, entretanto a quantidade de linhas de código aumenta, e a complexidade também*/

            System.out.println("Mover disco do pino " + pinos[origem] + " para o pino " + pinos[destino]);
        }
    }
}