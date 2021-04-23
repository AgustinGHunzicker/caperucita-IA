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
package frsf.cidisi.faia.solver.situationcalculus;

import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.exceptions.SituationCalculusException;
import frsf.cidisi.faia.solver.Solve;
import java.util.Hashtable;

public class SituationCalculus extends Solve {

    @Override
    public void showSolution() {
        // TODO Auto-generated method stub
    }

    @Override
    public Action solve(Object[] params) throws SituationCalculusException {
        KnowledgeBase kb = (KnowledgeBase) params[0];

        // Query the knowledge base for the best action in the current situation.
        Hashtable[] results =
                kb.query(kb.getBestActionPredicate() + "(X," +
                kb.getSituation() + ")");

        // We look for the first result.
        if (results.length == 0) {
            throw new SituationCalculusException("No solutions returned. Maybe there is an error in the knowledge base.");
        }

        String bestAction = results[0].get("X").toString();

        /* We convert the string 'bestAction' in an Action object */
        return kb.getActionFactory().makeActionFromString(bestAction);
    }
}
