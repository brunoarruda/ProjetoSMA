package Aulas.Comportamentos;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
public class ComportamentoOneShot extends OneShotBehaviour 
{
    public ComportamentoOneShot (Agent a)
    {
       super (a);        
    }
    public void action()
    {
        //codigo a ser executado
        System.out.println("Estou no comportamento onShot()");
        myAgent.doDelete();
        System.out.println("O m�todo action() vai executar todos seus comandos, mesmo com a chamada do m�todo doDelete()");
        System.out.println("Porque quem controla a execu��o dos comportamentos � o escalonador da plataforma JADE");
    }
}