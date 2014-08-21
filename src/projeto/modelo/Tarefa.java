package projeto.modelo;

public class Tarefa {
	
	private int dificuldade;
	private String nome;
	private int tempoDeImplementacao;
	
	public int getDificuldade(){
		return dificuldade;
	}
	
	public String getNome(){
		return nome;
	}
	
	public int getTempo(){
		return tempoDeImplementacao;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTempo(int tempoDeImplementacao) {
		this.tempoDeImplementacao = tempoDeImplementacao;
	}
	
	
}
