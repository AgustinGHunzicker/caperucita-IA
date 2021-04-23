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
 * @created 08-Mar-2007 18:21:43
 */
public class UniformCostSearch extends Strategy {

    private IStepCostFunction stepCostFunction;

    public UniformCostSearch(IStepCostFunction stepCostFunction) {
        this.stepCostFunction = stepCostFunction;
    }

    public void addNodesToExpand(Vector<NTree> nodes) {
        //Add the nodes at the bottom of the list of nodes to expand
        for (NTree nt : nodes) {
            nt.setCost(nt.getParent().getCost() + stepCostFunction.calculateCost(nt));
        }
        nodesToExpand.addAll(nodes);
    }

    public void addNodeToExpand(NTree node) {
        //Add the node at the top of the list of nodes to expand
        node.setCost(node.getParent().getCost() + stepCostFunction.calculateCost(node));
        nodesToExpand.add(node);
    }

    public IStepCostFunction getStepCostFunction() {
        return stepCostFunction;
    }

    public void setStepCostFunction(IStepCostFunction stepCostFunction) {
        this.stepCostFunction = stepCostFunction;
    }

    @Override
    public String getStrategyName() {
        return "Uniform cost (Costo uniforme)";
    }
}
