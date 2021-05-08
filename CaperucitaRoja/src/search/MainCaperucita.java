package search;

import enumeration.TipoBusqueda;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class MainCaperucita {

    public static void main(String[] args) {
        //ConsoleDebug.get().setModeDebug();
        //ConsoleDebug.get().setPruebaEscenario(true);
        //ConsoleDebug.get().showLogs();
        //ConsoleDebug.get().staticWolf();

        Ambiente ambiente = new Ambiente();
        ambiente.setEstadoInicialAmbiente();

        Caperucita caperucita = new Caperucita(ambiente, TipoBusqueda.INFORMADA);

        SearchBasedAgentSimulator simulator = new SearchBasedAgentSimulator(ambiente, caperucita);
        simulator.start();
    }
}
