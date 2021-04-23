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
package frsf.cidisi.faia.agent;

import frsf.cidisi.faia.solver.Solve;
import frsf.cidisi.faia.state.AgentState;

public abstract class GoalBasedAgent extends Agent {

    protected Solve solver;
    protected AgentState state;

    public GoalBasedAgent() {
    }

    /**
     * This method must be overrode by the agent to receive perceptions
     * from the simulator.
     * @param p
     */
    public abstract void see(Perception p);

    public Solve getSolver() {
        return solver;
    }

    protected void setSolver(Solve solver) {
        this.solver = solver;
    }

    public AgentState getAgentState() {
        return state;
    }

    protected void setAgentState(AgentState agentState) {
        this.state = agentState;
    }
}
