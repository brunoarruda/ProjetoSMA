package projeto.comportamento.cliente;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class BuscaServico extends TickerBehaviour {

	private ServiceDescription servicoProcurado;

	public BuscaServico(Agent a, long period, ServiceDescription sd) {
		super(a, period);
		this.servicoProcurado = sd;
	}

	@Override
	protected void onTick() {
		DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(servicoProcurado);
		try {
			DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
			if (resultado.length != 0) {
				ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
				msg.addReceiver(resultado[0].getName());
				msg.setContent(servicoProcurado.getType());
				myAgent.send(msg);
				stop(); // finaliza comportamento
			}
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

}
