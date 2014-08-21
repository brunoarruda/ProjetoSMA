package aulas.praticasSMA.praticaComunicacao.primeiroEmissorReceptor;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.*;

@SuppressWarnings("serial")
public class ComportamentoReceptor extends SimpleBehaviour
{
   private boolean fim = false;

   public ComportamentoReceptor (Agent a)
   {
      super(a);
   }   

   public void action()
   {
      System.out.println(myAgent.getLocalName() + ": Preparando para receber mensagens");
 
      //Obtem a primeira mensagem da fila de mensagens
      ACLMessage mensagem = myAgent.receive();
 
      if (mensagem!= null)
      {
         System.out.println(myAgent.getLocalName() + ": acaba de receber a seguinte mensagem: ");
         System.out.println(mensagem.toString());
         fim = true;
      }
   } // Fim do método action()
            
   public boolean done()
   {
      return fim;
   }
}
