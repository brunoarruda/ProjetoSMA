package projeto.modelo;

import java.util.List;

public class Tarefa {

	private List<Competencia> competencias;
	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
	}

	private String nome;
	private int tempoDeImplementacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
