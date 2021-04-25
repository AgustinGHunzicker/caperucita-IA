package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import enumeration.TipoLado;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

public class Moverse {

    public static SearchBasedAgentState modelSearchBasedAgentState(SearchBasedAgentState s, TipoLado tipoMovimiento) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();
        EstadoCelda celda = estadoCaperucita.getUltimaPerception().getAbajoPercepcion();

        if (celda.equals(EstadoCelda.LOBO)) {
            //Haciendo este movimiento el lobo se come a Caperucita
            int vidas = estadoCaperucita.getVidasRestantes() - 1;
            estadoCaperucita.setVidasRestantes(vidas);
            System.out.println(Consola.textoColoreadoRed("Me mató el lobo"));
        } else {
            int cantMovimientos = estadoCaperucita.getUltimaPerception().getCantMovimientosAbajo();
            posicionCaperucita = moverseAlLado(tipoMovimiento, posicionCaperucita, cantMovimientos);
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);

            int posXActual = posicionCaperucita.x;
            int posYActual = posicionCaperucita.y;

            switch (celda) {
                case FLORES: {
                    //Caperucita se desplaza hasta llegar a la meta
                    System.out.println(Consola.textoColoreadoCyan("Llegué a la meta en " + posXActual + ", " + posYActual));
                }
                case DULCE: {
                    //Caperucita va a juntar el/los dulce/s y se desplaza hasta chocar con un arbol
                    juntarDulce(estadoCaperucita, cantMovimientos, posXActual, posYActual);
                }
                case ARBOL: {
                    //Caperucita se desplaza hasta chocar un arbol
                    System.out.println(Consola.textoColoreadoCyan("Me choqué con un arbol en " + posXActual + ", " + posYActual));
                }
            }
        }

        return estadoCaperucita;
    }

    public static EnvironmentState modelEnvironmentState(AgentState ast, EnvironmentState est, TipoLado tipoMovimiento) {
        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();
        EstadoCelda celda = estadoCaperucita.getUltimaPerception().getAbajoPercepcion();

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();

        if (celda.equals(EstadoCelda.LOBO)) {
            //Haciendo este movimiento el lobo se come a Caperucita
            int vidas = estadoCaperucita.getVidasRestantes() - 1;
            estadoCaperucita.setVidasRestantes(vidas);
            System.out.println(Consola.textoColoreadoRed("Me mató el lobo"));
        } else {
            int cantMovimientos = estadoCaperucita.getUltimaPerception().getCantMovimientosAbajo();
            int posXActual = posicionCaperucita.x;
            int posYActual = posicionCaperucita.y;
            escenario.setPosicionCelda(posXActual, posYActual, EstadoCelda.VACIA);

            // Actualiza la posición de caperucita
            posicionCaperucita = moverseAlLado(tipoMovimiento, posicionCaperucita, cantMovimientos);
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);
            posXActual = posicionCaperucita.x;
            posYActual = posicionCaperucita.y;

            switch (celda) {
                case FLORES: {
                    //Caperucita se desplaza hasta llegar a la meta
                    System.out.println(Consola.textoColoreadoCyan("Llegué a la meta en " + posXActual + ", " + posYActual));
                }
                case DULCE: {
                    //Caperucita va a juntar el/los dulce/s y se desplaza hasta chocar con un arbol
                    juntarDulce(estadoCaperucita, cantMovimientos, posXActual, posYActual);
                }
                case ARBOL: {
                    //Caperucita se desplaza hasta chocar un arbol
                    System.out.println(Consola.textoColoreadoCyan("Me choqué con un arbol en " + posXActual + ", " + posYActual));
                }
            }

            escenario.setPosicionCelda(posXActual, posYActual, EstadoCelda.CAPERUCITA);
        }

        environmentState.setEscenario(escenario);
        environmentState.setPosicionCaperucita(posicionCaperucita);

        return environmentState;
    }

    /**
     * Es la lógica de juntar dulces en una linea en la dirección que se mueva.
     */
    private static void juntarDulce(EstadoCaperucita estadoCaperucita, int cantMovimientos, int posXActual, int posYActual) {
        HashSet<Point> dulcesNoJuntados = estadoCaperucita.getPosicionesDulces();
        HashSet<Point> dulcesJuntados = estadoCaperucita.getPosicionesDulces();
        int posXDulce;
        int posYDulce;
        for (Point dulce : dulcesNoJuntados) {
            posXDulce = (int) dulce.getX();
            posYDulce = (int) dulce.getY();

            //Si está en la misma columna y está dentro de los posibles movimientos a la derecha
            if (posYActual == posYDulce && posXActual <= posXDulce && posXDulce <= (posXActual + cantMovimientos)) {
                dulcesJuntados.add(dulce);
                dulcesNoJuntados.remove(dulce);
                System.out.println(Consola.textoColoreadoCyan("Junté un dulce en " + posXDulce + ", " + posYDulce));
            }
        }

        estadoCaperucita.setDulcesJuntados(dulcesJuntados);
        estadoCaperucita.setPosicionesDulces(dulcesNoJuntados);
    }

    private static Point moverseAlLado(TipoLado movimiento, Point posicionCaperucita, int cantMovimientos) {
        Point nuevaPosicion = new Point(posicionCaperucita);
        switch (movimiento) {
            case IZQUIERDA:
                nuevaPosicion = new Point(posicionCaperucita.x - cantMovimientos, posicionCaperucita.y);
                break;
            case DERECHA:
                nuevaPosicion = new Point(posicionCaperucita.x + cantMovimientos, posicionCaperucita.y);
                break;
            case ABAJO:
                nuevaPosicion = new Point(posicionCaperucita.x, posicionCaperucita.y + cantMovimientos);
                break;
            case ARRIBA:
                nuevaPosicion = new Point(posicionCaperucita.x, posicionCaperucita.y - cantMovimientos);
                break;
        }
        return nuevaPosicion;
    }
}
