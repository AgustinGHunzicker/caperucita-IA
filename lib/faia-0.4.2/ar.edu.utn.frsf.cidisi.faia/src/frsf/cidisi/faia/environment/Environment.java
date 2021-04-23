/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.environment;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.cidisi.faia.state.AgentState;

public abstract class Environment {

    protected EnvironmentState environmentState;

    /**
     * This method updates the state of the simulator, that is the real world.
     * @param ast The state of the Agent
     * @param action
     */
    public void updateState(AgentState ast, Action action) {
        environmentState = (EnvironmentState) action.execute(ast, environmentState);
    }

    public void setEnvironmentState(EnvironmentState state) {
        environmentState = state;
    }

    public EnvironmentState getEnvironmentState() {
        return environmentState;
    }

    /**
     * This method will return a perception made by the subclass of Environment
     * @param agent
     * @return
     */
    public abstract Perception getPercept();

    /**
     * This method is called by the simulator to know if the agent has failed.
     * If so, the simulation will finish. Users can override it.
     * @param actionReturned
     * @return
     */
    public boolean agentFailed(AgentState state) {
        return false;
    }

    /**
     * Subclasses of Environment can override this method to close any
     * resource when the simulation finished.
     */
    public void close() {
    }
}

