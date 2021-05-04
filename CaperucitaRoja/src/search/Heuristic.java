package search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

import java.awt.*;
import java.util.HashSet;

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
        HashSet<Point> flores = agState.getEscenario().getFlores();
        int x = 100;
        int y = 100;
        int x1 = agState.getPosicionCaperucita().x;
        int y1 = agState.getPosicionCaperucita().y;

        for (Point i : flores) {
            x = Math.min(x, i.x);
            y = Math.min(y, i.y);
        }

        return Math.abs(x - x1) + Math.abs(y - y1);
    }

}
