package aulas.praticasSMA.praticaComportamentos.FSMComportamento;

import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.Agent;
        
@SuppressWarnings("serial")
public class ComportamentoFSM extends FSMBehaviour
{ 
   //Define as constantes dos estados
   private static final String ONE_STATE = "EstadoUm";
   private static final String TWO_STATE = "EstadoDois";
   private static final String THREE_STATE = "EstadoTres";
   private static final String ERROR_STATE= "EstadoZero";
   
   //Define as constantes das transições
   private final int UM = 1;
   private final int DOIS = 2;
   private final int TRES = 3;
   private final int ZERO = 0;
   
@SuppressWarnings("unused")
private int transicao=0;
   private String entrada="";
 
   public ComportamentoFSM(Agent agente, String ent)
   {
      super(agente);
      entrada=ent;
   }

   public void onStart()
   {
      //Relaciona cada constante com o respectivo objeto do comportamento 
      registerFirstState(new OneBehaviour(),ONE_STATE);
      registerState(new TwoBehaviour(), TWO_STATE);
      registerState(new ThreeBehaviour(), THREE_STATE);
      registerLastState(new ErrorBehaviour(),ERROR_STATE);
      
      //Define as transições entre os estados 
      registerTransition(ONE_STATE, TWO_STATE, DOIS);
      registerTransition(TWO_STATE, THREE_STATE,TRES);
      registerTransition(THREE_STATE,ONE_STATE,UM);
      registerDefaultTransition(ONE_STATE, ERROR_STATE);
      registerDefaultTransition(TWO_STATE, ERROR_STATE);
      registerDefaultTransition(THREE_STATE, ERROR_STATE);
   }
 
   /*
    * Método chamado após a execução de cada sub-comportamento (cada filho), 
    * com o objetivo de verificar se o CompositeBehaviour deve terminar, ou não
    */
   protected boolean checkTermination(boolean currentDone,int currentResult)
   {
      System.out.println("   ** Terminado estado número: " + currentName);
      System.out.println("-----------------------------");
      return super.checkTermination(currentDone,currentResult);
   }
 
   public int getEntrada()
   {
      int tipoEvento = ZERO;
      if (entrada.length()<1) 
         return tipoEvento;
      else 
         tipoEvento=Integer.parseInt(entrada.substring(0,1));
      entrada=entrada.substring(1,entrada.length());
      
      System.out.println("tipoEvento = " + tipoEvento);
      System.out.println("entrada = " + entrada);
         
      return tipoEvento;
     
      /*
      int tipoEvento;
      String mensagem = "Você está no estado " + currentName;
      mensagem = mensagem + "\nDigite o evento a ser executado (1, 2, 3 ou 0): ";
      tipoEvento = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
      return tipoEvento;
      */
   }
   
   //CHAMANDO AS CLASSES PRIVADAS
private class OneBehaviour extends OneShotBehaviour 
   {
      public void action()
      {
          System.out.println("() Passei pelo primeiro estado");
      }
      public int onEnd() 
      { 
          return getEntrada();
      }
   }
 
   private class TwoBehaviour extends OneShotBehaviour 
   {
      public void action()
      {
          System.out.println("() Passei pelo segundo estado");  
      }
      public int onEnd() 
      { 
          return getEntrada();
      }
   }
 
   private class ThreeBehaviour extends OneShotBehaviour 
   {
      public void action()
      {
          System.out.println("() Passei pelo terceiro estado"); 
      }
      public int onEnd() 
      { 
          return getEntrada();
      }
   }
 
   private class ErrorBehaviour extends OneShotBehaviour 
   {
      public void action()
      {
          System.out.println("() Passei pelo estado default");
      }
   }
}