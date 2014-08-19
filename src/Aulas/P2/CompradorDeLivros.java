package Aulas.P2;

import jade.core.Agent;

public class CompradorDeLivros extends Agent
{
    private String livrosComprar; 
    
    protected void setup()
    {
        //imprime mensagem de Bem-Vindo
    System.out.println("Ol�!!! Eu sou o Agente Comprador "+ getLocalName() +" e estou pronto para comprar!");
        
        //captura o titulo do livro que comprara, que foi passado como argumento de inicializacao
        Object[] args = getArguments();
        if(args != null && args.length>0)
        {
           livrosComprar = (String) args[0];
           System.out.println("Pretendo comprar o livro: "+ livrosComprar);            
        }else 
        {
            //finaliza o agente
           System.out.println("N�o tenho livros para comprar!");
           doDelete(); //invoca a execucao do metodo takeDown()
        }
    }
    
    protected void takeDown() {
           System.out.println("Agente Comprador" + getAID().getName() + " est� finalizado");
    }
}