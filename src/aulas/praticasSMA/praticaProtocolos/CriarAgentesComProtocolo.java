package aulas.praticasSMA.praticaProtocolos;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CriarAgentesComProtocolo 
{
    static ContainerController containerController;
    static AgentController agentController;

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        //startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT, "UFABC");
        startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "AtendimentoDesastres");
        //adicionando agente
        //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao)
        addAgent(containerController, "C1", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );
        addAgent(containerController, "C2", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );
        addAgent(containerController, "C3", AgenteFIPARequestCentraldeBombeiros.class.getName(), null );

        String[] pedidoSocorro = new String[3];
        pedidoSocorro[0] = "C1";
        pedidoSocorro[1] = "C2";
        pedidoSocorro[2] = "C3";
        addAgent(containerController, "Alarmado", AgenteFIPARequestAlarmado.class.getName(), pedidoSocorro );

        //adicionando agente RMA
        //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
        addAgent(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
        
        //Criando o agente Sniffer e definindo quais agentes ele irá controlar
        addAgent(containerController, "Sniffer", "jade.tools.sniffer.Sniffer", 
           new Object[]{"C1", ";", "C2", ";", "C3", ";", "Alarmado"});
    }

    public static void startMainContainer(String host, String port, String name) {
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        profile.setParameter(Profile.PLATFORM_ID, name);
        
        containerController = runtime.createMainContainer(profile);
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