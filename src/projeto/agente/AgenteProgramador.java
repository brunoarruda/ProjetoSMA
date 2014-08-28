package projeto.agente;

import java.util.List;

import projeto.modelo.Competencia;
import projeto.modelo.*;
import jade.core.Agent;


@SuppressWarnings("serial")
public class AgenteProgramador extends Agent
{
	private List<Competencia> competencias;
	private Tarefa tarefa;
	
    protected void setup()
    {
        System.out.println("Ol� Sistema. ");
        System.out.println("Sou o melhor programador e meu nome � " + getLocalName());
        System.out.println("Resolvo TUDO!\n");
    }
}