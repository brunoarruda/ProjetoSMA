package aulas.praticasSMA.praticaOntologia;
import jade.content.AgentAction;
 
@SuppressWarnings("serial")
public class Comprar implements AgentAction 
{ 
   private Fruta fruta;
 
   public Fruta getFruta() 
   {
      return fruta;
   }
 
   public void setFruta(Fruta f) 
   {
      fruta = f;
   }
}