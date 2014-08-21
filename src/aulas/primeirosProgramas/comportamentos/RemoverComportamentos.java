package aulas.primeirosProgramas.comportamentos;

import jade.core.Agent;
import jade.core.behaviours.*;
 
@SuppressWarnings("serial")
public class RemoverComportamentos extends Agent
{
    private Behaviour comp;
 
    protected void setup()
    {
        System.out.println("Sou o agente " + getLocalName());
        //Criação do primeiro comportamento
        comp = new MeuComportamento1();
        //Aqui es donde se aÃ±ade el comportamiento.
        addBehaviour(comp);
    }
 
    //Definicao do primeiro comportamento
    private class MeuComportamento1 extends Behaviour
    { 
        int i =1;
        public void action()
        {
            System.out.println("Sou o primeiro comportamento");
            // Criacao de um outro comportamento, a partir do primeiro comportamento
            if (i==1)
                myAgent.addBehaviour(new MeuComportamento2());
        }
 
        public boolean done()
        {
            System.out.println("Executei o primeiro comportamento "+ i + " vezes");
            i++;
            return false;
        }
    }
 
    //Definicao do segundo comportamento
    private class MeuComportamento2 extends Behaviour
    {
        int i = 1;
        public void action()
        {
            System.out.println("Sou o segundo comportamento");            
            myAgent.removeBehaviour(comp);//Eliminamos o primeiro comportamento            
        }
        public boolean done()
        {
            System.out.println("Executei o segundo comportamento "+ i + " vezes");
            i++;
            return true;
        }
    }
}