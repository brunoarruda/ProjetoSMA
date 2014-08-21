package aulas.praticasSMA.praticaComunicacao.respondendoMensagens;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
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
      System.out.println(myAgent.getLocalName() +": Preparando para enviar una mensagem ao receptor");

      // Cria��o do objeto ACLMessage
      ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);   

      //Preencher os campos neces�rios da mensagem
      mensagem.setSender(myAgent.getAID());
      mensagem.addReceiver(new AID("Receptor",AID.ISLOCALNAME));
      mensagem.setLanguage("Portugues");
      mensagem.setContent("Ol�, como voc� vai Receptor?");

      //Envia a mensagem aos destinatarios
      myAgent.send(mensagem);
      System.out.println(myAgent.getLocalName() +": Enviando ol� ao receptor");
      System.out.println(myAgent.getLocalName() + "\n" + mensagem.toString());

      //Esperando a resposta
      ACLMessage mensagem2 = myAgent.blockingReceive();
      if (mensagem2!= null)
      {
         System.out.println(myAgent.getLocalName() + ": acaba de receber a seguinte mensagem: ");
         System.out.println(mensagem2.toString());
         fim = true;
      }
   }
   public boolean done()
   {  
       return fim;  
   }
}