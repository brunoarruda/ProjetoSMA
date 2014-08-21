package aulas.primeirosProgramas.P4;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CriarAgenteArgs {

    static ContainerController containerController;
    static AgentController agentController;

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        startMainContainer("127.0.0.1", Profile.LOCAL_PORT, "UFABC");

        String tempo[] = new String[1];
        tempo[0] = "200";
        addAgent(containerController, "Graca", AgenteImpressorArgs.class.getName(), tempo );
        
        tempo[0] = "400";
        addAgent(containerController, "João", AgenteImpressorArgs.class.getName(), tempo );

        tempo[0] = "600";
        addAgent(containerController, "Carlos", AgenteImpressorArgs.class.getName(), tempo );

        
        //adicionando agente RMA
        //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
        addAgent(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
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