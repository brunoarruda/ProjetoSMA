package aulas.praticasSMA.praticaPaginasAmarelas.primeiroRegistro;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

@SuppressWarnings("serial")
public class AgenteRegistro extends Agent 
{
   protected void setup() 
   {    
      try
      {
         Thread.sleep(3000);
      }
      catch(Exception e)
      {
         System.out.println("Erro: " + e);
      }
 
      //Descri��o do agente que oferecera um servi�o (Servico 1)
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.setName(getAID()); //Informamos a AID do agente
      //Defini��o e descri��o do servi�o
      ServiceDescription sd = new ServiceDescription();
      sd.setType("Tipo 1"); //Tipo do Servico
      sd.setName("Servico1"); //Nome do Servico
      //Inser��o do servi�o na lista de servi�os da descricao do agente
      dfd.addServices(sd);
      
      //Vamos criar outro servico (Servico 2)
      sd = new ServiceDescription();
      sd.setType("Tipo 2");
      sd.setName("Servico2");
      //Inser��o do servi�o na lista de servi�os da descricao do agente
      dfd.addServices(sd);
      //Vamos registrar o agente no DF
      try 
      {
         //register(agente que oferece, descricao)
         DFService.register(this, dfd);
      } 
      catch (FIPAException e) 
      {
         e.printStackTrace();
      }
   }//Fim do metodo setup()
   
   protected void takeDown()
   {
      try
      {
         DFService.deregister(this); 
      }
      catch ( FIPAException e )
      {
         e.printStackTrace() ;
      }
   }
}