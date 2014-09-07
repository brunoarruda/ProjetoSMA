package projeto.comportamento.gerente;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import projeto.agente.AgenteProgramador;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

@SuppressWarnings("serial")
public class IniciadorContractNet extends ContractNetInitiator {

	private int quantidadeTotalProgramadores;

	public IniciadorContractNet(Agent a, ACLMessage cfp, int quantidadeProgramadores) {
		super(a, cfp);
		quantidadeTotalProgramadores = quantidadeProgramadores;
	}

	protected void handleFailure(ACLMessage failure) {
		if (failure.getSender().equals(myAgent.getAMS())) {
			// FAILURE notification from the JADE runtime: the receiver
			// does not exist
			System.out.println("Responder does not exist");
		} else {
			System.out.println("Agent " + failure.getSender().getName() + " failed");
		}
		// Immediate failure --> we will not receive a response from this agent
		quantidadeTotalProgramadores--;
	}

	/*
	 * (non-Javadoc) Método chamado quando todas as respostas são recebidas ou o
	 * tempo expira
	 * 
	 * @see jade.proto.ContractNetInitiator#handleAllResponses(java.util.Vector,
	 * java.util.Vector)
	 */
	protected void handleAllResponses(Vector responses, Vector acceptances) {
		if (responses.size() < quantidadeTotalProgramadores) {
			// Algum participante não respondeu a tempo
			System.out.println("Tempo Expirou: Faltando: " + (quantidadeTotalProgramadores - responses.size()) + " respostas");
		}

		AID programadorMaisAdequado = null;
		ACLMessage accept = null;
		Enumeration e = responses.elements();

		String tarefaProgramador = "";
		float melhorEsforco = 0;
		int melhorTempoOcupado = Integer.MAX_VALUE;

		while (e.hasMoreElements()) {
			ACLMessage msg = (ACLMessage) e.nextElement();
			if (msg.getPerformative() == ACLMessage.PROPOSE) {

				String[] respostaDoProgramador = msg.getContent().split(":");

				String tarefa = respostaDoProgramador[0];
				float esforco = Float.parseFloat(respostaDoProgramador[1]);
				int tempoOcupado = Integer.parseInt(respostaDoProgramador[2]);

				// Caso o tempo ocupado do programador seja menor que o dos
				// outros
				if (tempoOcupado <= melhorTempoOcupado) {
					// utiliza o esforco como criterio de desempate
					if (esforco > melhorEsforco) {
						if (accept != null) {
							accept.setPerformative(ACLMessage.REJECT_PROPOSAL);
							acceptances.add(accept);
						}
						accept = msg.createReply();
						programadorMaisAdequado = msg.getSender();
						melhorEsforco = esforco;
						melhorTempoOcupado = tempoOcupado;
						tarefaProgramador = tarefa;
					}
				}
			}
		}

		// Agora o gerente sabe quem é o programador mais adequado

		if (accept != null) {
			System.out.println("Aceitando a proposta do " + programadorMaisAdequado.getLocalName() + " que foi: ");
			System.out.println("Tarefa: "+ tarefaProgramador + " Esforco "+ melhorEsforco + " Tempo: " + melhorTempoOcupado + " tempo");
			accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
			acceptances.add(accept);
		}

	}

	protected void handleInform(ACLMessage inform) {
		System.out.println("Agent " + inform.getSender().getName() + " successfully performed the requested action");
	}
}
