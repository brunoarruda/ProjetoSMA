package aulas.praticasSMA.praticaComunicacao.primeiroEmissorReceptor;

import jade.core.*;
 
@SuppressWarnings("serial")
public class AgenteEmissor extends Agent
{    
    protected void setup()
    {
       addBehaviour(new ComportamentoEmissor(this));
    }
}
