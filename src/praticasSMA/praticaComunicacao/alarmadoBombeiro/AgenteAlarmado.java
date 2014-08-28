package praticasSMA.praticaComunicacao.alarmadoBombeiro;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteAlarmado extends Agent{
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
     addBehaviour(new ComportamentoAgenteAlarmado(this));
  }
}
