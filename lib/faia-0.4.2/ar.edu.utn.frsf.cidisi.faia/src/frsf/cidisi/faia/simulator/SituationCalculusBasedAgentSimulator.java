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
package frsf.cidisi.faia.simulator;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.agent.NoAction;
import frsf.cidisi.faia.state.AgentState;
import java.util.Arrays;
import java.util.Vector;

public class SituationCalculusBasedAgentSimulator extends GoalBasedAgentSimulator {

    public SituationCalculusBasedAgentSimulator(Environment environment, Vector<Agent> agents) {
        super(environment, agents);
    }

    public SituationCalculusBasedAgentSimulator(Environment environment, Agent agent) {
        this(environment, new Vector<Agent>(Arrays.asList(agent)));
    }

    @Override
    public String getSimulatorName() {
        return "Situation Calculus Based Simulator";
    }

    @Override
    public boolean agentSucceeded(Action actionReturned) {
        if (actionReturned instanceof NoAction) {
            return true;
        }

        return false;
    }

    @Override
    public boolean agentFailed(AgentState state) {
        return this.environment.agentFailed(state);
    }

    @Override
    public void actionReturned(Agent agent, Action action) {
        this.updateState(action);

        SituationCalculusBasedAgent scAgent =
                (SituationCalculusBasedAgent) agent;

        scAgent.tell(action);
    }
}
