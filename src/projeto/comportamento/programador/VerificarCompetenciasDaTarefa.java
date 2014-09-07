package projeto.comportamento.programador;

import java.util.EnumSet;
import projeto.modelo.Competencia;
import projeto.modelo.Tarefa;

public class VerificarCompetenciasDaTarefa {

	public static EnumSet<Competencia> Verificar(String tarefa) {

		if (tarefa.equals(Tarefa.TelaDeBusca.name())) {
			return Tarefa.TelaDeBusca.getCompetencia();
		} else if (tarefa.equals(Tarefa.GUI.name())) {
			return Tarefa.GUI.getCompetencia();
		}

		return null;
	}
}
