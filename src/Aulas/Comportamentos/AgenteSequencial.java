package Aulas.Comportamentos;

import jade.core.Agent;import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class AgenteSequencial extends Agent{
    
    protected void setup() {
        //mensagem de inicializa��o do agente
        System.out.println("Olá! Meu nome é " + getLocalName());
        System.out.println("Vou executar três comportamentos:");
        
        //criamos um objeto da classe SequentialBehaviour
 SequentialBehaviour comportamento = new SequentialBehaviour(this) {

               public int onEnd() {
               myAgent.doDelete();
               return 0;
            }
      
  };
  //adicionamos seu primeiro comportamento
        comportamento.addSubBehaviour(new WakerBehaviour(this, 500) {
            long t0 = System.currentTimeMillis();
            protected void onWake() {
                System.out.println((System.currentTimeMillis() - t0) +": Executei meu primeiro comportamento após meio segundo!");
            }
        });
        
  //adicionamos seu segundo comportamento
        comportamento.addSubBehaviour(new OneShotBehaviour(this) {

                 public void action() {
                System.out.println("Executei meu segundo comportamento");
            }
        });
        
 //adicionamos seu terceiro comportamento
        comportamento.addSubBehaviour(new TickerBehaviour(this, 700) {
              int exec=0;
              long t1 = System.currentTimeMillis();
               protected void onTick() {
                   if(exec==3) stop();
                   else {
                       System.out.println((System.currentTimeMillis()-t1)+ ": Estou executando meu terceiro comportamento");
                       exec++;
                   }
              }
        });
        
        //acionamos sua execucao;      
       addBehaviour(comportamento);                  
    }  
   
   protected void takeDown() {
       System.out.println("Fui finalizado com sucesso");    
   }
}