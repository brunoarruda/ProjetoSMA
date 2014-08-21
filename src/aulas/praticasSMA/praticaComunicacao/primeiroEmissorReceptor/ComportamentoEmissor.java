package aulas.praticasSMA.praticaComunicacao.primeiroEmissorReceptor;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.AID;
import jade.lang.acl.*;
@SuppressWarnings("serial")
public class ComportamentoEmissor extends SimpleBehaviour
{
   boolean fim = false;
   public ComportamentoEmissor (Agent a)
   {
      super(a);
   }   
   public void action()
   {
      System.out.println(myAgent.getLocalName() +": Preparando para enviar uma mensagem ao Receptor");
      // Cria��o do objeto ACLMessage
      ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
      //Preencher os campos neces�rios da mensagem
      mensagem.setSender(myAgent.getAID());
      mensagem.addReceiver(new AID("Receptor",AID.ISLOCALNAME));
      mensagem.setLanguage("Portugues");
      mensagem.setContent("Ol�, como voc� vai Receptor?");
      //Envia a mensagem aos destinatarios
      myAgent.send(mensagem);
      System.out.println(myAgent.getLocalName() +": Enviando ol� ao Receptor");
      System.out.println(myAgent.getLocalName() + "\n" + mensagem.toString());
      fim = true;
   }
   public boolean done()
   {
      return fim;
   }
}
