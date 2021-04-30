package search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import search.EstadoAmbiente;
import search.EstadoCaperucita;

import java.awt.*;

public class IrArriba extends SearchAction {

    //private Double costo = 2.0;

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;


        Point posInicialCap = estadoCaperucita.getPosicionCaperucita();
        // ARRIBA THINGS
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasArriba();
        int cantMovimientos = estadoCaperucita.getCantMovimientosArriba();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0) {
            Consola.printExecution1(this, estadoCaperucita.getPosicionCaperucita());


            if (celda.equals(EstadoCelda.LOBO)) {
                //TODO hacer lo del lobo
                return null;
            }

            Point posFinalCap = new Point(posInicialCap.x, posInicialCap.y - cantMovimientos);
            estadoCaperucita.setPosicionCaperucita(new Point(posFinalCap.x, posFinalCap.y));

            //si hay un dulce en esa dirección
            // en el caso que también hay flores en esa dirección, solo lo junta si esta antes de las flores
            for (Point dulce : estadoCaperucita.getPosicionesDulces()) {
                //Si está en la misma columna y está dentro de los posibles movimientos hacia arriba
                if (posFinalCap.x == dulce.x && (posFinalCap.y <= dulce.y) && (dulce.y <= posInicialCap.y)) {
                    estadoCaperucita.getDulcesJuntados().add(dulce);
                    estadoCaperucita.getEstadoAmbiente().getEscenario().setPosicionCelda(dulce.x, dulce.y, EstadoCelda.VACIA);
                }
            }
            estadoCaperucita.getEstadoAmbiente().getPosicionesDulces().removeAll(estadoCaperucita.getDulcesJuntados());
            estadoCaperucita.getPosicionesDulces().removeAll(estadoCaperucita.getDulcesJuntados());
            estadoCaperucita.setDulcesJuntados(estadoCaperucita.getDulcesJuntados());

            //Se debe verificar si no esta sobre las flores,
            // puesto que la bandera FLORES puede quedar anulada por los dulces
            for (Point posicionFlor : estadoCaperucita.getPosicionFlores()) {
                if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                    estadoCaperucita.getPosicionFlores().add(posicionFlor);
                    estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                    break;
                }
            }

            estadoCaperucita.getEstadoAmbiente().setPosicionCaperucita(posFinalCap);
            estadoCaperucita.updateState(estadoCaperucita.getAmbiente().getPercept());
            return estadoCaperucita;
        }

        return null;


    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        Consola.printExecution2(this);

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);

        Point posInicialCap = estadoCaperucita.getPosicionCaperucita();

        // particular de IrArriba
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasArriba();
        int cantMovimientos = estadoCaperucita.getCantMovimientosArriba();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0) {

            if (celda.equals(EstadoCelda.LOBO)) {
                //TODO hacer lo del lobo
                return null;
            }

            Point posFinalCap = new Point(posInicialCap.x, posInicialCap.y - cantMovimientos);
            estadoCaperucita.setPosicionCaperucita(posFinalCap);

            for (Point dulce : estadoCaperucita.getPosicionesDulces()) {
                escenario.setPosicionCelda(dulce.x, dulce.y, EstadoCelda.DULCE);
                //Si está en la misma columna y está dentro de los posibles movimientos hacia arriba
                if (posFinalCap.x == dulce.x && (posInicialCap.y <= dulce.y) && (dulce.y <= posFinalCap.y)) {
                    estadoCaperucita.getDulcesJuntados().add(dulce);
                    escenario.setPosicionCelda(dulce.x, dulce.y, EstadoCelda.VACIA);
                    estadoCaperucita.getEstadoAmbiente().getEscenario().setPosicionCelda(dulce.x, dulce.y, EstadoCelda.VACIA);
                }
            }

            estadoCaperucita.getEstadoAmbiente().getPosicionesDulces().removeAll(estadoCaperucita.getDulcesJuntados());
            environmentState.getPosicionesDulces().removeAll(estadoCaperucita.getDulcesJuntados());
            estadoCaperucita.getPosicionesDulces().removeAll(estadoCaperucita.getDulcesJuntados());
            estadoCaperucita.setDulcesJuntados(estadoCaperucita.getDulcesJuntados());

            //Se debe verificar si no esta sobre las flores,
            // puesto que la bandera FLORES puede quedar anulada por los dulces
            for (Point posicionFlor : environmentState.getPosicionesFlores()) {
                escenario.setPosicionCelda(posicionFlor.x, posicionFlor.y, EstadoCelda.FLORES);
                if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                    estadoCaperucita.getPosicionFlores().add(posicionFlor);
                    estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                    break;
                }
            }

            escenario.setPosicionCelda(posInicialCap.x, posInicialCap.y, EstadoCelda.VACIA);
            escenario.setPosicionCelda(posInicialCap.x, posInicialCap.y, EstadoCelda.CAPERUCITA);

            environmentState.setEscenario(escenario);
            environmentState.setPosicionCaperucita(posFinalCap);
            estadoCaperucita.updateState(estadoCaperucita.getAmbiente().getPercept());
            return environmentState;
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return 2.0;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrArriba";
    }

}