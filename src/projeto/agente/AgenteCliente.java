package projeto.agente;

import projeto.comportamento.Cliente.Comunicar;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

@SuppressWarnings("serial")
public class AgenteCliente extends Agent
{ 
	
	private String agenteContato = "Janete";
	private String msgPedidoDeServico = "ME AJUDAAAAAAA!";
	
    protected void setup()
    {
        System.out.println("Olá Sistema. ");
        System.out.println("Sou o cliente: " + getLocalName());
        System.out.println("Passo tudo o que tem que ser feito!\n");
        
        try{
        Thread.sleep(3000);
        
        OneShotBehaviour comunicarMsg = new Comunicar(agenteContato,msgPedidoDeServico);
        addBehaviour(comunicarMsg);
        
        //Mando mensagem para o programador requisitando a resolucao de uma tarefa
        }catch(Exception e){}
    }
    
    public String getAgenteContato() {
		return agenteContato;
	}

	public void setAgenteContato(String agenteContato) {
		this.agenteContato = agenteContato;
	}

	public String getMsgPedidoDeServico() {
		return msgPedidoDeServico;
	}

	public void setMsgPedidoDeServico(String msgPedidoDeServico) {
		this.msgPedidoDeServico = msgPedidoDeServico;
	}
}