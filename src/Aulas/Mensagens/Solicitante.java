package Mensagens;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Solicitante extends Agent {

    protected void setup() {
        //Captura argumentos
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            //Se o argumento � fogo
            if (argumento.equalsIgnoreCase("fogo")) {
                ServiceDescription servico = new ServiceDescription();
                //O servi�o � apagar fogo
                servico.setType("apaga fogo");
                //busca por quem fornece o servi�o
                busca(servico, "fogo");
            }
            //Se o argumento � ladr�o
            if (argumento.equalsIgnoreCase("ladrao")) {
                ServiceDescription servico = new ServiceDescription();
                //O servi�o  prender o ladr�o
                servico.setType("prende ladrao");
                busca(servico, "ladrao");
            }
            //Se o argumento � doente
            if (argumento.equalsIgnoreCase("doente")) {
                ServiceDescription servico = new ServiceDescription();
                //O servi�o � salvar vidas
                servico.setType("salva vidas");
                busca(servico, "doente");
            }
            //Comportamento para receber mensagens
            addBehaviour(new CyclicBehaviour(this) {

                public void action() {
                    ACLMessage msg = receive();
                    if (msg != null) {
                        System.out.println(msg.getSender() + " : " + msg.getContent());
                    }else
                    block();
                }
            });
        }
    }
    //M�todo que realiza a busca nas p�ginas amarelas da plataforma
    protected void busca(final ServiceDescription sd, final String Pedido) {

        //A cada minuto tenta buscar por agentes que fornecem
        //o servi�o
        addBehaviour(new TickerBehaviour(this, 60000) {

            protected void onTick() {
                DFAgentDescription dfd = new DFAgentDescription();
                dfd.addServices(sd);
                try {
                    DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
                    if (resultado.length != 0) {
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(resultado[0].getName());
                        msg.setContent(Pedido);
                        myAgent.send(msg);
                        stop(); //finaliza comportamento
                    }
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
