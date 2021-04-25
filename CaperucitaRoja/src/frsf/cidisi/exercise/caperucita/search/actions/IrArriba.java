package frsf.cidisi.exercise.caperucita.search.actions;

import enumeration.TipoLado;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrArriba extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        return Moverse.modelSearchBasedAgentState(s, TipoLado.ARRIBA);
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState agentState, EnvironmentState environmentState) {
        return Moverse.modelEnvironmentState(agentState, environmentState, TipoLado.ARRIBA);
    }



    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return 2.0;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrDerecha";
    }

}