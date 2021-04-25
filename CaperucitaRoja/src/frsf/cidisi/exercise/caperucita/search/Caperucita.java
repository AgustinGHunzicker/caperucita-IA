package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.exercise.caperucita.search.actions.IrAbajo;
import frsf.cidisi.exercise.caperucita.search.actions.IrArriba;
import frsf.cidisi.exercise.caperucita.search.actions.IrDerecha;
import frsf.cidisi.exercise.caperucita.search.actions.IrIzquierda;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caperucita extends SearchBasedAgent {

    public Caperucita() {
        ObjetivoCaperucita objetivoCaperucita = new ObjetivoCaperucita();

        EstadoCaperucita estadoCaperucita = new EstadoCaperucita();
        setAgentState(estadoCaperucita);

        Vector<SearchAction> actions = new Vector<>();
        //actions.addElement(new RecolectarDulce());
        actions.addElement(new IrDerecha());
        actions.addElement(new IrIzquierda());
        actions.addElement(new IrAbajo());
        actions.addElement(new IrArriba());

        Problem problem = new Problem(objetivoCaperucita, estadoCaperucita, actions);
        setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        //DepthFirstSearch strategy = new DepthFirstSearch();
        BreathFirstSearch strategy = new BreathFirstSearch();

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.GRAPHICAL_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(Caperucita.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     *
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
