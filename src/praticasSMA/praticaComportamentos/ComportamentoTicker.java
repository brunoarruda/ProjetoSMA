package praticasSMA.praticaComportamentos;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

@SuppressWarnings("serial")
public class ComportamentoTicker extends TickerBehaviour
{      
    long tempoInicial = System.currentTimeMillis();
    
    public ComportamentoTicker(Agent a, long  tempo)
    {
       super(a, tempo);
       System.out.println("Tempo inicial: " + (System.currentTimeMillis() - tempoInicial));
    }   
    
    protected void onTick() 
    {
       if( getTickCount() > 5)
       {
          stop();
       }
       else
          //getTickCount() retorna o numero de execucoes do comportamento.
          System.out.println("Estou realizando meu " + getTickCount() + " tick");
          System.out.println("Tempo atual: " + (System.currentTimeMillis() - tempoInicial));
    }
}
