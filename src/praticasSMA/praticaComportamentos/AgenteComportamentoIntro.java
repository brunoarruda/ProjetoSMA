package praticasSMA.praticaComportamentos;

import jade.core.Agent;

@SuppressWarnings("serial")
public class AgenteComportamentoIntro extends Agent
{
    protected void setup(){
       System.out.println("Olá, eu sou um agente.");
       System.out.println("Estou disparando meu comportamento ...");
       addBehaviour(new MeuPrimeiroComportamento(this));                        
       addBehaviour(new MeuSegundoComportamento(this));   
    }   
}