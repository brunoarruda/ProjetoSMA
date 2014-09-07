package projeto.comportamento.gerente;

import java.util.List;

import projeto.modelo.Tarefa;
import projeto.modelo.TiposServico;

public class DividirServicoEmTarefas {
	
	public static List<Tarefa> Divir(String servico){
		List<Tarefa> tarefas = null;

		if(servico.equals(TiposServico.BUSCA_DISPONIBILIDADE_HOTEIS.toString())){
			tarefas = TiposServico.BUSCA_DISPONIBILIDADE_HOTEIS.getTarefa();
		}
		
		return tarefas;
	}

}
