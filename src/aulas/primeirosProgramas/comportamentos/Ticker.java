package aulas.primeirosProgramas.comportamentos;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
@SuppressWarnings("serial")
public class Ticker extends Agent {
     protected void setup() {
    System.out.println("Adicionando TickerBehaviour");
 
    addBehaviour(new TickerBehaviour(this,1000) {
		protected void onTick() {                      
                  if(getTickCount()>5)
                  {
                      stop();
                  }else
                    //getTickCount() retorna o número de execuções
                    //do comportamento.
                    System.out.println("Estou realizando meu " + getTickCount() + " tick");
                 }
    });
   }
}
