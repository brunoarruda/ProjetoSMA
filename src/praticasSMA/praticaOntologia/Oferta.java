package praticasSMA.praticaOntologia;

import jade.content.Predicate;
 
@SuppressWarnings("serial")
public class Oferta implements Predicate 
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