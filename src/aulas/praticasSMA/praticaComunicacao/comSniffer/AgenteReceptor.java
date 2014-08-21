package aulas.praticasSMA.praticaComunicacao.comSniffer;

import jade.core.*;

@SuppressWarnings("serial")
public class AgenteReceptor extends Agent
{
   protected void setup()
   {
      //addBehaviour(new ComportamentoReceptor(this));
      addBehaviour(new ComportamentoReceptorComBlock(this));
   }
}
