package projeto.agente;

import projeto.comportamento.gerente.*;
import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteGerente extends Agent {

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();

		addBehaviour(new RecebeSolicitacao(this));
	}
	
}
