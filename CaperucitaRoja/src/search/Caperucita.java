package search;

import enumeration.TipoBusqueda;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import search.actions.IrAbajo;
import search.actions.IrArriba;
import search.actions.IrDerecha;
import search.actions.IrIzquierda;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caperucita extends SearchBasedAgent {

    private final EstadoCaperucita estadoCaperucita;
    private final TipoBusqueda tipoBusqueda;

    public Caperucita(Ambiente ambiente, TipoBusqueda tipoBusqueda) {
        ObjetivoCaperucita objetivoCaperucita = new ObjetivoCaperucita();
        this.tipoBusqueda = tipoBusqueda;
        estadoCaperucita = new EstadoCaperucita();
        estadoCaperucita.setEscenario(ambiente.getEnvironmentState().getEscenario());
        estadoCaperucita.setFlores(ambiente.getEnvironmentState().getEscenario().getFlores());
        setAgentState(estadoCaperucita);

        Vector<SearchAction> actions = new Vector<>();
        actions.addElement(new IrDerecha());
        actions.addElement(new IrAbajo());
        actions.addElement(new IrIzquierda());
        actions.addElement(new IrArriba());

        Problem problem = new Problem(objetivoCaperucita, estadoCaperucita, actions);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {
        Search searchSolver;
        // Create the search strategy
        switch (tipoBusqueda){
            case ANCHURA:
                BreathFirstSearch strategy = new BreathFirstSearch();
                searchSolver  = new Search(strategy);
                break;
            case INFORMADA:
                searchSolver  = new Search(new AStarSearch(new CostFunction(), new Heuristic()));
                break;
            case COSTO_UNIFORME:
                searchSolver  = new Search(new UniformCostSearch(new CostFunction()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipoBusqueda);
        }

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

    @Override
    public String toString() {
        return estadoCaperucita.toString();
    }
}
