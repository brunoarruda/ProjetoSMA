package P3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
public class MeuAgente extends Agent{
    
    protected void setup(){
       System.out.println("Olá, eu sou um agente.");
       System.out.println("Estou disparando meu comportamento ...");
       addBehaviour(new MeuComportamento(this));                        
    }
}