package aulas.praticasSMA.praticaComunicacao.alarmadoBombeiro;    

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class ComportamentoAgenteBombeiro extends CyclicBehaviour
{ 
   public ComportamentoAgenteBombeiro(Agent a) 
   {
      super(a);
   }
   public void action () 
   {
       ACLMessage msg = myAgent.receive();
       if(msg != null) 
       {
          String content = msg.getContent();
          //com equalsIgnoreCase fazemos uma comparacao
          //nao case-sensitive.
          if(content.equalsIgnoreCase("Fogo")) 
          {
             System.out.println("O agente " + msg.getSender().getName() +
                               " avisou de um incendio");
             System.out.println("Vou ativar os procedimentos de combate ao incendio!"); 
          }  
       } 
       else
          block();
   } //fim do metodo action()
}
