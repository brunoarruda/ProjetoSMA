package aulas.praticasSMA.praticaPaginasAmarelas.atendimentoSocorro;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgentePolicial extends Agent 
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
       
      //Descricao do Servico
      ServiceDescription servico = new ServiceDescription();
      //Seu servico eh "salvar vidas"
      servico.setType("prende ladrao");
      servico.setName(this.getLocalName());
      registraServico(servico);
      String mensagem = "ladrao";
      String resp = "Vou prender o ladrao";
      addBehaviour(new RecebeMensagens(this, mensagem, resp));
   }//Fim do metodo setup()

   //metodo para registrar servico
   protected void registraServico(ServiceDescription sd) 
   {
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.addServices(sd);
      try 
      {
         DFService.register(this, dfd);
      } 
      catch (FIPAException e) 
      {
         e.printStackTrace();
      }
   }
   
   //CRIANDO AS CLASSES PRIVADAS
   private class RecebeMensagens extends CyclicBehaviour 
   {   
      String mensagem, resp;
      
      public RecebeMensagens(Agent a, String mensagem, String resp) 
      {
         super(a);
         this.mensagem = mensagem;
         this.resp = resp;
      }
      
      public void action() 
      {
         ACLMessage msg = receive();
         if (msg != null) 
         {
            if (msg.getContent().equalsIgnoreCase(mensagem)) 
            {
               ACLMessage reply = msg.createReply();
               reply.setContent(resp);
               myAgent.send(reply);
            }
         }
         else
            block();
      }
   }//Fim da classe RecebeMensagens
   
} //Fim da classe AgentePolicial