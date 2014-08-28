package primeirosProgramas.mensagens;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

@SuppressWarnings("serial")
public class Registro extends Agent {
    protected void setup() {
        //Criamos uma entrada no DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID()); //Informamos a AID do agente

        //Vamos criar um serviço
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo"); //Tipo do Servico
        sd.setName("Servico1"); //Nome do Serviço
        //adicionamos o Serviço1
        dfd.addServices(sd);

        //Vamos criar outro serviço
        sd = new ServiceDescription();
        sd.setType("Tipo de Servico");
        sd.setName("Servico2");
        dfd.addServices(sd);

        //Vamos registrar o agente no DF
        try {
            //register(agente que oferece, descrição)
            DFService.register(this, dfd);

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}



