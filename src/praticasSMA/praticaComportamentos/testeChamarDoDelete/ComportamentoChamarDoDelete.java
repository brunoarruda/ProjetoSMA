package praticasSMA.praticaComportamentos.testeChamarDoDelete;

import jade.core.behaviours.SimpleBehaviour;
import jade.core.Agent;
        
@SuppressWarnings("serial")
public class ComportamentoChamarDoDelete extends SimpleBehaviour 
{
   int c;
   int lim;
   String nome;
 
   public ComportamentoChamarDoDelete(Agent a, String nome, int lim) 
   {
      super(a);
      this.nome = nome;
      this.c = 0;
      this.lim = lim;
   }
 
   public void action () 
   {
      c++;
      System.out.println("contador " + nome + ": " + c);
   }
 
   public boolean done () 
   {
      return c == lim;
   }
   
   /*
    * O m�todo onEnd() � chamado depois que o m�todo done() retornar 
    * o valor true. E ap�s o comportamento ter sido movido para a 
    * lista de comportamentos bloqueados
    */
   public int onEnd()
   {
      if (nome.compareTo("B") == 0)
         myAgent.doDelete();
      return 1;
   }   
}