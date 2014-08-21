package aulas.praticasSMA.praticaComunicacao.alarmadoBombeiro;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteBombeiro extends Agent
{ 
    
    protected void setup() 
    {
        addBehaviour(new ComportamentoAgenteBombeiro(this));
    }
}