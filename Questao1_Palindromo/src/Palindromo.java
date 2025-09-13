public class Palindromo 
{
	public static boolean ehPalindromo(String palavra){
		char[] letras = palavra.toCharArray();
		
		int i = 0;
		int j = letras.length - 1;
		
		while (i < j){
			if (letras[i] != letras[j]){ // Verifica se as letras que está em i e j são iguais
				return false; //Se não for igual, não é palíndromo
			}
			i++;
			j--;
		}
		return true; //Se percorreu tudo sem falha
	}
}