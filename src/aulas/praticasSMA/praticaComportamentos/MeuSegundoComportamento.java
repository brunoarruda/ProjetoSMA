package aulas.praticasSMA.praticaComportamentos;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class MeuSegundoComportamento extends Behaviour
{
    int i=0;
      
    public MeuSegundoComportamento(Agent a)
    {
       super(a);
    }   
    
    public void action() {
       System.out.println(" Meu nome é: " + myAgent.getLocalName());
       System.out.println(" Sou o segundo comportamento de " + myAgent.getLocalName());
    }

    public boolean done() 
    {
       return true;
    }
}
