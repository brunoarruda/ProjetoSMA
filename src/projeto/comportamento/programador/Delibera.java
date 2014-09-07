package projeto.comportamento.programador;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import projeto.comportamento.programador.*;

@SuppressWarnings("serial")
public class Delibera extends CyclicBehaviour {

	@Override
	public void onStart() {
		MessageTemplate template = MessageTemplate.and(
				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET),
				MessageTemplate.MatchPerformative(ACLMessage.CFP) );

		myAgent.addBehaviour(new ParticipanteContractNet(myAgent, template));
	}

	public Delibera(Agent a) {
		super(a);
		
	}

	@Override
	public void action() {    	

	}

}
