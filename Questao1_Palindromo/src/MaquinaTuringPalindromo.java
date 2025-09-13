import java.util.*;

public class MaquinaTuringPalindromo{
	
	enum Movimento {ESQUERDA, DIREITA, PARADO}
	enum Estado {q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10}

	static class Transicao{
		Estado novoEstado;
		char escreve;
		Movimento move;
		
		Transicao(Estado novoEstado, char escreve, Movimento move){
			this.novoEstado = novoEstado;
			this.escreve = escreve;
			this.move = move;
		}
	}
	
	private ArrayList<Character> fita;
	private int cabeca;
	private Estado estadoAtual;
	private Map<String, Transicao> delta;
	
	public MaquinaTuringPalindromo (String palavra) {
		fita = new ArrayList<>();
		for (char c : palavra.toCharArray()) {
			fita.add(c);
		}
		fita.add('_'); //Espaço em branco no final
		cabeca = 0;
		estadoAtual = Estado.q0;
		delta = new HashMap<>();
		inicializarTransicoes();
	}
	
	private String chave (Estado estado, char leitura) {
		return estado + "," + leitura;
	}
	
	private void inicializarTransicoes() {
		
	}
}