package aulas.praticasSMA.criarVariosContainers.containersMesmaMaquina;

import jade.core.Agent;

import java.util.Iterator;

@SuppressWarnings("serial")
public class AgenteAluno extends Agent
{    
   protected void setup()
   {
       String mensagem="";
       super.setup();
       
       //monta a mensagem de boas vindas e com informa��es gerais   
       mensagem = "Ol�!!! Eu sou o Agente Aluno " + getAID().getLocalName();
       mensagem = mensagem + "\nMeu nome na plataforma � " + getAID().getName();
       
       mensagem = mensagem + "\nMeus endere�os na plataforma s�o: ";
       Iterator<?> it = getAID().getAllAddresses();
       while(it.hasNext()) 
       {
          mensagem = mensagem + "- " + it.next() + "\n";
       }
       mensagem = mensagem + "Meu estado atual �: " + getAgentState();
       
       System.out.println(mensagem);                

    }//Fim do m�todo main()

    protected void takeDown() 
    {
        System.out.println("Agente Aluno" + getAID().getName() + 
                   " est� finalizado");
    }
}