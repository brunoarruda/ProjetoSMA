package aulas.praticasSMA.praticaComportamentos;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

@SuppressWarnings("serial")
public class ComportamentoWaker extends WakerBehaviour
{      
    long tempoInicial = System.currentTimeMillis();
    
    public ComportamentoWaker(Agent a, long  tempo)
    {
       super(a, tempo);
       System.out.println("Tempo inicial: " + (System.currentTimeMillis() - tempoInicial));
    }   
    
    protected void onWake() 
    {
       System.out.println("Tempo atual: " + (System.currentTimeMillis() - tempoInicial));
       System.out.println("Estou no comportamento WakerBehaviour");
       System.out.println("Executei apenas uma vez este comportamento.");
       System.out.println("E após um minuto depois de ter sido disparado.");
    }    
}
