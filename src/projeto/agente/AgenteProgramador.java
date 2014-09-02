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
        System.out.println("Olá Sistema. ");
        System.out.println("Sou o melhor programador e meu nome é" + getLocalName());
        System.out.println("Resolvo TUDO!\n");
    }
}