package aulas.praticasSMA.praticaComunicacao.selecaoMensagem;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

@SuppressWarnings("serial")
public class AgenteAlarmado2 extends Agent
{
   protected void setup() 
   {       
      try
      {
         Thread.sleep(3000);
      }
      catch(Exception e)
      {
         System.out.println("Erro: " + e);
      } 
     
      addBehaviour(new EnviarMensagem(this));
      addBehaviour(new ReceberMensagem(this));     
   }//Fim do método setup()

   //CRIANDO AS CLASSES PRIVADAS
   private class EnviarMensagem extends OneShotBehaviour 
   {   
      public EnviarMensagem(Agent a) 
      {
         super(a);
      }
      
      public void action() 
      {
         ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
         msg.addReceiver(new AID("BombeiroFiltro",AID.ISLOCALNAME));
         msg.setLanguage("Portugues");
         msg.setOntology("Emergencia");
         msg.setContent("Fogo");
         myAgent.send(msg);
      }//FIm do método action()
   }//Fim da classe EnviarMensagem
       
   private class ReceberMensagem extends CyclicBehaviour 
   {   
      public ReceberMensagem(Agent a) 
      {
         super(a);
      }
      
      public void action() 
      {
         ACLMessage msg = myAgent.receive();
         if(msg != null)
         {
            String content = msg.getContent();
            System.out.println("--> " + msg.getSender().getName() + ": " + content);
         }
         else 
            //Com o block() bloqueamos o comportamento at� que uma nova 
            //mensagem chegue ao agente e assim evitamos consumir ciclos
            //da CPU.
            block();
         }
     }//Fim da classe ReceberMensagem  
  
}//Fim da classe Alarmado2