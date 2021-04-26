package frsf.cidisi.exercise.caperucita.search.actions;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.exercise.caperucita.search.Ambiente;
import frsf.cidisi.exercise.caperucita.search.EstadoAmbiente;
import frsf.cidisi.exercise.caperucita.search.EstadoCaperucita;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

public class IrDerecha extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;

        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        //Me devuelve que ve a primera vista caperucita, el lobo, un dulce, flores o arbol-> puede ser que antes del arbol haya vacio
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasDerecha();

        System.out.println("\n"+Consola.textoColoreadoWhite("IrDerecha -> SearchBasedAgentState -> " + celda + " posCaperucita: " + estadoCaperucita.getPosicionCaperucita()));

        int cantMovimientos = estadoCaperucita.getCantMovimientosDerecha();

        // Si no tiene movimiento o esta el lobo, es una accion no valida
        if (cantMovimientos == 0 || celda.equals(EstadoCelda.LOBO))
            return null;
        else {
            //realizo todos los movimientos que puedo hacer hacia la derecha
            estadoCaperucita.setPosicionCaperucita(new Point(posicionCaperucita.x+cantMovimientos,posicionCaperucita.y));
            //teniendo en cuenta que no seria posible ir a la derecha y a la izquierda a la vez desde la posicion,
            // puedo asumir que tenia cero posiciones a mover hacia la izquierda, entonces ahora le agrego las posiciones que me movi a la derecha
            //estadoCaperucita.setCantMovimientosIzquierda(estadoCaperucita.getCantMovimientosDerecha());
            //me movi hasta el final hacia la derecha por lo que ahora no tengo mas movimiento hacia esa direccion
            //estadoCaperucita.setCantMovimientosDerecha(0);
            //ahora estoy en otra posicion, debo actualizar mi vista hacia arriba y hacia abajo
            //int arriba = 0;
            //TENGO QUE USAR EL ESCENARIO DEL AMBIENTE PARA MIRAR, NO EL DE CAPERUCITA PORQUE NO ES EL REAL, ESE ESCENARIO MAS ADELANTE SACARLO
            //Escenario escenario = estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario();


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

        //return null;
    }

    /**
     * This method updates the agent state and the real world state.
     */
    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {

        EstadoAmbiente environmentState = (EstadoAmbiente) est;
        Escenario escenario = environmentState.getEscenario();

        EstadoCaperucita estadoCaperucita = ((EstadoCaperucita) ast);


        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasDerecha();
        System.out.println(Consola.textoColoreadoWhite("IrDerecha -> EnvironmentState -> " + celda));

        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();
        int cantMovimientos = estadoCaperucita.getCantMovimientosDerecha();

        // verifica que haya movimientos disponibles y que las posiciones sean válidas
        if (cantMovimientos > 0
                && Escenario.LIMITE_IZQUIERDA <= (posicionCaperucita.x + cantMovimientos)
                && (posicionCaperucita.x + cantMovimientos) <= Escenario.LIMITE_DERECHA
                && Escenario.LIMITE_ARRIBA <= posicionCaperucita.y
                && posicionCaperucita.y <= Escenario.LIMITE_ABAJO
        ) {
            if (celda.equals(EstadoCelda.LOBO)) {
                //Haciendo este movimiento el lobo se come a Caperucita
                estadoCaperucita.setVidasRestantes(estadoCaperucita.getVidasRestantes() - 1);

                if (estadoCaperucita.getVidasRestantes() < 1) return null;
                // TODO se fija si el agente falla?
                return environmentState;
            }

            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);

            posicionCaperucita = new Point(posicionCaperucita.x + cantMovimientos, posicionCaperucita.y);

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

                        //Si está en la misma columna y está dentro de los posibles movimientos a la derecha
                        if (posYActual == posYDulce && (posXActual - cantMovimientos) <= posXDulce && posXDulce <= posXActual) {
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

        environmentState.setPosicionCaperucita(estadoCaperucita.getPosicionCaperucita());
        environmentState.setEscenario(escenario);
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
        return "IrDerecha";
    }

}