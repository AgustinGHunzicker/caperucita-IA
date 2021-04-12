package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import frsf.cidisi.exercise.caperucita.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class RecolectarDulce extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita agState = (EstadoCaperucita) s;
        
        //Si la posicion actual se encuentra entre las habitaciones sucias, se puede limpiar
        if(agState.gethabitacionesSucias().contains(agState.getposicion())){
        	//Sacamos la habitaci�n en la cual nos encontramos de la lista de habit. sucias
        	agState.gethabitacionesSucias().remove(agState.getposicion());
        	
        	return agState;
        }
        	
        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        EstadoCaperucita agState = ((EstadoCaperucita) ast);

      //Si la posicion actual se encuentra entre las habitaciones sucias, se puede limpiar
        if(agState.gethabitacionesSucias().contains(agState.getposicion())){
        	
        	//Removemos la habitacion que ha sido limpiada de la lista de habitaciones sucias
        	Escenario hab=null;
        	for(Escenario h : environmentState.gethabitacionesSucias())
        		if(h.getNombre().equals(agState.getposicion().getNombre()))
        			hab = h;
        	
        	environmentState.gethabitacionesSucias().remove(hab);
        	
        	return environmentState;
        }
        	
        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(1);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "Limpiar";
    }
}