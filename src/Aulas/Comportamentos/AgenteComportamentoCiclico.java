package Aulas.Comportamentos;

import jade.core.Agent;

public class AgenteComportamentoCiclico extends Agent{
    protected void setup() {
        //addBehaviour(new ComportamentoCiclico(this, 300));
        addBehaviour(new ComportamentoCiclico());
    }
}
