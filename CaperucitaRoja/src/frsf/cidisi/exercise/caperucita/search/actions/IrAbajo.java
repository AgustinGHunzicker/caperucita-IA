package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

public class IrAbajo extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;

        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        //Me devuelve que ve a primera vista caperucita, el lobo, un dulce, flores o arbol-> puede ser que antes del arbol haya vacio
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasAbajo();

        System.out.println("\n" + Consola.textoColoreadoWhite("IrAbajo -> SearchBasedAgentState -> " + celda + " posCaperucita: " + estadoCaperucita.getPosicionCaperucita()));

        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();

        // Si no tiene movimiento o esta el lobo, es una accion no valida
        if (cantMovimientos == 0 || celda.equals(EstadoCelda.LOBO))
            return null;
        else {
            //debo actualizar en el ambiente la posicion vieja de caperucita
            estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario().setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            //realizo todos los movimientos que puedo hacer hacia la derecha
            estadoCaperucita.setPosicionCaperucita(new Point(posicionCaperucita.x, posicionCaperucita.y + cantMovimientos));
            for (Point p : estadoCaperucita.getPosicionFlores()) {
                if (p.equals(estadoCaperucita.getPosicionCaperucita())) {
                    estadoCaperucita.setFloresJuntadas(estadoCaperucita.getFloresJuntadas() + 1);
                    break;
                }
            }
            int capX = estadoCaperucita.getPosicionCaperucita().x;
            int capY = estadoCaperucita.getPosicionCaperucita().y;
            //actualizar el ambiente con mi posicion el ambiente - posiblemente se pueda hacer con un solo metodo
            //lo hace en el ambiente, puede ser que lo este haciendo en una copia o en el real, dependiendo de donde se llama el execute()
            estadoCaperucita.actualizarPosicionCaperucita(capX, capY);
            //TODO debo actualizar las otras cosas tambien

            //para no rehacer todo los metodos de nuevo, hago una percepcion sobre el ambiente clonado, y actualizo su vista de caperucita
            Perception p = estadoCaperucita.getAmbienteActual().getPercept();
            estadoCaperucita.updateState(p);

            return estadoCaperucita;
        }

    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        System.out.println(Consola.textoColoreadoWhite("IrAbajo -> EnvironmentState - Actualizar el Mundo real"));
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

        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasAbajo();
        System.out.println(estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario());


        int cantMovimientos = estadoCaperucita.getCantMovimientosAbajo();

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