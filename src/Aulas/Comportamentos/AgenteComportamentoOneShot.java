package Comportamentos;
import jade.core.Agent;
public class AgenteComportamentoOneShot extends Agent{
   protected void setup() {
      addBehaviour(new ComportamentoOneShot(this));
   }    
   protected void takeDown()
   {
      System.out.println("Execução finalizada!");
   }
}