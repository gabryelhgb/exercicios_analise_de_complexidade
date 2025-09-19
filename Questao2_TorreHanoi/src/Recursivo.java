public class Recursivo {

    // Método recursivo que resolve as Torres de Hanói
    public static void resolverHanoi(int quantidadeDiscos, char pinoOrigem, char pinoDestino, char pinoAuxiliar) {
        if (quantidadeDiscos == 1) {
            System.out.println("Mover disco 1 do pino " + pinoOrigem + " para o pino " + pinoDestino);
        } else {
            // Move n-1 discos para o pino auxiliar
            resolverHanoi(quantidadeDiscos - 1, pinoOrigem, pinoAuxiliar, pinoDestino);

            // Move o maior disco para o pino destino
            System.out.println("Mover disco " + quantidadeDiscos + " do pino " + pinoOrigem + " para o pino " + pinoDestino);

            // Move n-1 discos para o pino destino
            resolverHanoi(quantidadeDiscos - 1, pinoAuxiliar, pinoDestino, pinoOrigem);
        }
    }
}