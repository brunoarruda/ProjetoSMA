package primeirosProgramas.P1_2;

import jade.core.Agent;

import java.util.Iterator;
@SuppressWarnings("serial")
public class InfoAgentes extends Agent{
    
    protected void setup()
    {
        System.out.println("Hello World. Eu sou um agente!");
        System.out.println("Todas as minhas informa��es: \n" + getAID());
        System.out.println("Meu nome local � "+ getAID().getLocalName());
        System.out.println("Meu nome global (GUID) � "+ getAID().getName());
        System.out.println("Meus endere�os s�o: ");
        Iterator<?> it = getAID().getAllAddresses();
        while(it.hasNext()) 
        {
            System.out.println("- "+it.next());
        }
    }
}