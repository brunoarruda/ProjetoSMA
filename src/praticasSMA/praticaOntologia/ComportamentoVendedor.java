package praticasSMA.praticaOntologia;

import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SuppressWarnings("serial")
public class ComportamentoVendedor extends SimpleBehaviour 
{ 
   private boolean finished = false;
   Codec codec;
   Ontology ontologia;
 
   public ComportamentoVendedor(Agent a, Codec codec, Ontology ontologia) 
   {
      super(a);
      this.codec = codec;
      this.ontologia = ontologia;
   }
 
   public void action() 
   {
      try
      {
         System.out.println("\n Eu sou o agente Vendedor ");
         System.out.println("\nDigite o nome do DESTINATARIO (o nome dado ao Agente Comprador ao lancar o -container): ");
         BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
         String respuesta = buff.readLine();
         AID r = new AID();
         r.setLocalName(respuesta);
         ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
         msg.setSender(myAgent.getAID());
         msg.addReceiver(r);
         msg.setLanguage(codec.getName());
         msg.setOntology(ontologia.getName());
 
         System.out.println("\nDigite o NOME da fruta a ser vendida:");
         respuesta = buff.readLine();
         Fruta fru = new Fruta();
         fru.setNome(respuesta);
         System.out.println("\nDigite o preco da fruta a ser vendida:");
         respuesta = buff.readLine();
         fru.setPreco(Integer.parseInt(respuesta));
 
         Oferta of = new Oferta();
         of.setFruta(fru);
 
         myAgent.getContentManager().fillContent(msg, of);
 
         myAgent.send(msg);
      }
      catch (java.io.IOException io)
      {
         System.out.println(io);
      }
      catch (jade.content.lang.Codec.CodecException ce) 
      {
         System.out.println(ce);
      }
      catch (jade.content.onto.OntologyException oe) 
      {
         System.out.println(oe);
      }
      catch (Exception e)
      {
         System.out.println("\n\n... Terminando ...");
         finished=true;
      }
   }//Fim do metodo action()
 
   public boolean done() 
   { 
      return finished;
   }
} // Fim da classe ComportamentoVendedor