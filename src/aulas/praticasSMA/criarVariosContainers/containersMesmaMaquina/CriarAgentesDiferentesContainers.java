package aulas.praticasSMA.criarVariosContainers.containersMesmaMaquina;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CriarAgentesDiferentesContainers
{
   static ContainerController containerController;
   static AgentController agentController;
   static AgentContainer container[] = new AgentContainer [2];
   
   static jade.core.Runtime runtime;	

   public static void main(String[] args) throws InterruptedException 
   {
      //iniciando main container
      //startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT, "UFABC");
      startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "UFABC_EAD");
      
      container[0] = null;
      container[1] = null;
      container[0]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "ContainerProfessores", runtime, container[0] );
      container[1]=startContainer("127.0.0.1", Profile.LOCAL_PORT, "ContainerAlunos", runtime, container[1] );
        
      //adicionando agente
      //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao)
      String[] argumento = new String[1];
      argumento[0] = "Tiro d�vidas sobre o nível micro de SMAs";
      //argumento = null;
      addAgentContainer(container[0], "AgenteTutorSMA", AgenteTutorSMA.class.getName(), argumento);
      String[] argumento1 = new String[1];
      argumento1[0] = "Tiro d�vidas sobre comandos sequenciais e de desvio condicional, na linguagem Java";
      addAgentContainer(container[0], "AgenteTutorPI", AgenteTutorPI.class.getName(), argumento1);
      String[] argumento2 = new String[1];
      argumento2[0] = "Tiro d�vidas sobre conceitos te�ricos de L�gica Fuzzy";
      addAgentContainer(container[0], "AgenteTutorLogicaFuzzy", AgenteTutorLogicaFuzzy.class.getName(), argumento2);

      String[] argumento3 = new String[1];
      argumento3[0] = null;
      addAgentContainer(container[1], "AgenteGraca", AgenteAluno.class.getName(), argumento3);

      //adicionando agente RMA
      //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
      addAgentMainContainer(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
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