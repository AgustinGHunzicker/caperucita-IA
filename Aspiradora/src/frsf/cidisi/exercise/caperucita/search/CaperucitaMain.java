package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class CaperucitaMain {

    public static void main(String[] args) throws PrologConnectorException {
        Ambiente environment = new Ambiente();
        
        Caperucita agent = new Caperucita();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
