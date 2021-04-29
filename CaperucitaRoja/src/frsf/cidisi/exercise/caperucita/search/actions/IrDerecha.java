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

public class IrDerecha extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
        System.out.println(Consola.textoColoreadoPurple("Probando " + this));

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) s;
        Point posicionCaperucita = estadoCaperucita.getPosicionCaperucita();

        // particular de IrDerecha
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasDerecha();
        int cantMovimientos = estadoCaperucita.getCantMovimientosDerecha();

        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0 && !celda.equals(EstadoCelda.LOBO)) {

            int posXCaperucita = posicionCaperucita.x + cantMovimientos;
            int posYCaperucita = posicionCaperucita.y;
            estadoCaperucita.setPosicionCaperucita(new Point(posicionCaperucita.x + cantMovimientos, posicionCaperucita.y));

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

        // particular de IrDerecha
        EstadoCelda celda = estadoCaperucita.getPercepcionCeldasDerecha();
        int cantMovimientos = estadoCaperucita.getCantMovimientosDerecha();


        // Si no tiene movimiento -> ARBOL o esta el LOBO, es una acción no valida -> quitaría una vida
        if (cantMovimientos > 0 && !celda.equals(EstadoCelda.LOBO)) {

            int posXCaperucita = posicionCaperucita.x + cantMovimientos;
            int posYCaperucita = posicionCaperucita.y;


            //actualizo el estado de caperucita y el escenario
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);
            posicionCaperucita = new Point(posXCaperucita, posYCaperucita);
            estadoCaperucita.setPosicionCaperucita(posicionCaperucita);
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.CAPERUCITA);


            //si hay un dulce en esa dirección
            // en el caso que también hay flores en esa dirección, solo lo junta si esta antes de las flores
            /*
                        //Para verificar los dulces
            int pisoInferior = posicionCaperucita.y;
            int pisoSuperior = posicionCaperucita.y;
            if (celda.equals(EstadoCelda.DULCE)) {
                for (Point dulce : estadoCaperucita.getPosicionesDulces()) {
                    if (dulce.x == estadoCaperucita.getPosicionCaperucita().x && dulce.y <= pisoSuperior && pisoInferior <= dulce.y) {
                        estadoCaperucita.addDulceJuntado(dulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getEscenario().setPosicionCelda(dulce.x, dulce.y, EstadoCelda.VACIA);
                        estadoCaperucita.getPosicionesDulces().remove(dulce);
                        estadoCaperucita.getAmbienteActual().getEnvironmentState().getPosicionesDulces().remove(dulce);
                    }
                }
            }
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
            */
            //Se debe verificar si no esta sobre las flores,
            // puesto que la bandera FLORES puede quedar anulada por los dulces

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