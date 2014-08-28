package praticasSMA.praticaProtocolos;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
//para implementar o protocolo request importamos a seguinte classe:
import jade.domain.FIPANames; 

@SuppressWarnings("serial")
public class AgenteFIPARequestAlarmado extends Agent 
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
       
      Object[] args = getArguments();
      if (args != null && args.length > 0) 
      {
         System.out.println("Solicitando ajuda a varias centrais de bombeiros...");
         //montando a mensagem a ser enviada posteriormente
         ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
         for (int i = 0; i < args.length; i++) 
         {
            msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
         }
         msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
         msg.setContent("Fogo a 5 kms");
         
         /* A classe ComportamentoIniciador extende a classe AchieveREInitiator.
         Ela atua como o iniciador do protoloco. Seu metodo construtor envia 
         automaticamente a mensagem que esta no objeto msg */
         addBehaviour(new ComportamentoIniciador(this, msg));
      }
      else 
      {
         System.out.println("Especifique o nome de pelo menos uma central de bombeiros");
      }
   }//Fim do método setup()
}//Fim da classe AgenteFIPARequestAlarmado