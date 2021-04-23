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

import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public abstract class Action {

    public Action() {
    }

    /**
     * This method updates the real state of the agent and the environment.
     * 
     * @param ast This is the agent's state to be updated.-
     * @param est This is the agent's environment to be updated.-
     */
    public abstract EnvironmentState execute(AgentState ast, EnvironmentState est);

    /**
     * The string representation of the action is used by some components of
     * the framework, like LatexOutput.
     * It's very important to set a correct representation of the actions,
     * particularly when using knowledge based agent. Look at the
     * CalculusAction.toLogicName() method. The result of this one is lower
     * cased, and it's supposed you are using this one in your prolog file
     * (.pl file).
     */
    public abstract String toString();
}
