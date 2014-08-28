package projeto.comportamento.diretor;

import projeto.agente.AgenteDiretor;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RecebePedido extends CyclicBehaviour {

	private String resposta;
	private String agenteGerente = "Agente_Gerente";
	private String language = "Portugues";
	
	public RecebePedido(AgenteDiretor Agente, String resp) {
		super(Agente);
		this.resposta = resp;
	}

	@Override
	public void action() {
		ACLMessage mensagemCliente = myAgent.receive();
		if (mensagemCliente != null) {
			System.out.println(myAgent.getName() + ": Vou passar para o Gerente fazer: "
					+ mensagemCliente.getContent());
			ACLMessage reply = mensagemCliente.createReply();
			reply.setContent(resposta);
			myAgent.send(reply);
			
			
			//Avisar o gerente sobre o serviço requisitado
			ACLMessage avisoParaGerente = new ACLMessage(ACLMessage.REQUEST);
			avisoParaGerente.setSender(myAgent.getAID());
			avisoParaGerente.addReceiver(new AID(agenteGerente, AID.ISLOCALNAME));
			avisoParaGerente.setLanguage(language);
			avisoParaGerente.setContent("InserirBackLog: " + mensagemCliente.getContent());
			
			myAgent.send(avisoParaGerente);
		}

		block();
	}

}
