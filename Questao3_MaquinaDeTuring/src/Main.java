import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cria as máquinas de Turing para as duas linguagens
        MaquinaDeTuring maquinaL1 = MaquinaDeTuring.criarMaquinaLinguagem1();
        MaquinaDeTuring maquinaL2 = MaquinaDeTuring.criarMaquinaLinguagem2();

        boolean continuar = true;
        while (continuar) {
            // ==================== Linguagem 1 ====================
            System.out.println("Linguagem 1: { a^m b^n | m ≠ n }");
            System.out.println("Digite a palavra para testar na Linguagem 1:");
            String entradaL1 = scanner.nextLine();

            boolean aceitaL1 = maquinaL1.executar(entradaL1);
            if (aceitaL1) {
                System.out.println("A palavra '" + entradaL1 + "' eh ACEITA pela Linguagem 1.");
            } else {
                System.out.println("A palavra '" + entradaL1 + "' eh REJEITADA pela Linguagem 1.");
            }

            // ==================== Linguagem 2 ====================
            System.out.println("\nLinguagem 2: { a^n b^k c^n d^k | n,k ≥ 0 }");
            System.out.println("Digite a palavra para testar na Linguagem 2:");
            String entradaL2 = scanner.nextLine();

            boolean aceitaL2 = maquinaL2.executar(entradaL2);
            if (aceitaL2) {
                System.out.println("A palavra '" + entradaL2 + "' eh ACEITA pela Linguagem 2.");
            } else {
                System.out.println("A palavra '" + entradaL2 + "' eh REJEITADA pela Linguagem 2.");
            }

            // Pergunta se o usuário quer testar outra palavra
            System.out.println("\nDeseja testar outra palavra? (s/n)");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                continuar = false;
            }
            System.out.println("\n--------------------------------------\n");
        }

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}