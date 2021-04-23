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

import frsf.cidisi.faia.state.AgentState;

public abstract class SearchBasedAgentState extends AgentState {

    /**
     * We need this method to look for repeated nodes in the same search branch.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * We need to be able to clone an AgentState, because we'll use it
     * in the search process, when we apply the operations on a node.
     */
    public abstract SearchBasedAgentState clone();
}
