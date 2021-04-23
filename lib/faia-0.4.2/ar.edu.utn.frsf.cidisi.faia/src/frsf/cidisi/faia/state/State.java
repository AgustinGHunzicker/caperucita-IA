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
package frsf.cidisi.faia.state;

import frsf.cidisi.faia.state.datastructure.DataStructure;

public abstract class State implements Cloneable {

    protected DataStructure dataStructure;

    public State() {
    }

    /**
     * This method is used in two places:
     *   1. To set the initial state (the real one) of the world, seen
     *      by the simulator.
     *   2. To set the initial state of the agent.
     */
    public abstract void initState();

    /**
     * This method is used to print the state in the console.
     */
    @Override
    public abstract String toString();
}
