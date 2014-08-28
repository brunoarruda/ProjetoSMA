package projeto.comportamento.gerente;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RecebeSolicitacao extends CyclicBehaviour {

	public RecebeSolicitacao(Agent a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		ACLMessage solicitacao = myAgent.receive();
		if (solicitacao != null) {
			if (solicitacao.getSender().getLocalName().equals("Agente_Diretor")) {
				System.out.println(myAgent.getName() + ": Recebi do Diretor: "
						+ solicitacao.getContent());
			}
		}

		block();

	}

}
