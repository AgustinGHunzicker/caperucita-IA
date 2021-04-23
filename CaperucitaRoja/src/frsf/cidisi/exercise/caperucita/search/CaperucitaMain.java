package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class CaperucitaMain {

    public static void main(String[] args) {
        Ambiente environment = new Ambiente();

        Caperucita agent = new Caperucita(environment);

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(environment, agent);

        simulator.start();
    }

}
