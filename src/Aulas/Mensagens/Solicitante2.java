package Aulas.Mensagens;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SubscriptionInitiator;

public class Solicitante2 extends Agent {

    protected void setup() {
        //Nada mudou neste método, apenas o método Busca()
        //passou a se chamado de PedeNotificação()

        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            if (argumento.equalsIgnoreCase("fogo")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("apaga fogo");
                PedeNotificacao(servico, "fogo");
            }
            if (argumento.equalsIgnoreCase("ladrão")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("prende ladrão");
                PedeNotificacao(servico, "ladrão");
            }
            if (argumento.equalsIgnoreCase("doente")) {
                ServiceDescription servico = new ServiceDescription();
                servico.setType("salva vidas");
                PedeNotificacao(servico, "doente");
            }

            //Comportamento para receber mensagens
            addBehaviour(new CyclicBehaviour(this) {
                /*As mensagens de notificação do DF atendem ao protocolo
                 * FIPA Subscribe, e elas possuem um método exclusivo para sua recepção. (SubscriptionInitiator)
                 * Então, devemos ler todas as mensagens, exceto as que obdecem o protocolo
                 * FIPA Subcribe.*/
                //Faço um filtro para receber mensagens do protocolo Subscribe
                MessageTemplate filtro = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
                //Crio um novo filtro que realiza uma inversão lógica no filtro anterior.
                //ou seja, não aceita mensagens do protocolo Subscribe
                MessageTemplate filtro2 = MessageTemplate.not(filtro);

                public void action() {
                    //Só recebe mensagens do filtro 2
                    ACLMessage msg = receive(filtro2);
                    if (msg != null) {
                        System.out.println(msg.getSender() + " : " + msg.getContent());
                    }else
                    block();
                }
            });
        }
    }
    protected void PedeNotificacao(final ServiceDescription sd, final String Pedido) {

        //Crio descrição da entrada no registro
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        //Crio mensagem de notificação
   
        ACLMessage mgs = DFService.createSubscriptionMessage(this, getDefaultDF(), dfd, null);

        //Agora iniciamos o comportamento que ficará esperando pela notificação do DF

            addBehaviour(new SubscriptionInitiator(this, mgs) {
            //A mensagem de notificação é uma mensagem INFORM, então
            //utilizo o método padrão handleInform(). Este é um método pré-definido para 
            //manipular mensagens do tipo INFORM. 
            protected void handleInform(ACLMessage inform) {
                try {
                    //Retorna array de AIDs dos Agentes
                    DFAgentDescription[] dfds = DFService.decodeNotification(inform.getContent());
                    //Crio mensagem
                    ACLMessage mensagem =new ACLMessage(ACLMessage.INFORM);
                    //Capturo AID do agente
                    mensagem.addReceiver(dfds[0].getName());
                    //Defino conteúdo da mensagem
                    mensagem.setContent(Pedido);
                    //Envio a mensagem
                    myAgent.send(mensagem);

                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
