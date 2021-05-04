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

public class IrDerecha extends SearchAction {

    private Double costo = 2.0;

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posInicial = estadoCaperucita.getPosicionCaperucita();
        Point posFinal = posInicial;
        costo = 2.0;

        Consola.printExecution1(this, estadoCaperucita.getPosicionCaperucita());

        int movX = posInicial.x;
        while(movX < Escenario.LIMITE_DERECHA){
            movX++;
            EstadoCelda celdaActual = estadoCaperucita.getEscenario().getCeldas()[movX][posInicial.y];
            if (celdaActual.equals(EstadoCelda.ARBOL)) {
                movX = Escenario.LIMITE_DERECHA;
            } else {
                posFinal = new Point(movX, posInicial.y);
                switch (celdaActual) {
                    case LOBO:
                        costo += 3;
                        estadoCaperucita.reiniciarNivel(null);
                        estadoCaperucita.getEscenario().setPosicionCelda(posFinal, EstadoCelda.VACIA);
                        break;

                    case DULCE:
                        costo -= 3;
                        estadoCaperucita.getDulcesJuntados().add(posFinal);
                        estadoCaperucita.getEscenario().setPosicionCelda(posFinal, EstadoCelda.VACIA);
                        break;

                    case FLORES:
                        costo -= 2;
                        break;
                }
                costo++;
            }
        }

        if (!posInicial.equals(posFinal)) {
            estadoCaperucita.setPosicionCaperucita(posFinal);
            estadoCaperucita.getEscenario().setPosicionCelda(posInicial, EstadoCelda.VACIA);
            estadoCaperucita.getEscenario().setPosicionCelda(posFinal, EstadoCelda.CAPERUCITA);
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
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);
        Point posInicial = estadoCaperucita.getPosicionCaperucita();
        Point posFinal = posInicial;

        int movX = posInicial.x;
        while(movX < Escenario.LIMITE_DERECHA){
            movX++;
            EstadoCelda celdaActual = estadoCaperucita.getEscenario().getCeldas()[movX][posInicial.y];
            if (celdaActual.equals(EstadoCelda.ARBOL)) {
                movX = Escenario.LIMITE_DERECHA;
            } else {
                posFinal = new Point(movX, posInicial.y);
                switch (celdaActual) {
                    case LOBO:
                        environmentState.volverEstadoInicial();
                        estadoCaperucita.reiniciarNivel(environmentState.getPosicionCaperucita());
                        environmentState.updateWolfPosition();
                        return environmentState;

                    case DULCE:
                        estadoCaperucita.getDulcesJuntados().add(posFinal);
                        estadoCaperucita.getEscenario().setPosicionCelda(posFinal, EstadoCelda.VACIA);
                        environmentState.getEscenario().setPosicionCelda(posFinal, EstadoCelda.VACIA);
                        environmentState.getPosicionesDulces().remove(posFinal);
                        break;
                }
            }
        }

        estadoCaperucita.setPosicionCaperucita(posFinal);
        environmentState.setPosicionCaperucita(posFinal);
        environmentState.updateWolfPosition();
        return environmentState;
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
        return "IrDerecha";
    }

}