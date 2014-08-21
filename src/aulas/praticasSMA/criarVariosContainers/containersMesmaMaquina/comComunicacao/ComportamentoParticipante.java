package aulas.praticasSMA.criarVariosContainers.containersMesmaMaquina.comComunicacao;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class ComportamentoParticipante extends AchieveREResponder
{
   double DISTANCIA_MAX;
   
   public ComportamentoParticipante(Agent a, MessageTemplate mt, double DISTANCIA_MAX) 
   {
      //Define agente e protocolo de comunicacao
      super(a, mt);
      this.DISTANCIA_MAX = DISTANCIA_MAX;
   }

   /* Metodo que aguarda uma mensagem REQUEST, definida com o uso do objeto mt, 
    * utilizado no construtor desta classe. O retorno deste metodo eh uma 
    * mensagem que eh enviada automaticamente para o iniciador. */
   protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException 
   {
      System.out.println("Central " + myAgent.getLocalName() + ": Recebemos uma chamada de " + request.getSender().getName() +
         " dizendo que observou um incendio");

      /*A classe StringTokenizer permite que voce separe
       * ou encontre palavras (tokens) em qualquer formato.*/
      StringTokenizer st = new StringTokenizer(request.getContent());
      String conteudo = st.nextToken(); //pego primeiro token
      if (conteudo.equalsIgnoreCase("fogo")) 
      { //se for fogo
         st.nextToken(); //pulo o segundo 
         int distancia = Integer.parseInt(st.nextToken()); //capturo DIST
         if (distancia < DISTANCIA_MAX) 
         {
            System.out.println("Central " + myAgent.getLocalName() + ": Saimos correndo!");
            ACLMessage agree = request.createReply();
            agree.setPerformative(ACLMessage.AGREE);
            return agree;  //envia mensagem AGREEE
         } 
         else 
         {
            //Fogo esta longe. Envia Mensagem Refuse com o motivo
            System.out.println("Central " + myAgent.getLocalName() + ": Fogo esta longe demais. "
                    + "Nao podemos atender a solicitacaoo.");
            throw new RefuseException("Fogo esta muito longe");
         }
      } //envia mensagem NOT_UNDERSTOOD
      else 
      {
         throw new NotUnderstoodException("Central de Bombeiros nao entendeu sua mensagem");
      }
   }

   //Prepara resultado final, caso tenha aceitado     
   protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException 
   {
      if (Math.random() > 0.2) 
      {
         System.out.println("Central " + myAgent.getLocalName() + ": Voltamos da tarefa de apagar o fogo. Sucesso na missão!");
         ACLMessage inform = request.createReply();
         inform.setPerformative(ACLMessage.INFORM);
         return inform; //envia mensagem INFORM
      } 
      else 
      {
         System.out.println("Central " + myAgent.getLocalName() + ": Ficamos sem agua. Nao conseguimos apagar o incendio.");
         throw new FailureException("Ficamos sem agua");
      }
   } 
}