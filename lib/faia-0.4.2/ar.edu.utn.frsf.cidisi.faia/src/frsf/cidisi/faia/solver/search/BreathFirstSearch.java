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
package frsf.cidisi.faia.solver.search;

import java.util.Arrays;
import java.util.Vector;

/**
 * @author Jorge M. Roa
 * @version 1.0
 * @created 08-Mar-2007 13:16:03
 */
public class BreathFirstSearch extends Strategy {

    public BreathFirstSearch() {
    }

    public void addNodesToExpand(Vector<NTree> nodes) {
        //Add the nodes at the bottom of the list of nodes to expand
        for (NTree nt : nodes) {
            nt.setCost(nt.getParent().getCost() + 1);
        }
        nodesToExpand.addAll(nodes);
    }

    public void addNodeToExpand(NTree node) {
        //Add the node at the top of the list of nodes to expand
        node.setCost(node.getParent().getCost() + 1);
        nodesToExpand.add(node);
    }

    @Override
    public String getStrategyName() {
        return "Breath First (Primero en amplitud)";
    }
}
