package projeto.comportamento.cliente;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class RecebeMensagens extends CyclicBehaviour
{   
   public RecebeMensagens(Agent a) 
   {
      super(a);
   }
   
   public void action() 
   {
      ACLMessage msg = myAgent.receive();
      if (msg != null) 
      {
         System.out.println(msg.getSender() + " : " + msg.getContent());
      }
      else
         block();
   }//Fim do metodo action()
}