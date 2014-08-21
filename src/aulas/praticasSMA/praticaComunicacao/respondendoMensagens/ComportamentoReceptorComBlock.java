package aulas.praticasSMA.praticaComunicacao.respondendoMensagens;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.*;

@SuppressWarnings("serial")
public class ComportamentoReceptorComBlock extends SimpleBehaviour
{
   private boolean fim = false;
   public ComportamentoReceptorComBlock (Agent a)
   {
      super(a);
   }   
   public void action()
   {
      System.out.println(myAgent.getLocalName() + ": Preparando para receber mensagens");
      //Obtem a primeira mensagem da fila de mensagens
      ACLMessage mensagem = myAgent.receive();
      if (mensagem!= null)
      {
         System.out.println(myAgent.getLocalName() + ": Desbloqueou pois acaba de receber a seguinte mensagem: ");
         System.out.println(myAgent.getLocalName() + ": acaba de receber a seguinte mensagem: ");
         System.out.println(mensagem.toString());       
         // Envia resposta
         System.out.println(myAgent.getLocalName() +": Enviando resposta");
         ACLMessage resposta = new ACLMessage( ACLMessage.INFORM );
         resposta.setContent( "Vou bem, obrigado!" );
         resposta.addReceiver( mensagem.getSender() );
         myAgent.send(resposta);
         System.out.println(myAgent.getLocalName() +": Enviei uma resposta ao Receptor");
         System.out.println(resposta.toString());
         fim = true;
      }
      else
      {
         System.out.println("Receptor: Bloqueado para esperar receber mensagem.....");
         block();
      }
   } // Fim do método action()
   public boolean done()
   {
      return fim;
   }
}
