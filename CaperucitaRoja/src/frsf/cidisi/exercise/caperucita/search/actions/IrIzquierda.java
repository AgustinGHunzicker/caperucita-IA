package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;

public class IrIzquierda extends SearchAction {

    private Double costo = 0.0;

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        System.out.println(Consola.textoColoreadoPurple("Probando " + this));

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // particular de IrIzquierda
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();
        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0 && !celda.equals(EstadoCelda.LOBO)) {
            int posXCaperucita = posicionCaperucita.x - cantMovimientos;
            int posYCaperucita = posicionCaperucita.y;
            estadoCaperucita.setPosicionCaperucita(new Point(posXCaperucita, posYCaperucita));

            // para actualizar las percepciones
            estadoCaperucita.getEstadoAmbiente().setPosicionCaperucita(new Point(posXCaperucita, posYCaperucita));
            estadoCaperucita.updateState(estadoCaperucita.getAmbiente().getPercept());

            estadoCaperucita.imprimirEscenarioDescubierto();

            return estadoCaperucita;
        }

        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        System.out.println(Consola.textoColoreadoPurple("Usando " + this));

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);


        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // particular de IrIzquierda
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();
        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();


        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0 && !celda.equals(EstadoCelda.LOBO)) {

            int posXCaperucita = posicionCaperucita.x - cantMovimientos;
            int posYCaperucita = posicionCaperucita.y;

            //actualizo el estado de caperucita y el escenario
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            posicionCaperucita = new Point(posXCaperucita, posYCaperucita);
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.CAPERUCITA);

            for (Point posicionFlor : estadoCaperucita.getPosicionFlores()) {
                if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                    estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                    break;
                }
            }

            environmentState.setEscenario(escenario);
            environmentState.setPosicionCaperucita(posicionCaperucita);
            //environmentState.imprimirEscenarioDescubierto();
            return environmentState;
        }


        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return costo;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrIzquierda";
    }
}