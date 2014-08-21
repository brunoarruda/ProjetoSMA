package aulas.praticasSMA.praticaComportamentos.testeChamarDoDelete;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

@SuppressWarnings("serial")
public class AgenteChamarDoDelete extends Agent
{
  long tempoInicial = System.currentTimeMillis();   
   
   public void setup() 
   {
      SequentialBehaviour s = new SequentialBehaviour(this);
      s.addSubBehaviour(new ComportamentoChamarDoDelete(this, "A", 3));
      s.addSubBehaviour(new ComportamentoChamarDoDelete(this, "B", 2));
      addBehaviour(s);

      System.out.println("Tempo antes do doDelete(): " + (System.currentTimeMillis()  - tempoInicial));
      doDelete();
      System.out.println("Tempo depois do doDelete(): " + (System.currentTimeMillis()  - tempoInicial));
   }
 
   protected void takeDown()
   {
      System.out.println("Tempo no takeDown(): " + (System.currentTimeMillis()  - tempoInicial));
      System.out.println("Execução finalizada");
   }
}
