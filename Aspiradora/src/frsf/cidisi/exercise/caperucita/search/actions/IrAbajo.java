package frsf.cidisi.exercise.caperucita.search.actions;

import frsf.cidisi.exercise.caperucita.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrAbajo extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita agState = (EstadoCaperucita) s;
        
        //Si no es la primera habitaci�n, puede moverse a izquierda
        /*
        int habitacionActualIndex = agState.getmapaHabitaciones().indexOf(agState.getposicion());
        if( habitacionActualIndex != 0 ){
        	agState.setposicion(agState.getmapaHabitaciones().get(habitacionActualIndex - 1));
        	agState.getmapaHabitaciones().get(habitacionActualIndex - 1).setVisitada(true);
        	agState.setenergiaDisponible((int)(agState.getenergiaDisponible() - this.getCost()));
        	return agState;
        }
        */
        
        return null;
        
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoCaperucita agState = ((EstadoCaperucita) ast);

        //Si no es la �ltima habitaci�n, puede moverse a derecha
        /*
        int habitacionActualIndex = agState.getmapaHabitaciones().indexOf(agState.getposicion());
        if( habitacionActualIndex != 0 ){
        	//Modificamos el estado del agente
        	agState.setposicion(agState.getmapaHabitaciones().get(habitacionActualIndex - 1));
        	agState.getmapaHabitaciones().get(habitacionActualIndex - 1).setVisitada(true);
        	agState.setenergiaDisponible((int)(agState.getenergiaDisponible() - this.getCost()));
        	
        	//Modificamos el estado del ambiente
        	environmentState.setposicionAspiradora(agState.getmapaHabitaciones().get(habitacionActualIndex -1));
        	return environmentState;
        }
        */
        
        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(2);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrIzquierda";
    }
}