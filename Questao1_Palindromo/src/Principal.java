import java.util.Scanner;

public class Principal 
{
	public static void main(String[] args) 
	{
        Scanner sc = new Scanner(System.in);
        Palindromo verificaPalindromo = new Palindromo();

        System.out.print("Digite uma palavra sobre {a,b}: ");
        String palavra = sc.nextLine();
        
        if (verificaPalindromo.ehPalindromo(palavra)) {
            System.out.println("Eh palindromo!");
        } else {
            System.out.println("Nao eh palindromo!");
        }
        sc.close();
	}
}