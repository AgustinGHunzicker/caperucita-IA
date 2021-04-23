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
package frsf.cidisi.faia.agent.search;

import java.util.Vector;

public class Problem {

    protected GoalTest goalTest;
    protected SearchBasedAgentState agentState;
    protected Vector<SearchAction> actions;

    /**
     * 
     * @param goalTest
     * @param initState
     * @param action
     */
    public Problem(GoalTest goalTest, SearchBasedAgentState initState, Vector<SearchAction> actions) {
        this.goalTest = goalTest;
        this.agentState = initState;
        this.actions = actions;
    }

    public Vector<SearchAction> getActions() {
        return actions;
    }

    public GoalTest getGoalState() {
        return goalTest;
    }

    public SearchBasedAgentState getAgentState() {
        return agentState;
    }

    public void setActions(Vector<SearchAction> actions) {
        this.actions = actions;
    }

    public void setGoalState(GoalTest goalTest) {
        this.goalTest = goalTest;
    }

    public void setAgentState(SearchBasedAgentState agentState) {
        this.agentState = agentState;
    }
}
