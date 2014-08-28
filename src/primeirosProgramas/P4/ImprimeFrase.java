package primeirosProgramas.P4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class ImprimeFrase  extends Behaviour{
    int numExecucao=1;
    long delay;
    long tempoInicial = System.currentTimeMillis();
    
public ImprimeFrase(Agent a, long delay) {
     super(a);
     this.delay = delay;   
} 

    public void action() {
        
        //System.out.println("Estado do agente no action() ANTES do block(): " + this.isRunnable());
        System.out.println("\nTempo " + (System.currentTimeMillis()  - tempoInicial)  + ": Meu nome é " + myAgent.getLocalName());
        block(delay);
        //System.out.println("Estado do agente no action() DEPOIS do block(): " + this.isRunnable());
        numExecucao = numExecucao+1;
    }

    public boolean done() {
        //System.out.println("Estado do agente no action() antes do done(): " + this.isRunnable());
        return numExecucao>5;
    }   
    
    public int onEnd() {
        
       System.out.println(myAgent.getLocalName() + ": Meu comportamento foi finalizado! Até mais...");
       return 0;
        }
}