package aulas.praticasSMA.criarVariosContainers.containersMesmaMaquina.comComunicacao;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CriarAgentesComProtocolo_DiferentesContainers 
{
   static ContainerController containerController;
   static AgentController agentController;
   static AgentContainer container[] = new AgentContainer [2];
   
   static jade.core.Runtime runtime;

   public static void main(String[] args) throws InterruptedException 
   {
      //iniciando main container
      //startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT, "UFABC");
      startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "AtendimentoDesastres");
      
      container[0] = null;
      container[1] = null;
      container[0]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "CentralBombeiro", runtime, container[0] );
      container[1]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "Populacao", runtime, container[1] );
        
      //adicionando agente
      //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao)
      addAgentContainer(container[0], "C1", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );
      addAgentContainer(container[0], "C2", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );
      addAgentContainer(container[0], "C3", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );
      
      String[] pedidoSocorro = new String[3];
      pedidoSocorro[0] = "C1";
      pedidoSocorro[1] = "C2";
      pedidoSocorro[2] = "C3";
      addAgentContainer(container[1], "Alarmado", AgenteFIPARequestAlarmado.class.getName(), pedidoSocorro );

      //adicionando agente RMA
      //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
      addAgentMainContainer(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
        
      //Criando o agente Sniffer e definindo quais agentes ele irá controlar
      addAgentMainContainer(containerController, "Sniffer", "jade.tools.sniffer.Sniffer", 
            new Object[]{"C1", ";", "C2", ";", "C3", ";", "Alarmado"});
   }
   public static void startMainContainer(String host, String port, String name) {
        runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);
        containerController = runtime.createMainContainer(profile);
    }

   public static AgentContainer startContainer(String host, String port, String nomeContainer, jade.core.Runtime runtime, AgentContainer container) {
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.CONTAINER_NAME, nomeContainer);
        container = runtime.createAgentContainer(profile);
        try
        {
           container.start();
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e);
        }
        return container;
    }
   
    public static void addAgentMainContainer(ContainerController cc, String agent, String classe, Object[] args) { 
        try {
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e);
        }        
    }
    
    public static void addAgentContainer(AgentContainer cc, String agent, String classe, Object[] args) {        
        try {
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("Erro: " + e);
        }        
    }    
}