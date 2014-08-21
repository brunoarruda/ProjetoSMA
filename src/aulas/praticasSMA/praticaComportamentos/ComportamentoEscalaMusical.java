package aulas.praticasSMA.praticaComportamentos;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

@SuppressWarnings("serial")
public class ComportamentoEscalaMusical extends Behaviour
{
    int estado=0;
      
    public ComportamentoEscalaMusical(Agent a)
    {
       super(a);
    }   
    
    public void action() 
    {
       switch(estado)
       {
          case 0: System.out.println("Do"); break;
          case 1: System.out.println("Re"); break;
          case 2: System.out.println("Mi"); break;
          case 3: System.out.println("Fa"); break;
          case 4: System.out.println("Sol");break;
          case 5: System.out.println("La"); break;
          case 6: System.out.println("Si"); break;
          case 7:
          {
             System.out.println("Do");
             myAgent.doDelete();
             break;
          }
       }
       estado++;
    }

    //Verifica se o comportamento está finalizado, ou não
    public boolean done() 
    {
       return (estado > 7);
    }
}