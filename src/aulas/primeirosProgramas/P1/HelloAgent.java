package aulas.primeirosProgramas.P1;

import jade.core.Agent;

@SuppressWarnings("serial")
public class HelloAgent extends Agent
{ 
    protected void setup()
    {
        System.out.println("Hello World. ");
        System.out.println("Meu nome é " + getLocalName());
    }
}