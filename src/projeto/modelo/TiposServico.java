package projeto.modelo;

import java.util.ArrayList;
import java.util.List;

public enum TiposServico {
		BUSCA_DISPONIBILIDADE_HOTEIS(Tarefa_NOVO.GUI), 
		ORCAMENTO_HOTEL(Tarefa_NOVO.GUI), 
		RESERVA_HOTEL, 
		PAGAMENTO_ONLINE,
		SUGESTAO_INTELIGENTE_HOTEIS(Tarefa_NOVO.IA);
		
		private List<Tarefa_NOVO> tarefas;
		
		TiposServico(Tarefa_NOVO... tarefas){
			this.tarefas = new ArrayList<Tarefa_NOVO>();
			for (Tarefa_NOVO tarefa: tarefas){
				this.tarefas.add(tarefa);
			}
		}
		
		public List<Tarefa_NOVO> getTarefa(){
			return this.tarefas;
		}
}
