package aulas.praticasSMA.praticaComportamentos;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;

@SuppressWarnings("serial")
public class AgenteParaleloContador extends Agent
{    
   public void setup() 
   {
      //ParallelBehaviour s = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
      //ParallelBehaviour s = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ANY);
      ParallelBehaviour s = new ParallelBehaviour(this, 3);
      s.addSubBehaviour(new ComportamentoContador(this, "A", 3));
      s.addSubBehaviour(new ComportamentoContador(this, "B", 2));
      s.addSubBehaviour(new ComportamentoContador(this, "C", 4));
      s.addSubBehaviour(new ComportamentoContador(this, "D", 3));
      s.addSubBehaviour(new ComportamentoContador(this, "E", 5));
      addBehaviour(s);
   }
   protected void takeDown()
   {
      System.out.println("Execução Finalizada");
   }
}
