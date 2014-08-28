package praticasSMA.praticaComunicacao.alarmadoBombeiro;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteBombeiro2 extends Agent
{     
   protected void setup() 
   {
      addBehaviour(new GerenciarMensagem(this));
   }

   //CRIANDO AS CLASSES PRIVADAS
   private class GerenciarMensagem extends CyclicBehaviour 
   {   
      public GerenciarMensagem(Agent a) 
      {
         super(a);
      }
      
      public void action() 
      {
          ACLMessage msg = myAgent.receive();
           
          if(msg != null) 
          {
             ACLMessage reply = msg.createReply();
             String content = msg.getContent();
             if(content.equalsIgnoreCase("Fogo")) 
             {
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Recebi seu aviso! Obrigado por auxiliar meu servico");
                myAgent.send(reply);
                System.out.println("O agente "+ msg.getSender().getName() +" avisou de um incendio");
                System.out.println("Vou ativar os procedimentos de combate ao incendio!"); 
             }  
          } 
          else
             block();
      } //Fim do método action()
   }//FIm da classe EnviarMensagem
}