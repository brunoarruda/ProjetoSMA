package Aulas.P3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class MeuComportamento extends Behaviour{
int i=0;
      
public MeuComportamento(Agent a){
    super(a);
}   
    public void action() {
          System.out.println("* Olá Mundo! ... Meu nome é " + myAgent.getLocalName());
              i=i+1;    
    }

    public boolean done() {
  //caso este metodo retorne TRUE o comportamento será finalizado
        return i>3;
    }
}