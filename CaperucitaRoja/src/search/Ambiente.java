package search;

import domain.Escenario;
import enumeration.EstadoCelda;
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
        perception.setPosicionActual(new Point(posXCap, posYCap));

        /*------- Se mira hacia las cuatro direcciones -------*/
        VistaCaperucita vistaIzquierda = this.verLado(TipoLado.IZQUIERDA, posXCap, posYCap);
        VistaCaperucita vistaDerecha = this.verLado(TipoLado.DERECHA, posXCap, posYCap);
        VistaCaperucita vistaArriba = this.verLado(TipoLado.ARRIBA, posXCap, posYCap);
        VistaCaperucita vistaAbajo = this.verLado(TipoLado.ABAJO, posXCap, posYCap);

        /*------- Se carga la bandera de jerarquía de posicionamiento de objetos en cada dirección -------*/
        perception.setPercepcionCeldasIzquierda(this.getContenidoEnVista(vistaIzquierda));
        perception.setPercepcionCeldasDerecha(this.getContenidoEnVista(vistaDerecha));
        perception.setArribaPerception(this.getContenidoEnVista(vistaArriba));
        perception.setAbajoPerception(this.getContenidoEnVista(vistaAbajo));


        /*------- Se carga los movimientos permitidos en cada dirección -------*/
        perception.setCantMovimientosIzquierda(vistaIzquierda.cantidadPosiciones);
        perception.setCantMovimientosDerecha(vistaDerecha.cantidadPosiciones);
        perception.setCantMovimientosArriba(vistaArriba.cantidadPosiciones);
        perception.setCantMovimientosAbajo(vistaAbajo.cantidadPosiciones);

        /*------- Se pasa la ubicación actual de caperucita -------*/
        perception.setPosicionActual(this.getEnvironmentState().getPosicionCaperucita());

         /*------- Se carga las posición del lobo, si es que lo vió -------*/
        perception.setPosicionLobo(this.loboEsta(vistaIzquierda.posicionLobo, vistaDerecha.posicionLobo, vistaArriba.posicionLobo, vistaAbajo.posicionLobo));

        /*------- Se carga las posición de las flores, si es que las haya visto -------*/
        perception.addPosicionFlores(vistaIzquierda.posicionFlores);
        perception.addPosicionFlores(vistaDerecha.posicionFlores);
        perception.addPosicionFlores(vistaArriba.posicionFlores);
        perception.addPosicionFlores(vistaAbajo.posicionFlores);

        /*------- Se carga las posición de los árboles que haya visto -------*/
        perception.addPosicionArbol(vistaIzquierda.posicionArbol);
        perception.addPosicionArbol(vistaDerecha.posicionArbol);
        perception.addPosicionArbol(vistaArriba.posicionArbol);
        perception.addPosicionArbol(vistaAbajo.posicionArbol);

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
        protected HashSet<Point> posicionFlores;
        protected Point posicionArbol;
        protected HashSet<Point> posicionDulces;
        protected int cantidadPosiciones;

        public VistaCaperucita() {
            this.posicionLobo = new Point(-1, -1);
            this.posicionArbol = new Point(-1, -1);
            this.posicionFlores = new HashSet<>();
            this.posicionDulces = new HashSet<>();
            this.cantidadPosiciones = 1;
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

        while (!arbol_flor) {
            posicionXActual = posXCap;
            posicionYActual = posYCap;

            switch (lado) {
                case IZQUIERDA:
                    posicionXActual = posXCap - vista.cantidadPosiciones;
                    break;
                case DERECHA:
                    posicionXActual = posXCap + vista.cantidadPosiciones;
                    break;
                case ARRIBA:
                    posicionYActual = posYCap - vista.cantidadPosiciones;
                    break;
                case ABAJO:
                    posicionYActual = posYCap + vista.cantidadPosiciones;
                    break;
            }

            for(Point flor : this.getEnvironmentState().getPosicionesFlores()){
                if(flor.equals(new Point(posicionXActual, posicionYActual)))
                    vista.posicionFlores.add(flor);
            }


            switch (escenario.getPosicionCelda(posicionXActual, posicionYActual)) {
                /* puedo seguir avanzando, está vacío */
                case VACIA:
                case CAPERUCITA:
                    vista.cantidadPosiciones++;
                    break;
                /* hay un arbol, hasta acá nomás se puede llegar */
                case ARBOL:
                    vista.posicionArbol.setLocation(posicionXActual, posicionYActual);
                    arbol_flor = true;
                    break;
                /* hay un dulce, lo tengo que juntar y puedo seguir avanzando */
                case DULCE:
                    vista.cantidadPosiciones++;
                    vista.posicionDulces.add(new Point(posicionXActual, posicionYActual));
                    break;
                /* está el lobo, no debería moverse en esta dirección */
                case LOBO://TODO debería cortar con el lobo o sigue viendo hasta el árbol igual?
                    vista.cantidadPosiciones++;
                    vista.posicionLobo.setLocation(posicionXActual, posicionYActual);
                    break;
                /* está el camino de flores, la meta */
                case FLORES:
                    //vista.posicionFlores.add(new Point(posicionXActual, posicionYActual));
                    if (posicionYActual == Escenario.LIMITE_ABAJO) {
                        // Llegó al borde del mapa
                        arbol_flor = true;
                    } else {
                        vista.cantidadPosiciones++;
                    }
                    break;
            }

        }

        vista.cantidadPosiciones--;  //Puesto que viene inicializado en 1, debe restarse el movimiento adicional
        return vista;
    }

    /**
     * Retorna lo que haya en las celdas.
     * Si hay más de un objeto, lo elige con la siguiente prioridad:
     * - LOBO
     * - FLORES
     * - DULCES
     * - ÁRBOL
     */
    private EstadoCelda getContenidoEnVista(VistaCaperucita vistaCaperucita) {
        // TODO Prioridades ver si cambiamos primero dulce que flores - edit (milton) le cambie la prioridad, ver si gusta o no
        if (!vistaCaperucita.posicionLobo.equals(UNKNOWN))
            return EstadoCelda.LOBO;
        if (!vistaCaperucita.posicionDulces.isEmpty())
            return EstadoCelda.DULCE;
        if (!vistaCaperucita.posicionFlores.isEmpty())
            return EstadoCelda.FLORES;
        else
            return EstadoCelda.ARBOL;
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


}
