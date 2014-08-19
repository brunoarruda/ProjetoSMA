package Aulas.Mensagens;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
public class AgenteAlarmado2 extends Agent{
  protected void setup() {  
       addBehaviour(new OneShotBehaviour(this) {

            public void action() {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("Bombeiro2",AID.ISLOCALNAME));
            msg.setLanguage("Portugues");
            msg.setOntology("Emergencia");
            msg.setContent("Fogo");
            myAgent.send(msg);
            
            }
        });
        addBehaviour(new CyclicBehaviour(this) 
        {            
            public void action() {
            ACLMessage msg = myAgent.receive();
            if(msg != null){
                String content = msg.getContent();
                System.out.println("--> " + msg.getSender().getName() + ": " + content);
            }else 
            //Com o block() bloqueamos o comportamento até que uma nova 
            //mensagem chegue ao agente e assim evitamos consumir ciclos
            //da CPU.
            block();
                       
            }
        });    
   
  
      
  
 
}
 } 
