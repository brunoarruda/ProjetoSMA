package projeto.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Tarefa_NOVO {
	
	//inicialização das tarefas
	GUI(1, Competencia.BACKEND, Competencia.LAYOUT), 
	IA(1, Competencia.REGRA_DE_NEGOCIO, Competencia.TRATAMENTO_ESTATISTICO, Competencia.AI);
	
	//constantes de cada tarefa
	private final int tempoDeImplementacao;
	private List<Competencia> competencias;
	
	//construtor
	Tarefa_NOVO(int tempo, Competencia ... competencias){
		
		this.tempoDeImplementacao = tempo;
		
		//Continuei usando lista para não alterar o projeto feito em dupla
		this.competencias = new ArrayList<Competencia>();
		for (Competencia competencia :competencias){
			this.competencias.add(competencia);
		}
	}
	
	public int getTempo(){
		return tempoDeImplementacao;
	}
	
	public List<Competencia> getCompetencia(){
		return this.competencias;
	}

}
