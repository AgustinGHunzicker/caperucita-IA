package search;

import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainCaperucita {

    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente();
        ambiente.setEstadoInicialAmbiente();
        Caperucita caperucita = new Caperucita(ambiente);
        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(ambiente, caperucita);
        simulator.start();
    }
}
