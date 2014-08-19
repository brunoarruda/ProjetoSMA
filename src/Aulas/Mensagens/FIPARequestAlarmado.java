package Aulas.Mensagens;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
//para implementar o protocolo request importamos a seguinte classe:
import jade.domain.FIPANames; 

public class FIPARequestAlarmado extends Agent {

    protected void setup() {

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            System.out.println("Solicitando ajuda a v�rias centrais de bombeiros...");
            //montando a mensagem a ser enviada posteriormente
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            for (int i = 0; i < args.length; i++) {
               msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
            }
            msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
            msg.setContent("Fogo a 5 kms");
            /* A classe Iniciador(abaixo) extende a classe AchieveREInitiator, ela
            atua como o iniciador do protoloco. Seu m�todo construtor envia 
            automaticamente a mensagem que est� no objeto msg */
            addBehaviour(new Iniciador(this, msg));
        } else {
            System.out.println("Especifique o nome de pelo menos uma central de bombeiros");
        }

    }

    class Iniciador extends AchieveREInitiator {

//envia a mensagem request para os receptores que foram especificados no objeto msg
        public Iniciador(Agent a, ACLMessage msg) {
            super(a, msg);  //par�metros = agente que est� enviando, mensagem a ser enviada
        }
//Os m�todos a seguir tratam a resposta do participante
        //Se o participante concordar, isto �, enviar uma mensagem AGREE
        protected void handleAgree(ACLMessage agree) {
            System.out.println("Central de bombeiros " + agree.getSender().getName() + " informa que saiu para apagar o fogo");
        }

        //Se o participante se negar, enviando uma mensagem REFUSE
        protected void handleRefuse(ACLMessage refuse) {
            System.out.println("Central de bombeiros " + refuse.getSender().getName() + " responde que o fogo est� muito longe " +
                    "e n�o pode apag�-lo");
        }

        //Se o participante n�o entendeu, enviando uma mensagem NOT-UNDERSTOOD
        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.println("Central de bombeiros " + notUnderstood.getSender().getName() + "por algum motivo n�o entendeu a solicita��o");

        }

        //Se houve uma falha na execu��o do pedido
        protected void handleFailure(ACLMessage failure) {
            //Verifica inicialmente se foi um erro nas p�ginas brancas
            if (failure.getSender().equals(getAMS())) {
                System.out.println("Alguma das centrais de bombeiro n�o existe");
            } 
 /* O conte�do de uma mensagem envolvida neste protocolo � automaticamente colocado entre par�nteses. Com o m�todo substring() podemos ler apenas o que est� dentro deles.*/
             
             else {
                System.out.println("Falha na central de bombeiros " + failure.getSender().getName() +
                ": " + failure.getContent().substring(1, failure.getContent().length() - 1));
            }
        }

        //Ao finalizar o protocolo, o participante envia uma mensagem inform
        protected void handleInform(ACLMessage inform) {
            System.out.println("Central de bombeiros" + inform.getSender().getName() + " informa que apagou o fogo");
        }
    }
}
