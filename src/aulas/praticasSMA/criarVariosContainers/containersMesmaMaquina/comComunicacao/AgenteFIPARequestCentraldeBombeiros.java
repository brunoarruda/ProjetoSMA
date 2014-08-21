package aulas.praticasSMA.criarVariosContainers.containersMesmaMaquina.comComunicacao;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.domain.FIPANames;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class AgenteFIPARequestCentraldeBombeiros extends Agent 
{
   public double DISTANCIA_MAX;

   protected void setup() 
   {
      try
      {
         Thread.sleep(9000);
      }
      catch(Exception e)
      {
         System.out.println("Erro: " + e);
      }

      DISTANCIA_MAX = (Math.random() * 10);
      System.out.println("Central " + getLocalName() + ": Aguardando alarmes...");
   
      //Meu agente conversa sob o protocolo FIPA REQUEST
      MessageTemplate protocolo = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
      MessageTemplate performativa = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
      MessageTemplate padrao = MessageTemplate.and(protocolo, performativa);

      addBehaviour(new ComportamentoParticipante(this, padrao, DISTANCIA_MAX));
   }
}