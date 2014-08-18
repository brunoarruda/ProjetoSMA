package Mensagens;

import P1.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CriarAgentesSolictandoAjuda {

    static ContainerController containerController;
    static AgentController agentController;

    public static void main(String[] args) throws InterruptedException 
    {
        //iniciando main container
        startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT, "UFABC");
        
        String[] argumento = new String[1];
        argumento[0] = "fogo";
        //argumento[0] = "ladrao";
        //argumento[0] = "doente";
        //argumento = null;

        //adicionando agente
        //SINTAXE: addAgent(container, nome_do_agente, classe, parametros de inicializacao)
        addAgent(containerController, "Solicitante", Solicitante.class.getName(), argumento);
        addAgent(containerController, "Bombeiro", Bombeiro.class.getName(), null);
        addAgent(containerController, "Medico", Medico.class.getName(), null);
        addAgent(containerController, "Policial", Policial.class.getName(), null);
        
        //adicionando agente RMA
        //addAgent(containerController, "rma", "jade.tools.rma.rma", null);
        addAgent(containerController, "rma", jade.tools.rma.rma.class.getName(), null);
        
        //fazendo sniffer deles
        addAgent(containerController, "Sniffer", "jade.tools.sniffer.Sniffer", new Object[]{"Solicitante", ";", "Medico", ";", "Bombeiro", ";", "Policial"});

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
            agentController = cc.createNewAgent(agent, classe, args);
            agentController.start();
        } catch (StaleProxyException s) {
            s.printStackTrace();
        }
    }
}