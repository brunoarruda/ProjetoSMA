package primeirosProgramas.P4;

import jade.core.Agent;
@SuppressWarnings("serial")
public class AgenteImpressor extends Agent {
    
    protected void setup() {
        
        System.out.println("Ol�! Eu sou um agente impressor!");
        System.out.println("# Vou executar meu comportamento");
        addBehaviour(new ImprimeFrase(this, 5000)); 
    }   
}
