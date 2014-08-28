package praticasSMA.criarVariosContainers.containersMesmaMaquina.comComunicacao;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

@SuppressWarnings("serial")
public class ComportamentoIniciador extends AchieveREInitiator 
{
   //envia a mensagem request para os receptores que foram especificados no objeto msg
   public ComportamentoIniciador(Agent a, ACLMessage msg) 
   {
      super(a, msg);  //parametros = agente que esta enviando, mensagem a ser enviada
   }

   //Os metodos a seguir tratam a resposta do participante
   //Se o participante concordar, isto eh, enviar uma mensagem AGREE
   protected void handleAgree(ACLMessage agree) 
   {
      System.out.println("Central de bombeiros " + agree.getSender().getName() + " informa que saiu para apagar o fogo");
   }

   //Se o participante se negar, enviando uma mensagem REFUSE
   protected void handleRefuse(ACLMessage refuse) 
   {
      System.out.println("Central de bombeiros " + refuse.getSender().getName() + " responde que o fogo esta muito longe " +
                    "e nao pode apaga-lo");
   }

   //Se o participante nao entendeu, enviando uma mensagem NOT-UNDERSTOOD
   protected void handleNotUnderstood(ACLMessage notUnderstood) 
   {
      System.out.println("Central de bombeiros " + notUnderstood.getSender().getName() + " por algum motivo nao entendeu a solicitacao");
   }

   //Se houve uma falha na execucao do pedido
   protected void handleFailure(ACLMessage failure) 
   {
      //Verifica inicialmente se foi um erro nas paginas brancas
      if (failure.getSender().equals(myAgent.getAMS())) 
      {
         System.out.println("Alguma das centrais de bombeiro nao existe");
      }
      /* O conteudo de uma mensagem envolvida neste protocolo eh 
       * automaticamente colocado entre parenteses. Com o metodo substring() 
       * podemos ler apenas o que esta dentro deles.*/
      else 
      {
         System.out.println("Falha na central de bombeiros " + failure.getSender().getName() +
                ": " + failure.getContent().substring(1, failure.getContent().length() - 1));
      }
   }//Fim do metodo handleFailure()

   //Ao finalizar o protocolo, o participante envia uma mensagem inform
   protected void handleInform(ACLMessage inform) 
   {
      System.out.println("Central de bombeiros" + inform.getSender().getName() + " informa que apagou o fogo");
   }
}