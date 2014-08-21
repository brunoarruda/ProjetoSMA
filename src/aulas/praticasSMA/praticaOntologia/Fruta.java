package aulas.praticasSMA.praticaOntologia;

import jade.content.Concept;
 
@SuppressWarnings("serial")
public class Fruta implements Concept 
{ 
   private String nome;
   private int preco;
 
   public String getNome() 
   {
      return nome;
   }
 
   public void setNome(String n) 
   {
      nome = n;
   }
 
   public int getPreco() 
   {
      return preco;
   }
 
   public void setPreco(int p)
   {
      preco = p;
   }
}