package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
		EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
		// Si esta en la posición de flores y tiene mas de 0 vidas gana
		// Si tiene cero vidas, pierde	
		return estadoCaperucita.getPosicionCaperucita().equals(estadoCaperucita.getPosicionFlores()) &&
				estadoCaperucita.getVidasRestantes() > 0;
	}

	@Override
	public String toString() {
		return "ObjetivoCaperucita{}";
	}
}