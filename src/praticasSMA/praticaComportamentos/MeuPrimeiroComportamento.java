package praticasSMA.praticaComportamentos;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class MeuPrimeiroComportamento extends Behaviour
{
    int i=0;
      
    public MeuPrimeiroComportamento(Agent a)
    {
       super(a);
    }   
    
    public void action() {
       System.out.println(" Meu nome é: " + myAgent.getLocalName());
       System.out.println(" Sou o primeiro comportamento de " + myAgent.getLocalName());
    }

    public boolean done() 
    {
       return true;
    }
}