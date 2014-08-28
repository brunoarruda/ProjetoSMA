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
			// Sleep para segurar o sistema enquanto o sniffer n�o inicia
			Thread.sleep(3000);

			// Crio a descri��o do servi�o que eu procuro
			ServiceDescription servicoProcurado = new ServiceDescription();
			// o servi�o que eu quero
			servicoProcurado
					.setType(TiposServico.BUSCA_DISPONIBILIDADE_HOTEIS
							.toString());
			// busco por quem ofere�a o servi�o que eu quero
			buscarServicos(servicoProcurado);

		} catch (Exception e) {
		}
	}

	private void buscarServicos(ServiceDescription servicoProcurado) {
		// A cada segundo tenta buscar por agentes que fornecam o servico
		addBehaviour(new BuscaServico(this, 1000, servicoProcurado));
	}

}