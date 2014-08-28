package projeto.agente;

import projeto.comportamento.diretor.*;
import projeto.modelo.TiposServico;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

@SuppressWarnings("serial")
public class AgenteDiretor extends Agent {

	private String agenteProgramador = "Bruno";
	private String msgPedidoDeServico = "*X* pediu ajuda, parece que é pra ontem.";
	private String agenteCliente;

	protected void setup() {

		System.out.println("Olá Sistema. ");
		System.out.println("Meu nome é " + getLocalName());
		System.out.println("Informo TUDO!\n");

		/*
		 * Este tempo de sincronização importante para dar um tempo para o
		 * agente Sniffer iniciar suas atividades.
		 */
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}


	      DFAgentDescription dfd = new DFAgentDescription();
	      
		//Registra todos os serviços que ele disponibiliza
		for(TiposServico tipo : TiposServico.values()){
			//Descricao do servico
			ServiceDescription servico = new ServiceDescription();
			//Todos os serviços que a empresa disponibiliza
			servico.setType(tipo.toString());
			servico.setName(this.getLocalName());
			
			dfd.addServices(servico);
			
		}
		
		registraServico(dfd);
		
		String resposta = "Assim que ficar pronto exibiremos o cronograma!";
		addBehaviour(new RecebePedido(this, resposta));

	}

	 //metodo para registrar servico
	   protected void registraServico(DFAgentDescription dfd) 
	   {
	      try 
	      {
	         DFService.register(this, dfd);
	      } 
	      catch (FIPAException e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	
//
//	public void setAgenteCliente(String agenteCliente) {
//		this.agenteCliente = agenteCliente;
//		msgPedidoDeServico = msgPedidoDeServico.replace("*X", agenteCliente);
//	}

}
