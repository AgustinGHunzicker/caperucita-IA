package search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    public double getEstimatedCost(NTree node) {
        EstadoCaperucita agState = (EstadoCaperucita) node.getAgentState();

        return 3 - agState.getDulcesJuntados().size();
    }
    
}
