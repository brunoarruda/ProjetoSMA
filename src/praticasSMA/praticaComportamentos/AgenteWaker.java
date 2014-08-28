package praticasSMA.praticaComportamentos;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteWaker extends Agent {
    protected void setup() 
    {
       System.out.println("Adicionando waker behaviour");
       addBehaviour(new ComportamentoWaker(this, 1000));
    }
}
