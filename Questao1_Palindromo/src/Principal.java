import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma palavra sobre {a,b}: ");
        String palavra = sc.nextLine();

        // --- Algoritmo clássico ---
        Palindromo p = new Palindromo(palavra);
        boolean resultadoAlgoritmo = p.ehPalindromo();

        // --- Máquina de Turing simulada ---
        MaquinaTuringPalindromo objeto = new MaquinaTuringPalindromo(palavra);
        boolean resultadoMaquinaTuring = objeto.VerificaTuring();

        // Exibir resultados
        System.out.println("\n- RESULTADOS - ");
        System.out.println("Algoritmo classico: " + (resultadoAlgoritmo ? "Aceita" : "Rejeita"));
        System.out.println("Maquina de Turing: " + (resultadoMaquinaTuring ? "Aceita" : "Rejeita"));

        // Verificar equivalência
        if (resultadoAlgoritmo == resultadoMaquinaTuring) {
            System.out.println("Ambos os algoritmos deram o mesmo resultado (equivalentes).");
        } else {
            System.out.println("Os algoritmos divergiram.");
        }

        sc.close();
    }
}
