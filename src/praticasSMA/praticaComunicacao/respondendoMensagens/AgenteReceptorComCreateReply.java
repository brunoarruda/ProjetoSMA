package praticasSMA.praticaComunicacao.respondendoMensagens;

import jade.core.*;

@SuppressWarnings("serial")
public class AgenteReceptorComCreateReply  extends Agent
{
   protected void setup()
   {
      //addBehaviour(new ComportamentoReceptor(this));
      addBehaviour(new ComportamentoReceptorComCreateReply(this));
   }
}