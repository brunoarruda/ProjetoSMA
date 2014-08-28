package praticasSMA.praticaOntologia;

import jade.content.lang.*;
import jade.content.onto.*;
import jade.content.ContentElement;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@SuppressWarnings("serial")
public class ComportamentoComprador extends SimpleBehaviour 
{
   private boolean finished = false;
   Codec codec;
   Ontology ontologia;
      
   public ComportamentoComprador(Agent a, Codec codec, Ontology ontologia) 
   {
      super(a);
      this.codec = codec;
      this.ontologia = ontologia;
   }
 
   public void action() 
   {
      System.out.println("Eu sou o Comprador....");
      System.out.println("Estou esperando oferta do Vendedor....");
 
      MessageTemplate mt = MessageTemplate.and(
            MessageTemplate.MatchLanguage(codec.getName()),
            MessageTemplate.MatchOntology(ontologia.getName()));
      ACLMessage  msg = myAgent.blockingReceive(mt);
 
      try 
      { 
         if(msg != null)
         {
            if(msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD)
            {
               System.out.println("Mensagem NOT UNDERSTOOD recebida");
            }
            else
            {
               if(msg.getPerformative()== ACLMessage.INFORM)
               {
                  ContentElement ce = myAgent.getContentManager().extractContent(msg);
                  if (ce instanceof Oferta)
                  {
                     // Recebida uma mensagem INFORM con conteudo correto
                     Oferta of = (Oferta) ce;
                     Fruta fru = of.getFruta();
                     System.out.println("Mensagem recebida:");
                     System.out.println("Nome: " + fru.getNome());
                     System.out.println("Preco: " + fru.getPreco());
 
                     //Compramos
                     Comprar compra = new Comprar();
                     compra.setFruta(fru);
                     ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                     msg2.setLanguage(codec.getName());
                     msg2.setOntology(ontologia.getName());
                     msg2.setSender(myAgent.getAID());
                     msg2.addReceiver(msg.getSender());
                     myAgent.getContentManager().fillContent(msg2,compra);
                     myAgent.send(msg2);
                     System.out.println("Compra solicitada.");
                  }
                  else
                  {
                     // Recebido uma mensagem INFORM com conteudo incorreto
                     ACLMessage reply = msg.createReply();
                     reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                     reply.setContent("( UnexpectedContent (expected ping))");
                     myAgent.send(reply);
                  }
               }
               else 
               {
                  // Recebida uma performative incorreta
                  ACLMessage reply = msg.createReply();
                  reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                  reply.setContent("( (Unexpected-act "+ACLMessage.getPerformative(msg.getPerformative())+")( expected (inform)))");
                  myAgent.send(reply);
               }
            }
         }
         else
         {
            //System.out.println("No message received");
         }
      }//Fim do try
      catch (jade.content.lang.Codec.CodecException ce) 
      {
            System.out.println(ce);
      }
      catch (jade.content.onto.OntologyException oe) 
      {
         System.out.println(oe);
      }
   }//Fim do metodo action()
 
   public boolean done() 
   {
      return finished;
   }
} //Fim da clase ComportamentoComprador