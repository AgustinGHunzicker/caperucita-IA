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

import frsf.cidisi.faia.agent.Action;

public abstract class SearchAction extends Action {

    /**
     * This method is used internally by the framework when the "Search Process"
     * is being executed. It updates the node's state on every node of the
     * search tree. Therefore, it doesn't updates the real agent's state, it
     * just updates the state of the agent on every node of the search tree.
     *
     * @param s
     *            This is the state of the agent to be updated on search tree's
     *            node.
     */
    public abstract SearchBasedAgentState execute(SearchBasedAgentState s);

    /**
     * Depending on the strategy used by the agent, this method can be overrode
     * by the agent's actions to return its cost.
     */
    public abstract Double getCost();
}
