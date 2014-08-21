package projeto.comportamento.secretaria;

import projeto.agente.AgenteSecretaria;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class ReceberPedido extends CyclicBehaviour {

	public ReceberPedido(AgenteSecretaria Agente) {
		super(Agente);
	}	

	@Override
	public void action() {		
		ACLMessage msg = this.myAgent.receive();
		if (msg != null) {
			String agenteCliente = msg.getSender().getName();
			((AgenteSecretaria) myAgent).setAgenteCliente(agenteCliente);		
		}		
		block();
	}
	
}
