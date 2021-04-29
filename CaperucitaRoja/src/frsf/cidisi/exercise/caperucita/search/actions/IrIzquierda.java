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

    private Double costo = 0.0;

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        System.out.println(Consola.textoColoreadoPurple("Probando " + this));

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // IZQUIERDA THINGS
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();
        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0 && !celda.equals(EstadoCelda.LOBO)) {
            //Para verificar los dulces
            int pisoInferior = posicionCaperucita.x - cantMovimientos;
            int pisoSuperior = posicionCaperucita.x;
            //realizo todos los movimientos que puedo hacer hacia la derecha
            estadoCaperucita.setPosicionCaperucita(new Point(posicionCaperucita.x - cantMovimientos, posicionCaperucita.y));

            //si hay un dulce en esa dirección
            // en el caso que también hay flores en esa dirección, solo lo junta si esta antes de las flores
            /*if (celda.equals(EstadoCelda.DULCE)) {
                for (Point posDulce : estadoCaperucita.getPosicionesDulces()) {
                    if (posDulce.x == estadoCaperucita.getPosicionCaperucita().x && posDulce.x <= pisoSuperior && pisoInferior <= posDulce.x) {
                        estadoCaperucita.addDulceJuntado(posDulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario().setPosicionCelda(posDulce.x, posDulce.y, EstadoCelda.VACIA);
                        estadoCaperucita.getPosicionesDulces().remove(posDulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getPosicionesDulces().remove(posDulce);
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
        //fijarse que ya esta actualizando el mundo real cuando lo hace arriba, arriba solo deberia actualizarlo en una copia
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);


        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();


        for (Point p : estadoCaperucita.getPosicionFlores()) {
            if (p.equals(posicionCaperucita)) {
                estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                environmentState.setPosicionCaperucita(posicionCaperucita);
                return environmentState;

            }
        }

        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasIzquierda();


        int cantMovimientos = estadoCaperucita.getCantMovimientosIzquierda();

        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (cantMovimientos > 0
                && Escenario.LIMITE_IZQUIERDA <= posicionCaperucita.x
                && posicionCaperucita.x <= Escenario.LIMITE_DERECHA
                && Escenario.LIMITE_ARRIBA <= (posicionCaperucita.y + cantMovimientos)
                && (posicionCaperucita.y + cantMovimientos) <= Escenario.LIMITE_ABAJO
        ) {
            if (celda.equals(EstadoCelda.LOBO)) {
                //Haciendo este movimiento el lobo se come a Caperucita
                estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);

                if (estadoCaperucita.getVidasRestantes() < 1) return null;
                // TODO se fija si el agente falla?
                return environmentState;
            }

            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            posicionCaperucita = new Point(posicionCaperucita.x, posicionCaperucita.y + cantMovimientos);

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

                        //Si está en la misma columna y está dentro de los posibles movimientos hacia abajo
                        if (posXActual == posXDulce && (posYActual - cantMovimientos) <= posYDulce && posYDulce <= posYActual) {
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
                    //System.out.println(Consola.textoColoreadoCyan("Llegué a la meta en " + posXActual + ", " + posYActual));
                    break;
                case ARBOL:
                    //Caperucita se desplaza hasta chocar un arbol
                    //System.out.println(Consola.textoColoreadoCyan("Me choqué con un árbol en " + posXActual + ", " + posYActual));
                    break;
            }
        } else {
            return null;
        }

        System.out.println(escenario);
        environmentState.setEscenario(escenario);
        environmentState.setPosicionCaperucita(posicionCaperucita);

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
        return "IrIzquierda";
    }
}