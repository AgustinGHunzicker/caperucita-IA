package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.EstadoCelda;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

public class IrAbajo extends SearchAction {

    //private Double costo = 0.0;

    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // particular de IrAbajo
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasAbajo();
        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos < 1 || celda.equals(EstadoCelda.LOBO))
            return null;
        else {
            //debo actualizar en el ambiente la posicion vieja de caperucita
            estadoCaperucita.getEstadoAmbienteActual().getEscenario().setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            //Para verificar los dulces
            int pisoInferior = posicionCaperucita.y;
            int pisoSuperior = posicionCaperucita.y + cantMovimientos;
            //realizo todos los movimientos que puedo hacer hacia la derecha
            //escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
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

            System.out.println(estadoCaperucita.getEstadoAmbienteActual().getEscenario());
            estadoCaperucita.actualizarPosicionCaperucita(estadoCaperucita.getPosicionCaperucita().x, estadoCaperucita.getPosicionCaperucita().y);
            //CaperucitaPerception p = estadoCaperucita.getAmbienteActual().getPercept();
            //estadoCaperucita.updateState(p);
            return estadoCaperucita;
        }

        /*EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posCaperucitaVieja = estadoCaperucita.getPosicionCaperucita();
        EstadoCelda perceptCeldasAbajo = estadoCaperucita.getPercepcionCeldasAbajo();

        System.out.println("\n" + Consola.textoColoreadoWhite("IrAbajo -> SearchBasedAgentState -> " + perceptCeldasAbajo + " posCaperucita: " + estadoCaperucita.getPosicionCaperucita()));
        System.out.println("\n" + Consola.textoColoreadoWhite("IrAbajo -> SearchBasedAgentState -> " + perceptCeldasAbajo + " posFlores: " + estadoCaperucita.getPosicionFlores()));


        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();
        int posXActual = posCaperucitaVieja.x;
        int posYActual = posCaperucitaVieja.y + cantMovimientos;

        /* verifica que haya movimientos disponibles y que las posiciones sean válidas *
        if (cantMovimientos > 0 &&
                Escenario.LIMITE_IZQUIERDA <= posXActual && posXActual <= Escenario.LIMITE_DERECHA &&
                Escenario.LIMITE_ARRIBA <= posYActual && posYActual <= Escenario.LIMITE_ABAJO) {

            moverseAbajo(estadoCaperucita, perceptCeldasAbajo, cantMovimientos, posXActual, posYActual);

            return estadoCaperucita;
        }
        return null;*/
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);


        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // particular de IrAbajo
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasAbajo();
        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();

        for (Point posCaperucita : estadoCaperucita.getPosicionFlores()) {
            if (posCaperucita.equals(posicionCaperucita)) {
                estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                environmentState.setPosicionCaperucita(posicionCaperucita);
                return environmentState;
            }
        }


        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (cantMovimientos > 0) {
            if (celda.equals(EstadoCelda.LOBO)) {
                //Haciendo este movimiento el lobo se come a Caperucita
                estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);
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

        environmentState.setEscenario(escenario);
        environmentState.setPosicionCaperucita(posicionCaperucita);
        System.out.print(escenario);
        return environmentState;
    }


    /*@Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();

        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);
        Point posCaperucitaVieja = estadoCaperucita.getPosicionCaperucita();

        EstadoCelda perceptCeldasAbajo = estadoCaperucita.getPercepcionCeldasAbajo();

        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();
        int posXActual = posCaperucitaVieja.x;
        int posYActual = posCaperucitaVieja.y + cantMovimientos;

        moverseAbajo(estadoCaperucita, perceptCeldasAbajo, cantMovimientos, posXActual, posYActual);

        environmentState.setEscenario(escenario);
        environmentState.setPosicionCaperucita(posCaperucitaVieja);

        return environmentState;
    }

    private void moverseAbajo(EstadoCaperucita estadoCaperucita, EstadoCelda percepCeldasAbajo, int cantMovimientos, int posX, int posY) {
        if (percepCeldasAbajo.equals(EstadoCelda.LOBO)) {
            // volvemos a establecer el estado inicial de la simulación, pero esta vez caperucita tiene una vida menos
            //estadoCaperucita.disminuirVidas();
            //estadoCaperucita.getAmbienteActual().setEstadoAmbienteInicial();
            Point posInicialCap = estadoCaperucita.getEstadoAmbienteActual().getPosicionCaperucita();
            estadoCaperucita.actualizarPosicionCaperucita(posInicialCap.x, posInicialCap.y);

            costo += 5;
        } else {
            switch (percepCeldasAbajo) {
                case DULCE:
                    //Caperucita va a juntar el/los dulce/s y se desplaza hasta chocar con un árbol
                    HashSet<Point> dulcesNoJuntados = estadoCaperucita.getPosicionesDulces();
                    HashSet<Point> dulcesJuntados = estadoCaperucita.getPosicionesDulces();
                    int posXDulce;
                    int posYDulce;

                    for (Point dulce : dulcesNoJuntados) {
                        posXDulce = (int) dulce.getX();
                        posYDulce = (int) dulce.getY();

                        //Si está en la misma columna y está dentro de los posibles movimientos hacia abajo
                        if (posX == posXDulce && posY <= posYDulce && posYDulce <= (posY + cantMovimientos)) {
                            costo -= 3;
                            dulcesJuntados.add(dulce);
                        }
                    }
                    dulcesNoJuntados.removeAll(dulcesJuntados);
                    estadoCaperucita.setDulcesJuntados(dulcesJuntados);
                    estadoCaperucita.setPosicionesDulces(dulcesNoJuntados);
                    break;
                case FLORES:
                    for (Point p : estadoCaperucita.getPosicionFlores()) {
                        if (p.equals(estadoCaperucita.getPosicionCaperucita())) {
                            estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                            break;
                        }
                    }
                    costo -= 3;
                    break;
            }
            // Caperucita se desplaza a la nueva posición siempre que no se encuentre al lobo (se actualiza el estado y el ambiente)
            estadoCaperucita.actualizarPosicionCaperucita(posX, posY);

            costo += cantMovimientos;
        }
    }*/

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return 1.0;
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "IrAbajo";
    }
}