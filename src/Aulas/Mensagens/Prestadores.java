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
        //Descrição do Serviço
        ServiceDescription servico = new ServiceDescription();
        //Captura argumentos para verificar o tipo de prestador
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            //Se o argumento é bombeiro
            if (argumento.equalsIgnoreCase("bombeiro")) {
                //Seu serviço é apagar fogo
                servico.setType("apaga fogo");
                //O nome do serviço será o nome do agnete
                servico.setName(this.getLocalName());
                //Registra o serviço                
                registraServico(servico);
                //Aciona método para receber mensagens
                RecebeMensagens("fogo", "Vou apagar o fogo");
            //Se o argumento é policial
            } else if (argumento.equalsIgnoreCase("policial")) {
                //Seu serviço é prender o ladrão
                servico.setType("prende ladrão");
                servico.setName(this.getLocalName());
                registraServico(servico);
                RecebeMensagens("ladrão", "Vou prender o ladrão");
            //Se o argumento é médico
            } else if (argumento.equalsIgnoreCase("medico")) {
                //Seu serviço é salvar vidas
                servico.setType("salva vidas");
                servico.setName(this.getLocalName());
                registraServico(servico);
                RecebeMensagens("doente", "Vou salvar o doente");
            } else {
                //Se não passei argumento ou o argumento não é válido
                System.out.println("Você não forneceu minha função");
                doDelete();
            }
        }
    }

    //método para registrar serviço
    protected void registraServico(ServiceDescription sd) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

    }
    //Método para adicionar um comportamento para receber mensagens
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