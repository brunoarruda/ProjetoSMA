package praticasSMA.criarVariosContainers.containersMesmaMaquina;

import jade.core.Profile;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CriarAgentesTodosNoMainContainer 
{
   static ContainerController containerController;
   static AgentController agentController;   static Runtime runtime;
   static AgentContainer container[] = new AgentContainer [2];
   public static void main(String[] args) throws InterruptedException 
   {
      //iniciando main container
      //startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT, "UFABC");
      startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "UFABC_EAD");            
      container[0] = null;
      container[1] = null;
      container[0]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "ContainerTutores", runtime, container[0] );
      container[1]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "ContainerAlunos", runtime, container[1] );
      //adicionando agente
      //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao)
      String[] argumento = new String[1];
      argumento[0] = "Tiro dúvidas sobre o nível micro de SMAs";
      //argumento = null;
      addAgent(containerController, "AgenteTutorSMA", AgenteTutorSMA.class.getName(), argumento);
      String[] argumento1 = new String[1];
      argumento1[0] = "Tiro dúvidas sobre comandos sequenciais e de desvio condicional, na linguagem Java";
      addAgent(containerController, "AgenteTutorPI", AgenteTutorPI.class.getName(), argumento1);
      String[] argumento2 = new String[1];
      argumento2[0] = "Tiro dúvidas sobre conceitos teóricos de Lógica Fuzzy";
      addAgent(containerController, "AgenteTutorLogicaFuzzy", AgenteTutorLogicaFuzzy.class.getName(), argumento2);
      String[] argumento3 = new String[1];
      argumento3[0] = null;
      addAgent(containerController, "AgenteGraca", AgenteAluno.class.getName(), argumento3);
      //adicionando agente RMA
      //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
      addAgent(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
   }
   public static void startMainContainer(String host, String port, String name) {
        runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);
        containerController = runtime.createMainContainer(profile);
    }

   public static AgentContainer startContainer(String host, String port, String nomeContainer, 
           jade.core.Runtime runtime, AgentContainer container) {
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
   
    public static void addAgent(ContainerController cc, String agent, String classe, Object[] args) {
        try {
            //agentController = cc.createNewAgent(agent, classe, args);
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
}