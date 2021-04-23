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

import java.util.Vector;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

/**
 * @created 07-Mar-2007 19:34:42
 * @author Jorge M. Roa
 * @version 1.0
 */
public abstract class Simulator {

    protected Vector<Agent> agents;
    protected Environment environment;

    public Simulator() {
    }

    public Simulator(Environment environment, Vector<Agent> agents) {
        this.environment = environment;
        this.agents = agents;
    }

    public void addAgent(Agent agent) {
        this.getAgents().addElement(agent);
    }

    public Vector<Agent> getAgents() {
        return agents;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public Perception getPercept() {
        return this.getEnvironment().getPercept();
    }

    public void setEnvironment(Environment evm) {
    }

    public abstract void start();
}
