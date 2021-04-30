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

import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.util.Vector;

/**
 * @author Jorge M. Roa
 * @version 1.0
 * @created 08-Mar-2007 13:16:04
 */
public class NTree implements Cloneable, Comparable<NTree> {

    protected int deep;
    protected double cost;
    protected SearchAction action;
    protected NTree parent;
    protected Vector<NTree> sons;
    protected SearchBasedAgentState agentState;
    protected int executionOrder;

    public NTree() {
        this.deep = 0;
        this.parent = null;
        this.sons = new Vector<NTree>();
        this.agentState = null;
        this.executionOrder = 0;
    }

    public NTree(NTree firstNode, SearchAction action, SearchBasedAgentState ast, int order) {
        this.deep = firstNode.getDeep() + 1;
        this.parent = firstNode;
        this.sons = new Vector<NTree>();
        this.agentState = ast;
        this.action = action;
        this.executionOrder = order;
    }

    /**
     * 
     * @param son
     */
    public void addSon(NTree son) {
        this.getSons().addElement(son);
    }

    public Object clone() {

        NTree node = new NTree();

        SearchBasedAgentState agSt = agentState.clone();
        /*		if (parent!=null)
        node.setParent((NTree)parent.clone());*/
        node.setParent(parent);
        node.setAction(action);
        node.setAgentState(agSt);
        node.setExecutionOrder(executionOrder);

        // TODO: Ac� hay que clonar a los hijos tambi�n!!???.-

        /*		node.setSons((Vector<NTree>)sons.clone());*/
        node.setSons(sons);

        return node;
    }

    public void setExecutionOrder(int executionOrder) {
		this.executionOrder = executionOrder;
	}

	public SearchAction getAction() {
        return action;
    }

    public double getCost() {
        return cost;
    }

    public int getDeep() {
        return deep;
    }

    public NTree getParent() {
        return parent;
    }

    public Vector<NTree> getSons() {
        return sons;
    }

    public Vector<NTree> getSonsTotal() {
        Vector<NTree> temp = new Vector<NTree>();

        // Agrego primero mis hijos
        temp.addAll(this.sons);

        // Agrego después los hijos de mis hijos recursivamente
        for (int i = 0; i < this.getSons().size(); i++) {
            temp.addAll(this.getSons().elementAt(i).getSonsTotal());
        }
        return temp;
    }

    public SearchBasedAgentState getAgentState() {
        return agentState;
    }

    /**
     * 
     * @param action
     */
    public void setAction(SearchAction action) {
        this.action = action;
    }

    /**
     * 
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * 
     * @param deep
     */
    public void setDeep(int deep) {
        this.deep = deep;
    }

    /**
     * 
     * @param father
     */
    public void setParent(NTree parent) {
        this.parent = parent;
    }

    /**
     * 
     * @param sons
     */
    public void setSons(Vector<NTree> sons) {
        this.sons = sons;
    }

    /**
     * 
     * @param state
     */
    public void setAgentState(SearchBasedAgentState state) {
        this.agentState = state;
    }

    public SearchAction getActionFromBranchsTop() {
        return action;
    }

    public String toString() {
        String eo = "Id=\"" + executionOrder + "\" ";
        String ac = "Action=\"" + action + "\" ";
//TODO: FALTA VER COMO HACEMOS CUANDO HAY FUNCIONES DE COSTO O HEURISTICAS.- 
//		String hf = "Heu: " + getHeuristicFunction() + " ";
//		String cf = "Cst: " + getCostFunction() + " ";

        return eo + ac;
    }

    public String toGraphviz() {
        String str = "";
        str = "nodo" + this.executionOrder +
                "[label=\"{EO: " + this.executionOrder + "|" +
                "cost: " + this.cost + "|" +
                "A: " + this.getAction();

        if (this.getParent() != null) {
            if (this.getParent().getParent() != null) {
                str += "|" + this.getParent().getAgentState().toString();//.replace(",", "").replace("[", "").replace("]", "").replace(" ", "\\n")
            }
        }
        str += "}\"]";
        str = str + "\n";
        for (int i = 0; i < getSons().size(); i++) {
            str = str + sons.elementAt(i).toGraphviz();
            str += "nodo" + this.executionOrder + " -> " +
                    "nodo" + sons.elementAt(i).executionOrder + ";\n";
        }
        str = str + "\n";
        return str;
    }

    public int getExecutionOrder() {
		return executionOrder;
	}

	public String toXml() {

        String str = "";

        str = "<Nodo" + action;
        str = str + ">";
//		str = str + "\n";
        str = str + toString();
        str = str + agentState.toString();
        for (int i = 0; i < getSons().size(); i++) {
            str = str + sons.elementAt(i).toXml();
        }
        str = str + "</Nodo" + action + ">";
//		str = str + "\n";

        return str;
    }
//	public String toLatex() {
//		StringBuffer str = new StringBuffer();
//		
//	    // Clase del documento y opciones generales
//	    str.append("\\documentclass[a0,landscale]{a0poster}\n");
//	   
//	    // Paquetes utilizados
//	    str.append("\\usepackage{mathptmx}\n");
//	    str.append("\\usepackage[scaled=.90]{helvet}\n");
//	    str.append("\\usepackage{courier}\n");
//	    str.append("\\usepackage{qtree}\n");
//	    str.append("\\usepackage{nodo}\n");
//	    str.append("\\usepackage[spanish]{babel}\n");
//	    str.append("\\usepackage[utf8]{inputenc}\n");
//	   
//	    str.append("\\title{Arbol de ejecucion - Estrategia: " +
//	        "NO SETEADA" + "}\n");
//	    str.append("\\author{}\n");
//	    str.append("\\begin{document}\n");
//	    str.append("\\maketitle\n");
//	}

    public String toQtree() {

        StringBuffer resultado = new StringBuffer();

        //resultado.append("\\begin{figure}[!h]\n");
        resultado.append("[." + this.toLatex() + " ");
        //resultado.append("\\Tree [." + this.toLatex() + " ");

        for (int i = 0; i < getSons().size(); i++) {
            resultado.append(sons.elementAt(i).toLatex() + " ");
        }
//		for (NTree hijo : this.sons) {
//			resultado.append(hijo.toLatex() + " ");
//		}

        resultado.append("]");
        //resultado.append("\\end{figure}\n");

        return resultado.toString();
    }

    private String toLatex() {
        String resultado;

        resultado = "\\nodo" + "{" + this.executionOrder + "}" + "{" + this.cost + "}";

        if (this.action != null) {
            resultado += "{" + this.action.toString() + "}";
        } else {
            resultado += "{-}";
        }
        return resultado;
    }

    @Override
    public boolean equals(Object obj) {
        return agentState.equals(((NTree) obj).getAgentState());
    }

    @Override
    public int compareTo(NTree o) {
        if (this.cost == o.cost) {
        	// If both nodes have the same cost then it is necessary to check the order
        	// in which those nodes have been created.-
        	if (this.executionOrder == o.executionOrder) {
        		return 0;
            } else if (this.executionOrder < o.executionOrder) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.cost < o.cost) {
            return -1;
        } else {
            return 1;
        }
    }
}
