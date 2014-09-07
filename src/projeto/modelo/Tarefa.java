package projeto.modelo;

import java.util.EnumSet;

public enum Tarefa {
	
	//inicialização das tarefas
	TelaDeBusca(0, Competencia.FRONTEND, Competencia.LAYOUT, Competencia.JAVASCRIPT),
	GUI(0, Competencia.BACKEND, Competencia.LAYOUT), 
	IA(0, Competencia.REGRA_DE_NEGOCIO, Competencia.TRATAMENTO_ESTATISTICO, Competencia.AI);
	
	//constantes de cada tarefa
	private final int tempoDeImplementacao;
	private EnumSet competencias;
	
	//construtor
	Tarefa(int tempo, Competencia competencia, Competencia ... competencias){
		this.tempoDeImplementacao = tempo;
		this.competencias = EnumSet.of(competencia);
		for (Competencia competenciaExtra :competencias){
			this.competencias.add(competenciaExtra);
		}
	}
	
	public int getTempo(){
		return tempoDeImplementacao;
	}
	
	public EnumSet<Competencia> getCompetencia(){
		return this.competencias;
	}

}
