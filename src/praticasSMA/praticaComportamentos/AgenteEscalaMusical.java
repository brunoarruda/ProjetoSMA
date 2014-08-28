package praticasSMA.praticaComportamentos;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteEscalaMusical extends Agent
{
    protected void setup(){
       System.out.println("Ol�, eu sou um agente.");
       System.out.println("Estou disparando meu comportamento ...");
       addBehaviour(new ComportamentoEscalaMusical(this));                        
    }
    
    // Finaliza��o do agente
    protected void takeDown()
    {
       System.out.println("A escala musical terminou");
    }
}