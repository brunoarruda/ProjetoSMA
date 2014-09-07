package projeto.comportamento.gerente;

import java.util.Date;
import java.util.List;

import projeto.modelo.Tarefa;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RecebeSolicitacao extends CyclicBehaviour {

	private String[] Programadores;

	public RecebeSolicitacao(Agent a, String[] nomesProgramdores) {
		super(a);
		this.Programadores = nomesProgramdores;

	}

	@Override
	public void action() {
		ACLMessage solicitacao = myAgent.receive();
		if (solicitacao != null) {
			if (solicitacao.getSender().getLocalName().equals("Agente_Diretor")) {
				System.out.println(myAgent.getName() + ": Recebi do Diretor: " + solicitacao.getContent());

				String[] servico = solicitacao.getContent().split(":");

				List<Tarefa> tarefasParaRealizar = DividirServicoEmTarefas.Divir(servico[1]);

				// Sabemos quais tarefas deve ser realizadas pelos programadores

				/*
				 * Para cada tarefa, precisamos iniciar um ContractNet
				 */

				for (Tarefa t : tarefasParaRealizar) {
					System.out.println("Tarefa:" + t.name());

					// Criamos uma nova mensagem
					ACLMessage msg = new ACLMessage(ACLMessage.CFP);
					for (int i = 0; i < Programadores.length; ++i) {
						msg.addReceiver(new AID((String) Programadores[i], AID.ISLOCALNAME));
					}

					msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
					// We want to receive a reply in 5 secs
					msg.setReplyByDate(new Date(System.currentTimeMillis() + 5000));
					msg.setContent(t.name());

					myAgent.addBehaviour(new IniciadorContractNet(myAgent, msg, Programadores.length));
				}
			}
		}
		block();

	}

}
