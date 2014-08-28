package praticasSMA.criarVariosContainers.containersDiferentesMaquinas;

import java.util.Iterator;

@SuppressWarnings("serial")
public class AgenteTutorPI extends AgenteTutor
{
    protected void setup()
    {
       String servico = "", mensagem="";
       super.setup();
       
       //monta a mensagem de boas vindas e com informa��es gerais   
       mensagem = "Ol�!!! Eu sou o Agente Tutor " + getAID().getLocalName();
       mensagem = mensagem + "\nMeu nome na plataforma � " + getAID().getName();
       
       mensagem = mensagem + "\nMeus endere�os na plataforma s�o: ";
       Iterator<?> it = getAID().getAllAddresses();
       while(it.hasNext()) 
       {
          mensagem = mensagem + "- " + it.next() + "\n";
       }
       mensagem = mensagem + "Meu estado atual �: " + getAgentState();
       
       //captura o servi�o que o agente oferecer� � ag�ncia
       Object[] argumentos = getArguments();
       
       if(argumentos != null && argumentos.length>0)
       {
          servico = (String) argumentos[0];
          mensagem = mensagem + "\nPosso realizar o seguinte servi�o: " + servico;
        }else 
        {
           //finaliza o agente
           mensagem = mensagem + "\nN�o posso realizar nenhum servi�o na sociedade";
           doDelete(); //invoca a execucao do metodo takeDown()
        } 
         System.out.println(mensagem);                

    }//Fim do m�todo main()

    protected void takeDown() 
    {
        System.out.println("Agente Tutor" + getAID().getName() + 
                   " est� finalizado");
    }
}