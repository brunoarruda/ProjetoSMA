package Aulas.Mensagens;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Prestadores extends Agent {

    protected void setup() {
        //Descri��o do Servi�o
        ServiceDescription servico = new ServiceDescription();
        //Captura argumentos para verificar o tipo de prestador
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            //Se o argumento � bombeiro
            if (argumento.equalsIgnoreCase("bombeiro")) {
                //Seu servi�o � apagar fogo
                servico.setType("apaga fogo");
                //O nome do servi�o ser� o nome do agnete
                servico.setName(this.getLocalName());
                //Registra o servi�o                
                registraServico(servico);
                //Aciona m�todo para receber mensagens
                RecebeMensagens("fogo", "Vou apagar o fogo");
            //Se o argumento � policial
            } else if (argumento.equalsIgnoreCase("policial")) {
                //Seu servi�o � prender o ladr�o
                servico.setType("prende ladr�o");
                servico.setName(this.getLocalName());
                registraServico(servico);
                RecebeMensagens("ladr�o", "Vou prender o ladr�o");
            //Se o argumento � m�dico
            } else if (argumento.equalsIgnoreCase("medico")) {
                //Seu servi�o � salvar vidas
                servico.setType("salva vidas");
                servico.setName(this.getLocalName());
                registraServico(servico);
                RecebeMensagens("doente", "Vou salvar o doente");
            } else {
                //Se n�o passei argumento ou o argumento n�o � v�lido
                System.out.println("Voc� n�o forneceu minha fun��o");
                doDelete();
            }
        }
    }

    //m�todo para registrar servi�o
    protected void registraServico(ServiceDescription sd) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

    }
    //M�todo para adicionar um comportamento para receber mensagens
    protected void RecebeMensagens(final String mensagem, final String resp) {
        addBehaviour(new CyclicBehaviour(this) {

            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getContent().equalsIgnoreCase(mensagem)) {
                        ACLMessage reply = msg.createReply();
                        reply.setContent(resp);
                        myAgent.send(reply);
                    }
                }
                block();
            }
        });
    }
}