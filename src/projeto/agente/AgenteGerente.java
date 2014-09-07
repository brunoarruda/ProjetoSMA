package projeto.agente;

import projeto.comportamento.gerente.*;
import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteGerente extends Agent {

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();

		Object[] args = getArguments();
		String[] nomesProgramadores = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			nomesProgramadores[i] = (String) args[i];
		}
		addBehaviour(new RecebeSolicitacao(this, nomesProgramadores));
	}

}
