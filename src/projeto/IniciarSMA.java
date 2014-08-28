
package projeto;

import projeto.agente.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class IniciarSMA {

    static ContainerController containerController;
    static AgentController agentController;

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        //startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT);
        startMainContainer("127.0.0.1", Profile.LOCAL_PORT);
        
        //adicionando agente
        //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao 

        //Varios agentes Programadores
        addAgent(containerController, "Agente_Programador1", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador2", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador3", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador4", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador5", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador6", AgenteProgramador.class.getName(), null);
        addAgent(containerController, "Agente_Programador7", AgenteProgramador.class.getName(), null);
        
        //Um agente Gerente
        addAgent(containerController, "Agente_Gerente", AgenteGerente.class.getName(), null);
        
        //Um agente Secretária
        addAgent(containerController, "Agente_Diretor", AgenteDiretor.class.getName(), null);
        
        //Um agente Cliente
        addAgent(containerController, "Agente_Cliente", AgenteCliente.class.getName(), null);
        
        
        //adicionando agente RMA
        addAgent(containerController, "rma", "jade.tools.rma.rma", null);
        //addAgent(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
        
        
        //Criando o agente Sniffer e definindo quais agentes ele irï¿½ controlar
        addAgent(containerController, "Sniffer", "jade.tools.sniffer.Sniffer", 
                                       new Object[]{"Agente_Gerente", ";", "Agente_Diretor", ";", "Agente_Cliente", ";", "Agente_Programador1"});      
    }

    public static void startMainContainer(String host, String port) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);

        containerController = runtime.createMainContainer(profile);
    }

    public static void addAgent(ContainerController cc, String agent, String classe, Object[] args) {
        try {
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
}