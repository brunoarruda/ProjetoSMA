package projeto.agente;

import projeto.comportamento.cliente.*;
import projeto.modelo.TiposServico;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;

@SuppressWarnings("serial")
public class AgenteCliente extends Agent {

	
	private String agenteContato = "Agente_Diretor";
	
	private String msgPedidoDeServico = "ME AJUDAAAAAAA!";

	protected void setup() {
		try {
			// Sleep para segurar o sistema enquanto o sniffer não inicia
			Thread.sleep(3000);

			// Crio a descrição do serviço que eu procuro
			ServiceDescription servicoProcurado = new ServiceDescription();
			// o serviço que eu quero
			servicoProcurado
					.setType(TiposServico.BUSCA_DISPONIBILIDADE_HOTEIS
							.toString());
			// busco por quem ofereça o serviço que eu quero
			buscarServicos(servicoProcurado);

		} catch (Exception e) {
		}
	}

	private void buscarServicos(ServiceDescription servicoProcurado) {
		// A cada segundo tenta buscar por agentes que fornecam o servico
		addBehaviour(new BuscaServico(this, 1000, servicoProcurado));
	}

}