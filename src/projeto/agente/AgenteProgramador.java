package projeto.agente;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteProgramador extends Agent
{ 
    protected void setup()
    {
        System.out.println("Ol� Sistema. ");
        System.out.println("Sou o melhor programador e meu nome � " + getLocalName());
        System.out.println("Resolvo TUDO!\n");
    }
}