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
import java.util.HashSet;

public class IrIzquierda extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();
        System.out.println("\n" + Consola.textoColoreadoWhite("IrIzquierda -> SearchBasedAgentState -> " + celda + " posCaperucita: " + estadoCaperucita.getPosicionCaperucita()));

        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();

        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (Escenario.LIMITE_IZQUIERDA <= (posicionCaperucita.x - cantMovimientos)
                && (posicionCaperucita.x - cantMovimientos) <= Escenario.LIMITE_DERECHA
                && Escenario.LIMITE_ARRIBA <= posicionCaperucita.y
                && posicionCaperucita.y <= Escenario.LIMITE_ABAJO
        ) {
            //actualizo el estado de caperucita

            posicionCaperucita = new Point(posicionCaperucita.x - cantMovimientos, posicionCaperucita.y);
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);

            int posXActual = posicionCaperucita.x;
            int posYActual = posicionCaperucita.y;

            switch (celda) {
                case LOBO:
                    //Haciendo este movimiento el lobo se come a Caperucita
                    estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);
                    estadoCaperucita.setPosicionLobo(new Point(1, 1));
                    if (estadoCaperucita.getVidasRestantes() < 1) return null;
                    // TODO se fija si el agente falla?
                    return estadoCaperucita;
                case DULCE:
                    //Caperucita va a juntar el/los dulce/s y se desplaza hasta chocar con un arbol
                    HashSet<Point> dulcesNoJuntados = estadoCaperucita.getPosicionesDulces();
                    HashSet<Point> dulcesJuntados = estadoCaperucita.getPosicionesDulces();
                    int posXDulce;
                    int posYDulce;

                    for (Point dulce : dulcesNoJuntados) {
                        posXDulce = (int) dulce.getX();
                        posYDulce = (int) dulce.getY();

                        //Si está en la misma columna y está dentro de los posibles movimientos a la izquierda
                        if (posYActual == posYDulce && (posXActual + cantMovimientos) >= posXDulce && posXDulce >= posXActual) {
                            dulcesJuntados.add(dulce);
                        }
                    }

                    dulcesNoJuntados.removeAll(dulcesJuntados);

                    estadoCaperucita.setDulcesJuntados(dulcesJuntados);
                    estadoCaperucita.setPosicionesDulces(dulcesNoJuntados);
                    break;
                case FLORES:
                    //Caperucita se desplaza hasta llegar a la meta
                    break;
                case ARBOL:
                    if (estadoCaperucita.getPosicionesArboles().contains(estadoCaperucita.getPosicionCaperucita()))
                        return null;
                    //Caperucita se desplaza hasta chocar un arbol
                    break;
            }

            return estadoCaperucita;
        }

        return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        System.out.println(Consola.textoColoreadoWhite("IrIzquierda -> EnvironmentState"));
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();

        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();

        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();

        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (cantMovimientos > 0
                && Escenario.LIMITE_IZQUIERDA <= (posicionCaperucita.x - cantMovimientos)
                && (posicionCaperucita.x - cantMovimientos) <= Escenario.LIMITE_DERECHA
                && Escenario.LIMITE_ARRIBA <= posicionCaperucita.y
                && posicionCaperucita.y <= Escenario.LIMITE_ABAJO
        ) {
            if (celda.equals(EstadoCelda.LOBO)) {
                //Haciendo este movimiento el lobo se come a Caperucita
                estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);

                if (estadoCaperucita.getVidasRestantes() < 1) return null;

                return environmentState;
            }

            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);

            posicionCaperucita = new Point(posicionCaperucita.x - cantMovimientos, posicionCaperucita.y);

            //actualizo el estado de caperucita y el escenario
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.CAPERUCITA);

            int posXActual = posicionCaperucita.x;
            int posYActual = posicionCaperucita.y;

            switch (celda) {

                case DULCE:
                    //Caperucita va a juntar el/los dulce/s y se desplaza hasta chocar con un arbol
                    HashSet<Point> dulcesNoJuntados = estadoCaperucita.getPosicionesDulces();
                    HashSet<Point> dulcesJuntados = estadoCaperucita.getPosicionesDulces();
                    int posXDulce;
                    int posYDulce;

                    for (Point dulce : dulcesNoJuntados) {
                        posXDulce = (int) dulce.getX();
                        posYDulce = (int) dulce.getY();

                        //Si está en la misma columna y está dentro de los posibles movimientos a la izquierda
                        if (posYActual == posYDulce && (posXActual + cantMovimientos) >= posXDulce && posXDulce >= posXActual) {
                            dulcesJuntados.add(dulce);
                        }
                    }

                    dulcesNoJuntados.removeAll(dulcesJuntados);

                    estadoCaperucita.setDulcesJuntados(dulcesJuntados);
                    estadoCaperucita.setPosicionesDulces(dulcesNoJuntados);
                    break;
                case FLORES:
                    //Caperucita se desplaza hasta llegar a la meta
                    break;
                case ARBOL:
                    //Caperucita se desplaza hasta chocar un arbol
                    break;
            }
        } else {
            return null;
        }

        environmentState.setEscenario(escenario);
        environmentState.setPosicionCaperucita(posicionCaperucita);

        return environmentState;
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
        return "IrIzquierda";
    }
}