package projeto;

import java.util.EnumSet;

import projeto.modelo.Competencia;

public class Teste {

	public static void main(String[] args) throws InterruptedException 
    {
		EnumSet competenciasNecessarias;
		EnumSet agenteIncompetente;
		EnumSet agenteCompetente = EnumSet.noneOf(Competencia.class);
		
		competenciasNecessarias = EnumSet.of(Competencia.AI, Competencia.BACKEND);
		agenteIncompetente = EnumSet.of(Competencia.AI);
	
		agenteCompetente = EnumSet.of(Competencia.AI, Competencia.BACKEND);
		
		if(agenteIncompetente.containsAll(competenciasNecessarias)){
			System.out.println("Agente incompetente consegue fazer");
		} else{
			System.out.println("Agente incompetente NÃO consegue fazer");
		}
		Object[] objeto = {competenciasNecessarias};
		
		if(agenteCompetente.containsAll(competenciasNecessarias)){
			System.out.println("Agente competente consegue fazer");
		} else{
			System.out.println("Agente competente NÃO consegue fazer");
		}
    }

}
