package projeto.comportamento.secretaria;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

@SuppressWarnings("serial")
public class Comunicar extends OneShotBehaviour {

	private String agenteAlvo;
	private String conteudo;
	private String linguagem;
	
	public Comunicar(String agenteAlvo, String conteudo){
		this.agenteAlvo = agenteAlvo;
		this.conteudo = conteudo;
		this.linguagem = "Portugues";
	}
	
	@Override
	public void action() {
		
		// Criação do objeto ACLMessage
		ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);

		// Preencher os campos necessários da mensagem
		mensagem.setSender(myAgent.getAID());
		mensagem.addReceiver(new AID(agenteAlvo, AID.ISLOCALNAME));
		mensagem.setLanguage(linguagem);
		mensagem.setContent(conteudo);

		// Envia a mensagem aos destinatarios
		myAgent.send(mensagem);
	}
	
	
	
	
}
