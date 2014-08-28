package praticasSMA.praticaComportamentos.FSMComportamento;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteFSM extends Agent 
{
   private String entrada="";
   
   public void setup() 
   {
      entrada="231";
      
      System.out.println("Estou no começo do agente");
      System.out.println("entrada = " + entrada);
      System.out.println("-----------------------------");
      
      ComportamentoFSM b = new ComportamentoFSM(this, entrada);
      addBehaviour(b);
   }
}
