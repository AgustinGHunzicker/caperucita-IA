package search;

import enumeration.TipoBusqueda;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainCaperucita {

    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente();
        ambiente.setEstadoInicialAmbiente();
        Caperucita caperucita = new Caperucita(ambiente, TipoBusqueda.INFORMADA);
        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(ambiente, caperucita);
        simulator.start();
    }
}
