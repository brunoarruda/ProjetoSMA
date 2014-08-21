package aulas.primeirosProgramas.mensagens;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

import java.util.Iterator;

@SuppressWarnings("serial")
public class Busca extends Agent {

    protected void setup() {

     //crio uma entrada no DF
     DFAgentDescription template = new DFAgentDescription();

     //crio um objeto contendo a descri��o do servi�o
     ServiceDescription sd = new ServiceDescription();
     sd.setType("Tipo"); //defino o tipo de servi�o
     /*Neste momento poderia definir outras caracter�sticas
     * do servi�o buscado para filtrar melhor a busca.
     * No caso, vamos buscar por servi�os do tipo "Tipo" */


     //adiciono o servi�o na entrada
     template.addServices(sd);
     try {

       //Vou buscar pelos agentes
       //A busca retorna um array DFAgentDescription
       //O par�metro this indica o agente que est� realizando a busca
       DFAgentDescription[] result = DFService.search(this, template);

       //Imprimo os resultados
       for (int i = 0; i < result.length; i++) {
       //result[i].getName() fornece a AID do agente
       String out = result[i].getName().getLocalName() + " prov� ";

       //Para obter os servi�os do agente invocamos
       //o m�todo getAllServices();
       Iterator<?> iter = result[i].getAllServices();

       while (iter.hasNext()) {
            //Extra�mos os servicos para um objeto ServiceDescription
            ServiceDescription SD = (ServiceDescription) iter.next();
            //Capturamos o nome do servi�o
            out += " " + SD.getName();
                }
       //Os servi�os de cada agente s�o impressos na tela
       System.out.println(out);
            } //fim do la�o for


        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
