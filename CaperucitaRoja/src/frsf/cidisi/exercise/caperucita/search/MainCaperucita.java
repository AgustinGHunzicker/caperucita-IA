package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainCaperucita {

    public static void main(String[] args) {
        Caperucita caperucita = new Caperucita();
        Ambiente ambiente = new Ambiente();
        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(ambiente, caperucita);
        simulator.start();
    }
}
