package projeto.agente;

import projeto.comportamento.secretaria.*;
import jade.core.behaviours.*;
import jade.core.Agent;


@SuppressWarnings("serial")
public class AgenteSecretaria extends Agent {
	
	private String agenteProgramador = "Bruno";
	private String msgPedidoDeServico = "*X* pediu ajuda, parece que é pra ontem.";
	private String agenteCliente;
	

	protected void setup() {
		
		System.out.println("Olá Sistema. ");
	    System.out.println("Meu nome é " + getLocalName());
	    System.out.println("Informo TUDO!\n");
		
				
		CyclicBehaviour ouvir = new ReceberPedido(this);
		addBehaviour(ouvir);
		
		//OneShotBehaviour comunicarMsgPedido = new Comunicar(agenteProgramador, msgPedidoDeServico);
		//addBehaviour(comunicarMsgPedido);
	}

	public String getAgenteProgramador() {
		return agenteProgramador;
	}

	public void setAgenteProgramador(String agenteProgramador) {
		this.agenteProgramador = agenteProgramador;
	}

	public String getAgenteCliente() {
		return agenteCliente;
	}

	public void setAgenteCliente(String agenteCliente) {
		this.agenteCliente = agenteCliente;
		msgPedidoDeServico = msgPedidoDeServico.replace("*X", agenteCliente);
	}
	
	

}
