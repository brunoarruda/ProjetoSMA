package praticasSMA.praticaOntologia;

import jade.core.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
 
@SuppressWarnings("serial")
public class AgenteComprador extends Agent 
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
       
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontologia);
      addBehaviour(new ComportamentoComprador(this, codec, ontologia));
   }
}