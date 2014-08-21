package aulas.praticasSMA.praticaComportamentos;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

@SuppressWarnings("serial")
public class AgenteSequencialContador extends Agent
{
 
   public void setup() 
   {
      SequentialBehaviour s = new SequentialBehaviour(this);
      s.addSubBehaviour(new ComportamentoContador(this, "A", 3));
      s.addSubBehaviour(new ComportamentoContador(this, "B", 2));
      s.addSubBehaviour(new ComportamentoContador(this, "C", 4));
      s.addSubBehaviour(new ComportamentoContador(this, "D", 3));
      s.addSubBehaviour(new ComportamentoContador(this, "E", 5));
      addBehaviour(s);
   }
 
   protected void takeDown()
   {
      System.out.println("Execução finalizada");
   }
}