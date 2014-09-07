package projeto.modelo;

import java.util.ArrayList;
import java.util.List;

public enum TiposServico {
		BUSCA_DISPONIBILIDADE_HOTEIS(Tarefa.GUI, Tarefa.TelaDeBusca), 
		ORCAMENTO_HOTEL(Tarefa.GUI), 
		RESERVA_HOTEL, 
		PAGAMENTO_ONLINE,
		SUGESTAO_INTELIGENTE_HOTEIS(Tarefa.IA);
		
		private List<Tarefa> tarefas;
		
		TiposServico(Tarefa... tarefas){
			this.tarefas = new ArrayList<Tarefa>();
			for (Tarefa tarefa: tarefas){
				this.tarefas.add(tarefa);
			}
		}
		
		public List<Tarefa> getTarefa(){
			return this.tarefas;
		}
}
