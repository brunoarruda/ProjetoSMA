package praticasSMA.praticaComunicacao.alarmadoBombeiro;
        
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class ComportamentoAgenteAlarmado extends OneShotBehaviour
{ 
   public ComportamentoAgenteAlarmado(Agent a) 
   {
      super(a);
   }
   
   public void action () 
   {
      ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      msg.addReceiver(new AID("Bombeiro",AID.ISLOCALNAME));
      msg.setLanguage("Portugues");
      msg.setOntology("Emergencia");
      msg.setContent("Fogo");
      myAgent.send(msg);
   }
}
