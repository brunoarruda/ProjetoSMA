package Aulas.Mensagens;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgenteBombeiroFiltro extends Agent {

    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) { //Início do Comportamento

            public void action() {

                //definimos o primeiro filtro
                MessageTemplate MT1 = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                //definimos o segundo filtro
                MessageTemplate MT2 = MessageTemplate.MatchLanguage("Portugues");
                //Realizamos um "E" lógico entre os dois filtros
                MessageTemplate MT3 = MessageTemplate.and(MT1, MT2);
                //Recebe a mensagem de acordo com o filtro
                ACLMessage msg = myAgent.receive(MT3);

                if (msg != null) {
                    String content = msg.getContent();
                    if (content.equalsIgnoreCase("Fogo")) {
                        System.out.println("O agente " + msg.getSender().getName() + "avisou de um incendio");
                    }

                }
            }
        }); //Fim do Comportamento
    }
}
