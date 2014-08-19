package Aulas.Comportamentos;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
public class AgenteFSM extends Agent{
    
    protected void setup() {
        
        FSMBehaviour compFSM = new FSMBehaviour(this){
       
            public int onEnd() {
                System.out.println("Comportamento FSM finalizado com sucesso!");
                return 0;
            }
            
        };
        //registramos o primeiro comportamento - X
      compFSM.registerFirstState(new OneShotBehaviour(this) {

            int c=0;
            public void action() {
            System.out.println("Executando Comportamento X");
            c++;
            }

           
            public int onEnd() {
            return (c>4? 1:0);
            }            
        }, "X");
        
        //registramos outro estado - Z
        compFSM.registerState(new OneShotBehaviour(this) {


            public void action() {
                 System.out.println("Executando Comportamento Z");
           }
               public int onEnd() {
            return 2;
            }  
            
        }, "Z");
        
        //registramos o �ltimo estado - Y
        compFSM.registerLastState(new OneShotBehaviour(this) {
    
            public void action() {
              System.out.println("Executando meu �ltimo comportamento.");
            }
        }, "Y");
  
        //definimos as transi��es
        compFSM.registerTransition("X", "Z", 0);  //X --> Z, caso onEnd() do X retorne 0
        compFSM.registerTransition("X", "Y", 1);  //X --> Y, caso onEnd() do X retorne 1 
        
        //definimos uma transi��o padr�o (n�o importa tipo de retorno)
        //como a m�quina � finita, temos que zerar os estados X e Z --> new String[]{"X","Z"}
        compFSM.registerDefaultTransition("Z", "X", new String[]{"X","Z"});
       //Podemos tamb�m registrar uma transi��o quando o estado Z retornar 2
       //compFSM.registerTransition("Z", "X", 2);  
        
        //acionamos o comportamento
        addBehaviour(compFSM);
        
    }
    }

