package praticasSMA.praticaOntologia;
 
import jade.content.onto.*;
import jade.content.schema.*;
 
@SuppressWarnings("serial")
public class FrutasOntology extends Ontology 
{
   // Nome da ontologia
   public static final String ONTOLOGY_NAME = "ontología de frutas";
 
   // Vocabulario da ontología que os agentes vao usar
   public static final String FRUTA = "Fruta";
   public static final String FRUTA_NOME = "nome";
   public static final String FRUTA_PRECO = "preco";
 
   public static final String OFERTA = "Oferta";
   public static final String OFERTA_FRUTA = "fruta";
 
   public static final String COMPRAR = "Comprar";
   public static final String COMPRAR_FRUTA = "fruta";
 
   // Instancia da ontologia (que sera unica)
   private static Ontology instancia = new FrutasOntology();
 
   // Metodo para ter acesso aa instancia da ontología
   public static Ontology getInstance() 
   {
      return instancia;
   }
 
   // Construtor da classe
   private FrutasOntology() 
   {
      // frutasOntology extende a ontología basica da plataforma JADE
      super(ONTOLOGY_NAME, BasicOntology.getInstance());
 
      try 
      {
         // Cria os elementos do Schema
         add(new ConceptSchema(FRUTA), Fruta.class);
         add(new PredicateSchema(OFERTA), Oferta.class);
         add(new AgentActionSchema(COMPRAR), Comprar.class);
 
         // Estrutura do esquema para eo conceito FRUTA
         ConceptSchema cs = (ConceptSchema) getSchema(FRUTA);
         cs.add(FRUTA_NOME, (PrimitiveSchema) getSchema(BasicOntology.STRING));
         cs.add(FRUTA_PRECO, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
 
         // Estrutura do esquema para o predicado OFERTA
         PredicateSchema ps = (PredicateSchema) getSchema(OFERTA);
         ps.add(OFERTA_FRUTA, (ConceptSchema) getSchema(FRUTA));
 
         // Estrutura do esquema para a acao COMPRAR
         AgentActionSchema as = (AgentActionSchema) getSchema(COMPRAR);
         as.add(COMPRAR_FRUTA, (ConceptSchema) getSchema(FRUTA));
       }
       catch (OntologyException oe) 
       {
           oe.printStackTrace();
       }
   }//Fim do metodo construtor
}