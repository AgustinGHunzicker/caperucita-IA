/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa,
 * Luis Ignacio Larrateguy y Milton Pividori.
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
package frsf.cidisi.faia.agent.situationcalculus;

import java.util.Hashtable;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.PrologConnector;
import frsf.cidisi.faia.state.AgentState;

public abstract class KnowledgeBase extends AgentState {

    /**
     * Prolog connector
     */
    protected PrologConnector prologConnector;

    protected String lastPerception;

    public KnowledgeBase(String knowledgeBaseFile) throws PrologConnectorException {
        this.prologConnector = new PrologConnector(knowledgeBaseFile);
        this.lastPerception = "";

        // Start with situation 0
        this.addKnowledge(this.getSituationPredicate() + "(0)");
    }

    /**
     * Returns the actual situation of the Knowledge Base
     * @return
     */
    public int getSituation() {
        String query = this.getSituationPredicate() + "(S)";

        Hashtable[] pos = this.query(query);

        int s = Integer.parseInt(pos[0].get("S").toString());

        return s;
    }

    public void advanceToNextSituation() {
        int situation = this.getSituation();

        // Remove current situation predicate
        this.removeKnowledge(this.getSituationPredicate() +
                "(" + situation + ")");

        // Advance to next situation and add the new situation predicate
        situation = situation + 1;
        this.addKnowledge(this.getSituationPredicate() +
                "(" + situation + ")");
    }

    public void executeSuccessorStateAxioms() {
        int nextSituation = this.getSituation() + 1;
        this.prologConnector.executeNonQuery("findall(_,est(" + nextSituation + "),_)");
    }

    public void tell(SituationCalculusPerception perception) {
        if (!this.lastPerception.equals(""))
            this.prologConnector.executeNonQuery("retract(" +
                    this.lastPerception + ")");

        this.addKnowledge(perception.toString());
        this.lastPerception = perception.toString();
    }

    public void tell(Action actionObject) {
        String action = actionObject.toString();

        if (action == null)
            return;

        this.addKnowledge(this.getExecutedActionPredicate() +
                "(" + action + "," + this.getSituation() + ")");
        
        // Execute successors state axioms
        this.executeSuccessorStateAxioms();

        // Advance to the next situation
        this.advanceToNextSituation();
    }

    public void addKnowledge(String predicate) {
        this.prologConnector.addPredicate(predicate);
    }

    public void removeKnowledge(String predicate) {
        this.prologConnector.removePredicate(predicate);
    }

    public Hashtable[] query(String query) {
        return this.prologConnector.query(query);
    }

    public boolean queryHasSolution(String query) {
        return this.prologConnector.queryHasSolution(query);
    }

    public abstract ActionFactory getActionFactory();

    public abstract String getSituationPredicate();

    public abstract String getBestActionPredicate();

    public abstract String getExecutedActionPredicate();
}
