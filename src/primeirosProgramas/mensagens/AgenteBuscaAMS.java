package primeirosProgramas.mensagens;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;


@SuppressWarnings("serial")
public class AgenteBuscaAMS extends Agent{

    protected void setup() {
        try {
            //Quero buscar quais agentes estão na plataforma
            AMSAgentDescription[] agentes = null;
            //Crio objeto SearchConstraints para definir
            //que desejo todos os resultados
            SearchConstraints c = new SearchConstraints();
            //O método setMaxResults indica o número de resultados
            //que desejo obter. Por definição, -1 significa todos.
            c.setMaxResults (new Long(-1));
            //busco pelos agentes
            //AMSService.search(agente que busca, vetor de retorno, características)
            agentes= AMSService.search(this, new AMSAgentDescription(), c);
            //Capturo minha AID
            AID myAID = getAID();
            for(int i=0; i<agentes.length; i++) {
                AID agenteID = agentes[i].getName();
                //Imprimo todos os agentes
                //Este agente será identificado com **** para diferenciar
                //dos demais
                System.out.println((agenteID.equals(myAID)? "***": "    " )
                        + i + ": " + agenteID.getName() );
            }
        } catch (FIPAException ex) {
            ex.printStackTrace();
        }
        //Finalizo agente
        doDelete();
        //Finalizo aplicação
        System.exit(0);

    }

}
