package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.exercise.caperucita.search.CaperucitaPerception;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

public class IrArriba extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // ARRIBA THINGS
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasAbajo();
        int cantMovimientos = estadoCaperucita.getCantMovimientosArriba();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos < 1 || celda.equals(EstadoCelda.LOBO))
            return null;
        else {
            //debo actualizar en el ambiente la posicion vieja de caperucita
            estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario().setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            //Para verificar los dulces
            int pisoInferior = posicionCaperucita.y - cantMovimientos;
            int pisoSuperior = posicionCaperucita.y;
            //realizo todos los movimientos que puedo hacer hacia la derecha
            estadoCaperucita.setPosicionCaperucita(new Point(posicionCaperucita.x, pisoSuperior));

            //si hay un dulce en esa dirección
            // en el caso que también hay flores en esa dirección, solo lo junta si esta antes de las flores
            /*if (celda.equals(EstadoCelda.DULCE)) {
                for (Point dulce : estadoCaperucita.getPosicionesDulces()) {
                    if (dulce.x == estadoCaperucita.getPosicionCaperucita().x && dulce.y <= pisoSuperior && pisoInferior <= dulce.y) {
                        estadoCaperucita.addDulceJuntado(dulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario().setPosicionCelda(dulce.x, dulce.y, EstadoCelda.VACIA);
                        estadoCaperucita.getPosicionesDulces().remove(dulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getPosicionesDulces().remove(dulce);
                    }
                }
            }*/

            //Se debe verificar si no esta sobre las flores,
            // puesto que la bandera FLORES puede quedar anulada por los dulces
            for (Point posicionFlor : estadoCaperucita.getPosicionFlores()) {
                if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                    estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                    break;
                }
            }

            estadoCaperucita.actualizarPosicionCaperucita(estadoCaperucita.getPosicionCaperucita().x, estadoCaperucita.getPosicionCaperucita().y);
            //tengo que actualizar todo el estado caperucita completo, en esta copia

            //CaperucitaPerception p = estadoCaperucita.getAmbienteActual().getPercept();
            //estadoCaperucita.updateState(p);

            return estadoCaperucita;
        }
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        System.out.println(Consola.textoColoreadoWhite("IrArriba -> EnvironmentState"));
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();

        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasArriba();

        int cantMovimientos = estadoCaperucita.getCantMovimientosArriba();
        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (cantMovimientos > 0
                && Escenario.LIMITE_IZQUIERDA <= posicionCaperucita.x
                && posicionCaperucita.x <= Escenario.LIMITE_DERECHA
                && Escenario.LIMITE_ARRIBA <= (posicionCaperucita.y - cantMovimientos)
                && (posicionCaperucita.y - cantMovimientos) <= Escenario.LIMITE_ABAJO
        ) {
            if (celda.equals(EstadoCelda.LOBO)) {
                //Haciendo este movimiento el lobo se come a Caperucita
                estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);

                if (estadoCaperucita.getVidasRestantes() < 1) return null;
                // TODO se fija si el agente falla?
                return environmentState;
            }

            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);

            posicionCaperucita = new Point(posicionCaperucita.x, posicionCaperucita.y - cantMovimientos);

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

                        //Si está en la misma columna y está dentro de los posibles movimientos hacia arriba
                        if (posXActual == posXDulce && (posYActual + cantMovimientos) >= posYDulce && posYDulce >= posYActual) {
                            dulcesJuntados.add(dulce);
                            //System.out.println(Consola.textoColoreadoCyan("Junté un dulce en " + posXDulce + ", " + posYDulce));
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
        return "IrArriba";
    }

}