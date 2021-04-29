package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.exercise.caperucita.search.actions.IrArriba;
import frsf.cidisi.exercise.caperucita.search.actions.IrAbajo;
import frsf.cidisi.exercise.caperucita.search.actions.IrDerecha;
import frsf.cidisi.exercise.caperucita.search.actions.IrIzquierda;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.Search;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caperucita extends SearchBasedAgent {

    public Caperucita(Ambiente ambiente) {
        ObjetivoCaperucita objetivoCaperucita = new ObjetivoCaperucita();

        EstadoCaperucita estadoCaperucita = new EstadoCaperucita(ambiente);
        setAgentState(estadoCaperucita);

        Vector<SearchAction> actions = new Vector<>();
        actions.addElement(new IrArriba());
        actions.addElement(new IrIzquierda());
        actions.addElement(new IrAbajo());
        actions.addElement(new IrDerecha());

        Problem problem = new Problem(objetivoCaperucita, estadoCaperucita, actions);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        BreathFirstSearch strategy = new BreathFirstSearch();
        //DepthFirstSearch strategy = new DepthFirstSearch();

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
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
     * @param p percepción del agente
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
