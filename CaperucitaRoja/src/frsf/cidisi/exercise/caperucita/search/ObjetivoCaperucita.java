package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    	EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
    	
    	if ((estadoCaperucita.getposicionCaperucita().x == estadoCaperucita.getPosicionFlores().x) &&
    			(estadoCaperucita.getposicionCaperucita().y == estadoCaperucita.getPosicionFlores().y)) {
    		return true;
    	}
    	else {
    		return false;
    	}
        
	}
    
}