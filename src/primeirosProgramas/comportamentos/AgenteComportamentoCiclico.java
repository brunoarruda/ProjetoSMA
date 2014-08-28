package primeirosProgramas.comportamentos;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteComportamentoCiclico extends Agent{
    protected void setup() {
        //addBehaviour(new ComportamentoCiclico(this, 300));
        addBehaviour(new ComportamentoCiclico());
    }
}
