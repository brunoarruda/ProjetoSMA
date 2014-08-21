package aulas.praticasSMA.praticaPaginasAmarelas.primeiroRegistro;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

import java.util.Iterator;

@SuppressWarnings("serial")
public class AgenteBusca extends Agent 
{
   protected void setup() 
   {
      /*Este tempo de sincronização é importante para dar um tempo
      *para o AgenteRegistro iniciar suas atividades. Isto porque o 
      *AgenteBusca ira solicitar servicos do AgenteRegistro
      */
      try
      {
         Thread.sleep(6000);
      }
      catch(Exception e)
      {
         System.out.println("Erro: " + e);
      }

     //Criação de um objeto capaz de ser manipulado pelo DF 
     DFAgentDescription template = new DFAgentDescription();

     //crio um objeto contendo a descricao do servico
     ServiceDescription sd = new ServiceDescription();
     sd.setType("Tipo 1"); //defino o tipo de servico
     /*Neste momento poderia definir outras caracteristicas
     * do servico buscado para filtrar melhor a busca.
     * No caso, vamos buscar por servicos do tipo "Tipo1 " */

     //adiciono o servico no objeto da classe DFAgentDescription
     template.addServices(sd);
     try 
     {
        //Vou buscar pelos agentes
        //A busca retorna um array DFAgentDescription
        //O parametro this indica o agente que esta realizando a busca
        //DFAgentDescription[] result = DFService.search(this, template);
         DFAgentDescription[] result = DFService.search(this, template);

        //Imprimo os resultados
        for (int i = 0; i < result.length; i++) 
        {
           //result[i].getName() fornece o AID do agente
           String out = result[i].getName().getLocalName();

           //Para obter os servicos do agente invocamos
           //o metodo getAllServices();
           Iterator<?> iter = result[i].getAllServices();

           while (iter.hasNext()) 
           {
              //Extraimos os servicos para um objeto ServiceDescription
              ServiceDescription SD = (ServiceDescription) iter.next();
              //Capturamos o nome do servico
              out += " " + SD.getName();      
           }
          
           //Os servicos de cada agente sao impressos na tela
           System.out.println(out);
       } //fim do laco for
     } 
     catch (FIPAException e) 
     {
        e.printStackTrace();
     }
   }//Fim do metodo setup()
}//Fim da classe AgenteBusca