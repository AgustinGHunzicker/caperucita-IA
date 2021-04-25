package frsf.cidisi.exercise.caperucita.search;

import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainCaperucita {

    public static void main(String[] args) {
        Caperucita caperucita = new Caperucita();

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(((EstadoCaperucita)caperucita.getAgentState()).getAmbienteActual(), caperucita);

        simulator.start();
    }

}
