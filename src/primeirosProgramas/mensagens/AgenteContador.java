package primeirosProgramas.mensagens;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteContador extends Agent {

    protected void setup() {
        System.out.println("Agente Contador inicializado.\n " +
                "Aguardando informa��es...");

        addBehaviour(new CyclicBehaviour(this) {  //in�cio do comportamento

            Musicos[] musicos = new Musicos[5]; //vetor da classe Musicos
            int cont = 0;

            public void action() {

                ACLMessage msg = receive(); //captura nova mensagem

                if (msg != null) { //se existe mensagem
                    try { //extrai o objeto 
                        musicos[cont] = (Musicos) msg.getContentObject();
                        //imprime as informa��es do objeto
                        musicos[cont].Imprimir(); 
                        cont = cont + 1;
                    } catch (Exception e) {
                    }
                } else 
                block(); //aguarda nova mensagem
            }
        });//t�rmino do comportamento
    } //fim do m�todo setup() do agente
}

