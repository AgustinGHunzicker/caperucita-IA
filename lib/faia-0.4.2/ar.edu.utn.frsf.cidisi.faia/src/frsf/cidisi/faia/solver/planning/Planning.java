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
package frsf.cidisi.faia.solver.planning;

import frsf.cidisi.faia.agent.planning.PlanningBasedAgentState;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.planning.PlanningBasedAgent;
import frsf.cidisi.faia.solver.Solve;

public class Planning extends Solve {

    @Override
    public void showSolution() {
        // TODO Auto-generated method stub
    }

    @Override
    public Action solve(Object[] params) throws Exception {
        PlanningBasedAgent agent = (PlanningBasedAgent) params[0];
        PlanningBasedAgentState agentState = (PlanningBasedAgentState) agent.getAgentState();

        String bestAction = agentState.getBestActionAction();

//		// Take only the action name, not the arguments, ie if we get
//		// 'discover(3)' as the best action, take only 'discover'.
//		String[] aver = bestAction.split("\\(");
//		bestAction = aver[0];

        return agentState.getActionFactory().makeActionFromString(bestAction);
    }
}
