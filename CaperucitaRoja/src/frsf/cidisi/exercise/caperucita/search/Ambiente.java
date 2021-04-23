package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;
import enumeration.EstadoCelda;
import enumeration.TipoLado;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;
import java.util.HashSet;

public class Ambiente extends Environment {
    public final static Point UNKNOWN = new Point(-1, -1);

    public Ambiente() {
        this.environmentState = new EstadoAmbiente();
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    @Override
    public CaperucitaPerception getPercept() {
        //Recuperamos el estado del escenario en cuestión
        Escenario escenario = this.getEnvironmentState().getEscenario();

        //Primero chequeamos donde está caperucita
        int posXCap = (int) this.getEnvironmentState().getPosicionCaperucita().getX();
        int posYCap = (int) this.getEnvironmentState().getPosicionCaperucita().getY();

        //Creamos la percepción que el agente obtendrá, a partir del estado actual del ambiente
        CaperucitaPerception perception = new CaperucitaPerception();

        //Se mira hacia las cuatro direcciones
        VistaCaperucita vistaIzquierda = this.verLado(TipoLado.IZQUIERDA, escenario, posXCap, posYCap);
        VistaCaperucita vistaDerecha = this.verLado(TipoLado.DERECHA, escenario, posXCap, posYCap);
        VistaCaperucita vistaArriba = this.verLado(TipoLado.ARRIBA, escenario, posXCap, posYCap);
        VistaCaperucita vistaAbajo = this.verLado(TipoLado.ABAJO, escenario, posXCap, posYCap);

        //Se carga la bandera de jerarquía de posicionamiento de objetos en cada dirección
        perception.setIzquierdaPercepcion(this.getContenidoEnVista(vistaIzquierda));
        perception.setDerechaPercepcion(this.getContenidoEnVista(vistaDerecha));
        perception.setArribaPerception(this.getContenidoEnVista(vistaArriba));
        perception.setAbajoPerception(this.getContenidoEnVista(vistaAbajo));

        //Se carga los movimientos permitidos en cada dirección
        perception.setCantMovimientosIzquierda(vistaIzquierda.cantidadPosiciones);
        perception.setCantMovimientosDerecha(vistaDerecha.cantidadPosiciones);
        perception.setCantMovimientosArriba(vistaArriba.cantidadPosiciones);
        perception.setCantMovimientosAbajo(vistaAbajo.cantidadPosiciones);

        //Se carga las posición de las flores, si es que las haya visto
        perception.setFloresPerception(this.floresHay(vistaIzquierda.posicionFlores, vistaDerecha.posicionFlores, vistaArriba.posicionFlores, vistaAbajo.posicionFlores));

        //Se carga las posición del lobo, si es que lo vio
        perception.setPosicionLobo(this.loboEsta(vistaIzquierda.posicionLobo, vistaDerecha.posicionLobo, vistaArriba.posicionLobo, vistaAbajo.posicionLobo));

        //Se pasa la ubicación actual de caperucita
        perception.setPosicionActual(new Point(posXCap, posYCap));

        //Se carga las posición de los dulces, si hubiese
        HashSet<Point> dulces = new HashSet<>();
        dulces.addAll(vistaIzquierda.posicionDulces);
        dulces.addAll(vistaDerecha.posicionDulces);
        dulces.addAll(vistaArriba.posicionDulces);
        dulces.addAll(vistaAbajo.posicionDulces);
        perception.setDulcesPerception(dulces);

        //Se retorna la percepción
        return perception;
    }

    /**
     * Clase que funciona como estructura de vista de caperucita hacia una dirección en particular
     */
    private static class VistaCaperucita {
        protected Point posicionLobo;
        protected Point posicionFlores;
        protected HashSet<Point> posicionDulces;
        protected int cantidadPosiciones;

        public VistaCaperucita() {
            this.posicionLobo = UNKNOWN;
            this.posicionFlores = UNKNOWN;
            this.posicionDulces = new HashSet<>();
            this.cantidadPosiciones = 1;
        }
    }

    /**
     * Devuelve los objetos encontrados en linea horizontal al lado indicado.
     * Realiza la búsqueda hasta encontrar un árbol o un lobo.
     */
    private VistaCaperucita verLado(TipoLado lado, Escenario escenario, int posXCap, int posYCap) {
        VistaCaperucita vista = new VistaCaperucita();
        boolean arbol = false;

        int posicionXActual;
        int posicionYActual;

        while (!arbol && vista.posicionLobo.equals(UNKNOWN)) {
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

            switch (escenario.getPosicionCelda(posicionXActual, posicionYActual)) {
                //puedo seguir avanzando, está vacío
                case VACIA:
                    vista.cantidadPosiciones++;
                    break;
                //hay un arbol, hasta acá nomás se puede llegar
                case ARBOL:
                    arbol = true;
                    break;
                //hay un dulce, lo tengo que juntar y puedo seguir avanzando
                case DULCE:
                    Point dulce = new Point(posicionXActual, posicionYActual);
                    vista.posicionDulces.add(dulce);
                    vista.cantidadPosiciones++;
                    break;
                //está el lobo, no debería moverse en esta dirección
                case LOBO:
                    vista.posicionLobo.setLocation(posicionXActual, posicionYActual);
                    break;
                //Está el camino de flores, la meta
                case FLORES:
                    vista.posicionFlores.setLocation(posicionXActual, posicionYActual);
                    break;
            }
        }

        vista.cantidadPosiciones--;  //Puesto que viene inicializado en 1, debe restarse el movimiento adicional
        System.out.println(vista); // TODO quitar
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
        // TODO Prioridades ver si cambiamos primero dulce que flores
        if (!vistaCaperucita.posicionLobo.equals(UNKNOWN))
            return EstadoCelda.LOBO;
        if (!vistaCaperucita.posicionFlores.equals(UNKNOWN))
            return EstadoCelda.FLORES;
        if (!vistaCaperucita.posicionDulces.isEmpty())
            return EstadoCelda.DULCE;
        else
            return EstadoCelda.ARBOL;
    }

    /*
     * Devuelve la posicion del lobo si está a la vista en alguno de los lados,
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

    /*
     * Devuelve la posicion de las flores si hay a la vista en alguno de los lados,
     * en caso contrario, devuelve un fuera de rango - "pointOutOfRange"
     */
    private Point floresHay(Point celdaIzquierda, Point celdaDerecha, Point celdaArriba, Point celdaAbajo) {
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

    /**
     * Si caperucita no tiene mas vidas, entonces su objetivo falló
     */
    public boolean agentFailed(AgentState state) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) state;
        return estadoCaperucita.getVidasRestantes() <= 0;
    }
}
