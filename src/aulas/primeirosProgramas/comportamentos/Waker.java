package aulas.primeirosProgramas.comportamentos;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

@SuppressWarnings("serial")
public class Waker extends Agent {
    protected void setup() {
    System.out.println("Adicionando waker behaviour");
    
	addBehaviour(new WakerBehaviour(this, 10000) {
		protected void onWake() {
		   System.out.println("Estou no comportamento WakerBehaviour");
                   System.out.println("Irei executar apenas uma vez este comportamento.");
                   System.out.println("E após um minuto depois de ter sido disparado.");
		}
    });
   }
}
