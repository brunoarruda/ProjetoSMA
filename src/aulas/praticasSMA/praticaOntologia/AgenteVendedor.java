package aulas.praticasSMA.praticaOntologia;

import jade.core.*;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;
 
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
  
@SuppressWarnings("serial")
public class AgenteVendedor extends Agent 
{ 
   private Codec codec = new SLCodec();
   private Ontology ontologia = FrutasOntology.getInstance();
 
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

      /** Registrarse en el DF */
      DFAgentDescription dfd = new DFAgentDescription();
      ServiceDescription sd = new ServiceDescription();
      sd.setType("Vendedor");
      sd.setName(getName());
      sd.setOwnership("ARNOIA");
      dfd.setName(getAID());
      dfd.addServices(sd);
      try 
      {
         DFService.register(this,dfd);
      } 
      catch (FIPAException e) 
      {
         System.err.println(getLocalName()+" registration with DF unsucceeded. Reason: "+e.getMessage());
         doDelete();
      }
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontologia);
 
      addBehaviour(new ComportamentoVendedor(this, codec, ontologia));
   }//Fim do método setup()
 
   protected void takeDown() 
   {
      try 
      {
         DFService.deregister(this);
      }
      catch (FIPAException fe) 
      {
         fe.printStackTrace();
      }
   }//Fim do metodo takeDown()
}