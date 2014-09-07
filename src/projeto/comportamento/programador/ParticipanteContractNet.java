package projeto.comportamento.programador;

import java.util.EnumSet;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;
import projeto.agente.AgenteProgramador;
import projeto.modelo.Competencia;

@SuppressWarnings("serial")
public class ParticipanteContractNet extends ContractNetResponder {

	public ParticipanteContractNet(Agent a, MessageTemplate mt) {
		super(a, mt);

	}

	@Override
	protected ACLMessage handleCfp(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
		String tarefa = cfp.getContent();

		// O agente verifica se tem todas as competencias para realizar a tarefa
		// oferecida

		EnumSet<Competencia> competenciasNecessarias = VerificarCompetenciasDaTarefa.Verificar(tarefa);
		EnumSet<Competencia> competenciasDoProgramador = ((AgenteProgramador) myAgent).getCompetencias();

		if (competenciasDoProgramador.containsAll(competenciasNecessarias)) {
			float esforco = (float) (competenciasNecessarias.size() / (float) competenciasDoProgramador.size());
			int tempoOcupado = ((AgenteProgramador) myAgent).getTempoOcupado();

			// TODO: Calcular o tempo de implementacao de acordo com as
			// competências do programador
			tempoOcupado += competenciasNecessarias.size();

			String proposta = tarefa + ":" + esforco + ":" + tempoOcupado;
			// O agente aceita a proposta
			System.out.println("Agente " + myAgent.getLocalName() + ": Propoe " + proposta);
			ACLMessage propose = cfp.createReply();
			propose.setPerformative(ACLMessage.PROPOSE);
			propose.setContent(proposta);
			return propose;
		} else {
			// Recusa a proposta de realizar a tarefa
			// System.out.println("Agent " + myAgent.getLocalName() +
			// ": Recusou");
			throw new RefuseException("evaluation-failed");
		}
	}

	@Override
	protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
		System.out.println("Agent " + myAgent.getLocalName() + ": Proposal accepted");
		if (performAction()) {
			System.out.println("Agent " + myAgent.getLocalName() + ": Action successfully performed");
			ACLMessage inform = accept.createReply();
			inform.setPerformative(ACLMessage.INFORM);
			return inform;
		} else {
			System.out.println("Agent " + myAgent.getLocalName() + ": Action execution failed");
			throw new FailureException("unexpected-error");
		}
	}

	protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
		System.out.println("Agent " + myAgent.getLocalName() + ": Proposal rejected");
	}

	public int evaluateAction() {
		// Simulate an evaluation by generating a random number
		return (int) (Math.random() * 10);
	}

	public boolean performAction() {
		// Simulate action execution by generating a random number
		return (Math.random() > 0.2);
	}

}
