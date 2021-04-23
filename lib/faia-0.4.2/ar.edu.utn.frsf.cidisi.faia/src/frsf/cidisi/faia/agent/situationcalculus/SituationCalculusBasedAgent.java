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
package frsf.cidisi.faia.agent.situationcalculus;

import frsf.cidisi.faia.agent.*;

public abstract class SituationCalculusBasedAgent extends GoalBasedAgent {

    public SituationCalculusBasedAgent() {
        super();
    }

    /**
     * This method is executed by the simulator to tell the agent
     * what action was executed in the current situation.
     * @param action
     */
    public abstract void tell(Action action);
}
