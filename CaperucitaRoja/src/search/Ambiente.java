package search;

import domain.Escenario;
import enumeration.TipoLado;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;
import java.util.HashSet;

public class Ambiente extends Environment {

    private final static Point UNKNOWN = new Point(-1, -1);

    public Ambiente() {
        this.environmentState = new EstadoAmbiente();
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    @Override
    public CaperucitaPerception getPercept() {
        /* ------- Creamos la percepción que el agente obtendrá, a partir del estado actual del ambiente -------*/
        CaperucitaPerception perception = new CaperucitaPerception();
        /*------- Primero chequeamos donde está caperucita -------*/
        int posXCap = (int) this.getEnvironmentState().getPosicionCaperucita().getX();
        int posYCap = (int) this.getEnvironmentState().getPosicionCaperucita().getY();
        perception.setPosicionActual(this.getEnvironmentState().getPosicionCaperucita());

        /*------- Se mira hacia las cuatro direcciones -------*/
        VistaCaperucita vistaIzquierda = this.verLado(TipoLado.IZQUIERDA, posXCap, posYCap);
        VistaCaperucita vistaDerecha = this.verLado(TipoLado.DERECHA, posXCap, posYCap);
        VistaCaperucita vistaArriba = this.verLado(TipoLado.ARRIBA, posXCap, posYCap);
        VistaCaperucita vistaAbajo = this.verLado(TipoLado.ABAJO, posXCap, posYCap);

        /*------- Se carga las posición del lobo, si es que lo vió -------*/
        perception.setPosicionLobo(this.loboEsta(vistaIzquierda.posicionLobo, vistaDerecha.posicionLobo, vistaArriba.posicionLobo, vistaAbajo.posicionLobo));

        /*------- Se carga las posición de los dulces, si hubiese -------*/
        perception.addPosicionesDulces(vistaIzquierda.posicionDulces);
        perception.addPosicionesDulces(vistaDerecha.posicionDulces);
        perception.addPosicionesDulces(vistaArriba.posicionDulces);
        perception.addPosicionesDulces(vistaAbajo.posicionDulces);

        return perception;
    }

    /**
     * Si caperucita no tiene mas vidas, entonces su objetivo falló
     */
    public boolean agentFailed(AgentState state) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) state;
        return estadoCaperucita.getVidasRestantes() <= 0;
    }

    public void actualizarPosicionCaperucita(int x, int y) {
        ((EstadoAmbiente) environmentState).actualizarPosicionCaperucita(x, y);
    }

    /**
     * Clase que funciona como estructura de vista de caperucita hacia una dirección en particular
     */
    private static class VistaCaperucita {
        protected Point posicionLobo;
        protected HashSet<Point> posicionDulces;

        public VistaCaperucita() {
            this.posicionLobo = new Point(-1, -1);
            this.posicionDulces = new HashSet<>();
        }
    }

    /**
     * Devuelve los objetos encontrados en linea horizontal al lado indicado.
     * Realiza la búsqueda hasta encontrar un árbol o un lobo.
     *
     * @param lado    - Lado a buscar elementos.
     * @param posXCap - posicion x de caperucita.
     * @param posYCap - posicion y de caperucita.
     * @return VistaCaperucita - retorna la vista de caperucita del lado deseado.
     */
    private VistaCaperucita verLado(TipoLado lado, int posXCap, int posYCap) {
        /*------- Recuperamos el estado del escenario en cuestión -------*/
        Escenario escenario = this.getEnvironmentState().getEscenario();

        VistaCaperucita vista = new VistaCaperucita();
        boolean arbol_flor = false;

        int posicionXActual;
        int posicionYActual;
        int cantidadPosiciones = 1;

        while (!arbol_flor) {
            posicionXActual = posXCap;
            posicionYActual = posYCap;

            switch (lado) {
                case IZQUIERDA:
                    posicionXActual = posXCap - cantidadPosiciones;
                    break;
                case DERECHA:
                    posicionXActual = posXCap + cantidadPosiciones;
                    break;
                case ARRIBA:
                    posicionYActual = posYCap - cantidadPosiciones;
                    break;
                case ABAJO:
                    posicionYActual = posYCap + cantidadPosiciones;
                    break;
            }
            switch (escenario.getPosicionCelda(posicionXActual, posicionYActual)) {
                /* puedo seguir avanzando, está vacío */
                case VACIA:
                case CAPERUCITA:
                    cantidadPosiciones++;
                    break;
                /* hay un arbol, hasta acá nomás se puede llegar */
                case ARBOL:
                    arbol_flor = true;
                    break;
                /* hay un dulce, lo tengo que juntar y puedo seguir avanzando */
                case DULCE:
                    cantidadPosiciones++;
                    vista.posicionDulces.add(new Point(posicionXActual, posicionYActual));
                    break;
                /* está el lobo, no debería moverse en esta dirección */
                case LOBO:
                    cantidadPosiciones++;
                    vista.posicionLobo.setLocation(posicionXActual, posicionYActual);
                    break;
                /* está el camino de flores, la meta */
                case FLORES:
                    if (posicionYActual == Escenario.LIMITE_ABAJO || posicionYActual == Escenario.LIMITE_ARRIBA) {
                        // Llegó al borde del mapa
                        arbol_flor = true;
                    } else {
                        cantidadPosiciones++;
                    }
                    break;
            }
        }
        return vista;
    }

    /*
     * Devuelve la posicion del lobo si hay a la vista en alguno de los lados,
     * en caso contrario, devuelve un fuera de rango - "pointOutOfRange"
     */
    private Point loboEsta(Point celdaIzquierda, Point celdaDerecha, Point celdaArriba, Point celdaAbajo) {
        if (!celdaIzquierda.equals(UNKNOWN))
            return celdaIzquierda;
        if (!celdaDerecha.equals(UNKNOWN))
            return celdaDerecha;
        if (!celdaArriba.equals(UNKNOWN))
            return celdaArriba;
        if (!celdaAbajo.equals(UNKNOWN))
            return celdaAbajo;
        else
            return UNKNOWN;
    }

    public String toString() {
        return environmentState.toString();
    }


    public void setEstadoInicialAmbiente() {
        EstadoAmbiente inicio = new EstadoAmbiente();
        inicio.setEstadoInicialAmbiente(null);
        inicio.setPosicionCaperucita(((EstadoAmbiente) this.environmentState).getPosicionCaperucita());
        ((EstadoAmbiente) environmentState).setEstadoInicialAmbiente(inicio);
    }
}
