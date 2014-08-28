package praticasSMA.praticaComunicacao.comSniffer;

import jade.core.*;
 
@SuppressWarnings("serial")
public class AgenteEmissorComSleep extends Agent
{    
    protected void setup()
    {
       try
       {
          Thread.sleep(3000);
       }
       catch(Exception e)
       {
          System.out.println("Erro: " + e);
       }
       
       addBehaviour(new ComportamentoEmissor(this));
    }
}
