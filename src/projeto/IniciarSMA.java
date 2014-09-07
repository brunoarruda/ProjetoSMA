package projeto;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import projeto.agente.*;
import projeto.modelo.Competencia;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class IniciarSMA {

	static ContainerController containerController;
	static AgentController agentController;

	public static void main(String[] args) throws InterruptedException {
		// iniciando main container
		// startMainContainer(Profile.LOCAL_HOST, Profile.LOCAL_PORT);
		startMainContainer("127.0.0.1", Profile.LOCAL_PORT);

		// adicionando agente
		// SINTAXE: addAgent(container, nome_do_agente, classe, parametros de
		// inicializacao

		List<String> nomesProgramadores = new ArrayList<String>();
		String nomeAgente;

		Random r = new Random(1);

		nomeAgente = "Agente_Programador0";
		nomesProgramadores.add(nomeAgente);
		Object[] competenciasSenior = { EnumSet.allOf(Competencia.class) };
		addAgent(containerController, nomeAgente, AgenteProgramador.class.getName(), competenciasSenior);

		for (int i = 1; i < 9; i++) {
			nomeAgente = "Agente_Programador" + i;
			nomesProgramadores.add(nomeAgente);

			EnumSet<Competencia> competenciasDoProgramador = EnumSet.noneOf(Competencia.class);
			for (Competencia competenciaDoUniverso : Competencia.values()) {
				if (r.nextBoolean()) {
					competenciasDoProgramador.add(competenciaDoUniverso);
				}
			}

			Object[] competencias = { competenciasDoProgramador };
			addAgent(containerController, nomeAgente, AgenteProgramador.class.getName(), competencias);

		}

		// Varios agentes Programadores

		// Um agente Gerente
		addAgent(containerController, "Agente_Gerente", AgenteGerente.class.getName(), nomesProgramadores.toArray());

		// Um agente Secret�ria
		addAgent(containerController, "Agente_Diretor", AgenteDiretor.class.getName(), null);

		// Um agente Cliente
		addAgent(containerController, "Agente_Cliente", AgenteCliente.class.getName(), null);

		// adicionando agente RMA
		addAgent(containerController, "rma", "jade.tools.rma.rma", null);
		// addAgent(containerController, "rma",
		// jade.tools.rma.rma.class.getName(), null);

		// Criando o agente Sniffer e definindo quais agentes ele irá controlar
		addAgent(containerController, "Sniffer", "jade.tools.sniffer.Sniffer", new Object[] { "Agente_Gerente", ";", "Agente_Diretor", ";", "Agente_Cliente",
				";", "Agente_Programador0", ";", "Agente_Programador1", ";", "Agente_Programador2", ";", "Agente_Programador3", ";", "Agente_Programador4", ";", "Agente_Programador5",
				";", "Agente_Programador6", ";", "Agente_Programador7", ";", "Agente_Programador8" });
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