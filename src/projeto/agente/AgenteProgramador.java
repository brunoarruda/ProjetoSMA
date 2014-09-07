package projeto.agente;

import java.util.EnumSet;

import projeto.comportamento.programador.*;
import projeto.modelo.*;
import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteProgramador extends Agent {
	private EnumSet<Competencia> competencias;
	private int tempoOcupado = 0;

	protected void setup() {
		Object[] args = getArguments();
		competencias = (EnumSet<Competencia>) args[0];
		addBehaviour(new Delibera(this));
	}

	public EnumSet<Competencia> getCompetencias() {
		return competencias;
	}

	public int getTempoOcupado() {
		return tempoOcupado;
	}

	public void setTempoOcupado(int tempoOcupado) {
		this.tempoOcupado = tempoOcupado;
	}
}