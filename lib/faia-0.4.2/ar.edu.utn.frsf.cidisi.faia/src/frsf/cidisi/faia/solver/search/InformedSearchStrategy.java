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

import java.util.Vector;

/**
 * @author Jorge M. Roa
 * @version 1.0
 * @created 09-Mar-2007 14:13:32
 */
public abstract class InformedSearchStrategy extends Strategy {

    private IStepCostFunction g;
    private IEstimatedCostFunction h;

    public InformedSearchStrategy(IStepCostFunction g, IEstimatedCostFunction h) {
        this.g = g;
        this.h = h;
    }

    public InformedSearchStrategy(IStepCostFunction g) {
        this.g = g;
        this.h = DummyEstimatedCostFunction.getInstance();
    }

    public InformedSearchStrategy(IEstimatedCostFunction h) {
        this.g = DummyStepCostFunction.getInstance();
        this.h = h;
    }

    @Override
    public void addNodesToExpand(Vector<NTree> nodes) {
        //Add the nodes at the top of the list of nodes to expand
        for (NTree nt : nodes) {
            nt.setCost(nt.getParent().getCost() + g.calculateCost(nt) + h.getEstimatedCost(nt));
        }
        nodesToExpand.addAll(nodes);
    }

    @Override
    public void addNodeToExpand(NTree node) {
        //Add the node at the top of the list of nodes to expand
        node.setCost(node.getParent().getCost() + g.calculateCost(node) + h.getEstimatedCost(node));
        nodesToExpand.add(node);
    }

    public IStepCostFunction getStepCostFunction() {
        return g;
    }

    public IEstimatedCostFunction getEstimatedCostFunction() {
        return h;
    }
}
