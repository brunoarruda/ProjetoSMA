package aulas.praticasSMA.praticaComunicacao.comSniffer;

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
      System.out.println(myAgent.getLocalName() +": Preparando para enviar una mensagem ao receptor");
      // Criação do objeto ACLMessage
      ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
      //Preencher os campos necesários da mensagem
      mensagem.setSender(myAgent.getAID());
      mensagem.addReceiver(new AID("Receptor",AID.ISLOCALNAME));
      mensagem.setLanguage("Portugues");
      mensagem.setContent("Olá, como você vai Receptor?");
      //Envia a mensagem aos destinatarios
      myAgent.send(mensagem);
      System.out.println(myAgent.getLocalName() +": Enviando olá ao receptor");
      System.out.println(myAgent.getLocalName() + "\n" + mensagem.toString());
      fim = true;
   }
   public boolean done()
   {
      return fim;
   }
}