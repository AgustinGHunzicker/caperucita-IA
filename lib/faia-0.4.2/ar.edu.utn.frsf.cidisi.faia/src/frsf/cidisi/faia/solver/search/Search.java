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

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.cidisi.faia.solver.Solve;
import frsf.cidisi.faia.util.GraphvizTree;
import frsf.cidisi.faia.util.LatexOutput;
import frsf.cidisi.faia.util.TreeMLWriter;
import frsf.cidisi.faia.util.XmlTree;

import java.util.Vector;

public class Search extends Solve {

    public static final int WHITHOUT_TREE = 0;
    public static final int XML_TREE = 1;
    public static final int PDF_TREE = 2;
    public static final int GRAPHICAL_TREE = 3;
    public static final int GRAPHVIZ_TREE = 4;
    public static final int EFAIA_TREE = 5;
    private NTree tree;
    private NTree goalNode;
    private Strategy searchStrategy;
    private int visibleTree = Search.WHITHOUT_TREE;


    /*	public Search(){
    
    }
     */
    /**
     * 
     * @param strategy
     * @param p
     */
    public Search(Strategy strategy) {
        searchStrategy = strategy;
    }

    public Strategy getStrategy() {
        return searchStrategy;
    }

    /**
     * 
     * @param s
     */
    public void setStrategy(Strategy s) {
        this.searchStrategy = s;
    }

    /**
     * 
     * @param problem
     */
    @Override
    public SearchAction solve(Object[] params) {

        Problem problem = (Problem) params[0];

        Vector<SearchAction> actionList = problem.getActions();
        SearchBasedAgentState agentState = problem.getAgentState();//.clone();
        GoalTest goalTest = problem.getGoalState();

        int nodeIdx = 1;

        tree = new NTree();

        tree.setAgentState(agentState);

        //System.out.println("Search.java - solve" + agentState);
        searchStrategy.initNodesToExpandList(tree);

        boolean goal = false;

        // This iteration will occur while nodesToExpand have nodes and the actual node is not a goal node.-
        while (searchStrategy.getNodesToExpandSize() > 0 & !goal) {
            // This is the first node of the node's queue that will be expanded
            NTree firstNode = (NTree) searchStrategy.getNode();

            //System.out.println("Profundidad: " + firstNode.getDeep());

            // If the actual node is a goal node then the search must finish.-
            if (goalTest.isGoalState(firstNode.getAgentState())) {
                goal = true;
                goalNode = firstNode;
            } else {	// If the actual node is not a goal node then it must be expanded.-

                // Every item in the action list represents a possible son for the actual node.-
                for (int i = 0; i < actionList.size(); i++) {
                    // The state of the selected node must be cloned to assure consistence.-
                    SearchBasedAgentState ast = firstNode.getAgentState().clone();
                    // This is the action that can generate a new node.-
                    SearchAction action = actionList.elementAt(i);
                    ast = action.execute(ast);

                    // TODO: HAY QUE VER SI CONVIENE QUE CUANDO EL OPERADOR NO PUEDA SER 
                    // EJECUTADO DEVUELVA UN OBJETO EN LUGAR DE NULL.
                    if (ast != null) {	// If the action was correctly executed.-
                       // System.out.println("Search.java - solve - if (ast != null) {");
                        NTree n = new NTree(firstNode, actionList.elementAt(i), ast, nodeIdx);
                        // If the node is not repeated in his search's tree branch
                        // then it can be added to the end of the branch.-
                        if (!existsNode(n, n.getParent())) {
                           // System.out.println("Search.java - solve - if (!existsNode(n, n.getParent())) {");
                            firstNode.addSon(n);
                            nodeIdx++;
                            //System.out.println("Nodo nro: " + nodeIdx);
                        }
                    }
                }
                // The nodes are added to the queue of "nodes to expand",
                // so they can be expanded in the next cycles.-
                searchStrategy.addNodesToExpand(firstNode.getSons());
            }
           // System.out.println("Search.java - solve ---"+ searchStrategy.getNodesToExpandSize());
        }

        if (goal && !getBestPath().isEmpty()) {
            // This variable store the branch's path where the node belongs.-
            //System.out.println("Search.java - solve - Encontro un camino a las flores");
            Vector<NTree> path = getBestPath();

            // The first node of the branch has the action that must be executed by the agent.-
            return path.elementAt(0).getAction();
        }

        return null;

    }

    private boolean existsNode(NTree node, NTree parent) {
        NTree p = (NTree) parent;//.clone();

        // This is an iteration through the node's parent (and ancestors) looking for a repeated node 
        // in the same branch of the Search Tree.-  
        while (p != null) {
            // If node already exists in the actual branch then the function return true.-
            if (node.equals(p)) {
                return true;
            }
            p = (NTree) p.getParent();
            //if (p!=null)
            //	p = (NTree)p.clone();
        }

        // At this point it's sure that the node does not exists in the branch of the Search Tree.-  
        return false;
    }

    private Vector<NTree> getBestPath() {
        Vector<NTree> path = new Vector<NTree>();

        NTree node = (NTree) goalNode;//.clone();

        // This iteration will occur until the branch's top is reached.
        // The branch's top is not the root node of the tree. This is because there is no action 
        // associated with the root node. So, the branch's top is a son of the root node.-
        while (node.getParent() != null) {
            // I insert every node at the first position, therefore I get the path from rootNode to lastNode
            path.insertElementAt(node, 0);
            node = (NTree) node.getParent(); //.clone();
        }

        return path;
    }

    public void showTree() {
        switch (visibleTree) {
            case Search.WHITHOUT_TREE:
                break;
            case Search.XML_TREE:
                XmlTree.printFile(tree);
                /*			String arbol = this.toXml();
                System.out.println("Arbol:");
                System.out.println(arbol);
                 */
                break;
            case Search.PDF_TREE:
                LatexOutput.getInstance().printFile(tree, this.searchStrategy.getStrategyName());
                break;
            case Search.GRAPHICAL_TREE:
                break;
            case Search.GRAPHVIZ_TREE:
                GraphvizTree.printFile(tree);
                break;
            case Search.EFAIA_TREE:
            	TreeMLWriter.printFile(tree);
                break;
        }
    }

    public NTree getTree() {
        return tree;
    }

    private String toXml() {
        return tree.toXml();
    }

    public int getVisibleTree() {
        return visibleTree;
    }

    public void setVisibleTree(int visibleTree) {
        // Remove all objects subscribed to simulator events
//        SimulatorEventNotifier.CleanEventHandlers();

        if (visibleTree == Search.PDF_TREE) {
            SimulatorEventNotifier.SubscribeEventHandler(EventType.SimulationFinished,
                    LatexOutput.getInstance());
        }
        this.visibleTree = visibleTree;
    }

    @Override
    public void showSolution() {
        this.showTree();
    }

    public String getPath() {
        return this.getBestPath().toString();
    }
}
