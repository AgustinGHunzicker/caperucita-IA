package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunction implements IStepCostFunction {

    /**
     * This method calculates the cost of the given NTree node.
     */
    public double calculateCost(NTree node) {
    	
        return node.getAction().getCost();
        
    }
    
}
