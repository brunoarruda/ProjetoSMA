package aulas.praticasSMA.praticaPaginasBrancas;

import jade.core.Agent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

@SuppressWarnings("serial")
public class AgenteBuscaAMS extends Agent
{
   protected void setup() 
   {
      /*Este tempo de sincronização é importante para dar um tempo
      *para o agente Sniffer iniciar suas atividades.
      */       
      try
      {
         Thread.sleep(3000);
      }
      catch(Exception e)
      {
         System.out.println("Erro: " + e);
      }
      
      try 
      {
         //Criação de um objeto capaz de ser manipulado pelo AMS
         AMSAgentDescription[] agentes = null;
         
         //Crio objeto SearchConstraints para definir
         //que desejo todos os resultados
         SearchConstraints c = new SearchConstraints();    
         //O metodo setMaxResults indica o numero de resultados
         //que desejo obter. Por definicao, -1 significa todos.
         c.setMaxResults (new Long(-1));
         
         //Busco pelos agentes
         //AMSService.search(agente que busca, vetor de retorno, caracteristicas)
         agentes = AMSService.search(this, new AMSAgentDescription(), c);
         
         //Capturo minha AID
         AID myAID = getAID();
         
         for(int i=0; i<agentes.length; i++) 
         {
            AID agenteID = agentes[i].getName();
            //Imprimo todos os agentes
            //Este agente sera identificado com **** para diferenciar
            //dos demais
            System.out.println((agenteID.equals(myAID)? "***": "    " )
                        + i + ": " + agenteID.getName() );
         }
      } 
      catch (FIPAException ex) 
      {
         ex.printStackTrace();
      }
   }//Fim do metodo setup()
}