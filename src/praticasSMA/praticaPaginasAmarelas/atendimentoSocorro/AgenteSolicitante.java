package praticasSMA.praticaPaginasAmarelas.atendimentoSocorro;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class AgenteSolicitante extends Agent 
{
   protected void setup() 
   {
      //Captura argumentos
      Object[] args = getArguments();
      if (args != null && args.length > 0) 
      {
         String argumento = (String) args[0];
         //Se o argumento eh "fogo"
         if (argumento.equalsIgnoreCase("fogo")) 
         {
            ServiceDescription servico = new ServiceDescription();
            //O servico eh "apagar fogo"
            servico.setType("apaga fogo");
            //busca por quem fornece o servico
            busca(servico, "fogo");
         }
         //Se o argumento eh "ladrao"
         if (argumento.equalsIgnoreCase("ladrao")) 
         {
            ServiceDescription servico = new ServiceDescription();
            //O servico "prender o ladrao"
            servico.setType("prende ladrao");
            busca(servico, "ladrao");
         }
         //Se o argumento eh "doente"
         if (argumento.equalsIgnoreCase("doente")) 
         {
            ServiceDescription servico = new ServiceDescription();
            //O servico eh "salvar vidas"
            servico.setType("salva vidas");
            busca(servico, "doente");
         }
         
         //Comportamento para receber mensagens
         addBehaviour(new RecebeMensagens(this));
        }//Fim do if args
   }//Fim do metodo setup()
   
   //Metodo que realiza a busca nas paginas amarelas da plataforma
   protected void busca(final ServiceDescription sd, final String Pedido) 
   {
      //A cada minuto tenta buscar por agentes que fornecem
      //o servico
      addBehaviour(new BuscaServico(this, 8000, sd, Pedido));
   }
   
   private class BuscaServico extends TickerBehaviour 
   {   
      ServiceDescription sd = null;
      String Pedido = null;
      
      public BuscaServico(Agent a, long tempo, ServiceDescription sd, String Pedido) 
      {
         super(a, tempo);
         this.sd = sd;
         this.Pedido = Pedido;
      }
      
      public void onTick()
      {
         DFAgentDescription dfd = new DFAgentDescription();
         dfd.addServices(sd);
         try 
         {
            DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
            if (resultado.length != 0) 
            {
               ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
               msg.addReceiver(resultado[0].getName());
               msg.setContent(Pedido);
               myAgent.send(msg);
               stop(); //finaliza comportamento
            }
         } 
         catch (FIPAException e) 
         {
            e.printStackTrace();
         }
      }//Fim do metodo onTick()
   }//Fim da classe BuscaServico
   
   //CRIANDO AS CLASSES PRIVADAS
   private class RecebeMensagens extends CyclicBehaviour 
   {   
      public RecebeMensagens(Agent a) 
      {
         super(a);
      }
      
      public void action() 
      {
         ACLMessage msg = receive();
         if (msg != null) 
         {
            System.out.println(msg.getSender() + " : " + msg.getContent());
         }
         else
            block();
      }//Fim do metodo action()
   }
 
}//Fim da classe AgenteSolicitante