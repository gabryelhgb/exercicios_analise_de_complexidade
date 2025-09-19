public class Principal {
    public static void main(String[] args) {
        int numeroDeDiscos = 3; // Altere aqui para testar outras quantidades

        System.out.println("=====================================");
        System.out.println("       Torres de Hanoi Recursivo     ");
        System.out.println("=====================================");
        Recursivo.resolverHanoi(numeroDeDiscos, 'A', 'C', 'B');

        System.out.println("\n=====================================");
        System.out.println("       Torres de Hanoi Iterativo     ");
        System.out.println("=====================================");
        Iterativo.resolverHanoi(numeroDeDiscos, 'A', 'C', 'B');
    }
}