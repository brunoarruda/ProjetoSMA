package praticasSMA.praticaComportamentos;

import jade.core.Agent;
@SuppressWarnings("serial")
public class AgenteTicker extends Agent 
{
   protected void setup() 
   {
      System.out.println("Adicionando TickerBehaviour");
      addBehaviour(new ComportamentoTicker(this,1000));
   }
}
