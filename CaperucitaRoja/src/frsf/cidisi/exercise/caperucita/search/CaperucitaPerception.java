package frsf.cidisi.exercise.caperucita.search;

import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.awt.*;
import java.util.HashSet;

public class CaperucitaPerception extends Perception {
    private final static Point UNKNOWN = new Point(-1, -1);

    //Bandera que indican que hay en esa dirección
    private EstadoCelda izquierdaPercepcion;
    private EstadoCelda derechaPercepcion;
    private EstadoCelda arribaPercepcion;
    private EstadoCelda abajoPercepcion;

    //Cantidad de movimiento que puede hacer en esa dirección
    private int cantMovimientosIzquierda;
    private int cantMovimientosDerecha;
    private int cantMovimientosArriba;
    private int cantMovimientosAbajo;

    //Posiciones exactas en el escenario percibido
    private Point posicionFlores;
    private Point posicionLobo;
    private Point posicionActual; //
    private HashSet<Point> posicionesDulces;
    private HashSet<Point> posicionesArboles;

    public CaperucitaPerception() {
        izquierdaPercepcion = EstadoCelda.VACIA;
        derechaPercepcion = EstadoCelda.VACIA;
        arribaPercepcion = EstadoCelda.VACIA;
        abajoPercepcion = EstadoCelda.VACIA;
        cantMovimientosIzquierda = 0;
        cantMovimientosDerecha = 0;
        cantMovimientosArriba = 0;
        cantMovimientosAbajo = 0;
        posicionFlores = UNKNOWN;
        posicionLobo = UNKNOWN;
        posicionesDulces = new HashSet<Point>();
        posicionesArboles = new HashSet<Point>();
    }

    public CaperucitaPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
        // TODO no se debería usar?
        //TODO para mi lo que se hizo en ambiente debería ir acá
        Caperucita agent = (Caperucita) agentIn;
        Ambiente environment = (Ambiente) environmentIn;
        EstadoAmbiente environmentState = environment.getEnvironmentState();
        //Acá se crea la percepción inicial del agente
        Point position = environmentState.getPosicionCaperucita();
    }

    @Override
    public String toString() {
        return "\n" + Consola.textoColoreadoYellow("- Posición CAPERUCITA: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoYellow("- Posición LOBO: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoYellow("- Posición DULCES: " + Consola.celdaToString(posicionesDulces)) +
                "\n" + Consola.textoColoreadoYellow("- Posición FLORES: " + Consola.celdaToString(posicionFlores)) +
                "\n" + Consola.textoColoreadoYellow("- Posición ÁRBOLES: " + Consola.celdaToString(posicionesArboles)) +
                "\n" + Consola.textoColoreadoYellow("- IZQUIERDA:   Percepción - " + izquierdaPercepcion + ". Movimientos disponibles = " + cantMovimientosIzquierda) +
                "\n" + Consola.textoColoreadoYellow("- DERECHA:     Percepción - " + derechaPercepcion + ". Movimientos disponibles = " + cantMovimientosDerecha) +
                "\n" + Consola.textoColoreadoYellow("- ARRIBA:      Percepción - " + arribaPercepcion + ". Movimientos disponibles = " + cantMovimientosArriba) +
                "\n" + Consola.textoColoreadoYellow("- ABAJO:       Percepción - " + abajoPercepcion + ". Movimientos disponibles = " + cantMovimientosAbajo) +
                "\n ---------------------------------------------------- \n";
    }

    public void setArribaPerception(EstadoCelda arrPerception) {
        this.arribaPercepcion = arrPerception;
    }

    public void setAbajoPerception(EstadoCelda abaPerception) {
        this.abajoPercepcion = abaPerception;
    }

    public EstadoCelda getIzquierdaPercepcion() {
        return izquierdaPercepcion;
    }

    public void setIzquierdaPercepcion(EstadoCelda izqPerception) {
        this.izquierdaPercepcion = izqPerception;
    }

    public EstadoCelda getDerechaPercepcion() {
        return derechaPercepcion;
    }

    public void setDerechaPercepcion(EstadoCelda derPerception) {
        this.derechaPercepcion = derPerception;
    }

    public EstadoCelda getArribaPercepcion() {
        return arribaPercepcion;
    }

    public EstadoCelda getAbajoPercepcion() {
        return abajoPercepcion;
    }

    public int getCantMovimientosIzquierda() {
        return cantMovimientosIzquierda;
    }

    public void setCantMovimientosIzquierda(int cantMovimientosIzquierda) {
        this.cantMovimientosIzquierda = cantMovimientosIzquierda;
    }

    public int getCantMovimientosDerecha() {
        return cantMovimientosDerecha;
    }

    public void setCantMovimientosDerecha(int cantMovimientosDerecha) {
        this.cantMovimientosDerecha = cantMovimientosDerecha;
    }

    public int getCantMovimientosArriba() {
        return cantMovimientosArriba;
    }

    public void setCantMovimientosArriba(int cantMovimientosArriba) {
        this.cantMovimientosArriba = cantMovimientosArriba;
    }

    public int getCantMovimientosAbajo() {
        return cantMovimientosAbajo;
    }

    public void setCantMovimientosAbajo(int cantMovimientosAbajo) {
        this.cantMovimientosAbajo = cantMovimientosAbajo;
    }

    public Point getPosicionLobo() {
        return posicionLobo;
    }

    public void setPosicionLobo(Point posicionLobo) {
        this.posicionLobo = posicionLobo;
    }

    public HashSet<Point> getPosicionesArboles() {
        return posicionesArboles;
    }

    public void setPosicionesArboles(HashSet<Point> posicionesArboles) {
        this.posicionesArboles = posicionesArboles;
    }

    public Point getPosicionFlores() {
        return posicionFlores;
    }

    public void setPosicionFlores(Point posFlores) {
        this.posicionFlores = posFlores;
    }

    public HashSet<Point> getPosicionesDulces() {
        return posicionesDulces;
    }

    public void setPosicionesDulces(HashSet<Point> dulces) {
        this.posicionesDulces = dulces;
    }

    public Point getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Point posicionActual) {
        this.posicionActual = posicionActual;
    }
}
