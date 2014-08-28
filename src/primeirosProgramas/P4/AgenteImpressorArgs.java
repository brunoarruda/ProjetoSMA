package primeirosProgramas.P4;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteImpressorArgs extends Agent {
    
    protected void setup() {

	Object[] args = getArguments();
        if(args != null && args.length>0)
        {
        long valor  = Long.parseLong((String) args[0]);
        System.out.println("Ol�! Eu sou o agente impressor!" + getLocalName());
        System.out.println("# Vou executar meu comportamento");
         addBehaviour(new ImprimeFrase(this,valor));       
              }else
	System.out.println("Voc� n�o passou argumentos");
        
        
                
    }   
}