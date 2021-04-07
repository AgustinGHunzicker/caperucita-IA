package frsf.cidisi.exercise.aspiradora.search;

import domain.Escenario;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    	EstadoCaperucita estado = (EstadoCaperucita) agentState;
    	
        if( estado.gethabitacionesSucias().isEmpty()
        	&& Escenario.todasVisitadas(estado.getmapaHabitaciones()) ){
        		return true;      
        }
        else
        	return false;
        
	}
    
}