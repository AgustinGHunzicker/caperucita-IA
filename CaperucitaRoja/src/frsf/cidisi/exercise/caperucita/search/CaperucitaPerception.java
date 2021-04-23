package frsf.cidisi.exercise.caperucita.search;

import enumeration.EstadoCelda;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class CaperucitaPerception extends Perception {
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

    // TODO Posiblemente necesitemos la cantidad de dulces en esa dirección

    public CaperucitaPerception() {
        izquierdaPercepcion = EstadoCelda.VACIA;
        derechaPercepcion = EstadoCelda.VACIA;
        arribaPercepcion = EstadoCelda.VACIA;
        abajoPercepcion = EstadoCelda.VACIA;
        cantMovimientosIzquierda = 0;
        cantMovimientosDerecha = 0;
        cantMovimientosArriba = 0;
        cantMovimientosAbajo = 0;
        posicionFlores = Ambiente.UNKNOWN;
        posicionLobo = Ambiente.UNKNOWN;
        posicionesDulces = new HashSet<>();
    }

    public CaperucitaPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
        Caperucita agent = (Caperucita) agentIn;
        Ambiente environment = (Ambiente) environmentIn;
        EstadoAmbiente environmentState = environment.getEnvironmentState();
        //Acá se crea la percepción inicial del agente
        Point position = environmentState.getPosicionCaperucita();
    }

    public void setIzquierdaPercepcion(EstadoCelda izqPerception) {
        this.izquierdaPercepcion = izqPerception;
    }

    public void setDerechaPercepcion(EstadoCelda derPerception) {
        this.derechaPercepcion = derPerception;
    }

    public void setArribaPerception(EstadoCelda arrPerception) {
        this.arribaPercepcion = arrPerception;
    }

    public void setAbajoPerception(EstadoCelda abaPerception) {
        this.abajoPercepcion = abaPerception;
    }

    public void setFloresPerception(Point flores) {




        this.posicionFlores = flores;
    }

    public void setFloresPerception(){

    }

    public void setDulcesPerception(HashSet<Point> dulces) {
        this.posicionesDulces = dulces;
    }


    public EstadoCelda getIzquierdaPercepcion() {
        return izquierdaPercepcion;
    }

    public EstadoCelda getDerechaPercepcion() {
        return derechaPercepcion;
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

    public Point getPosicionFlores() {
        return posicionFlores;
    }

    public Set<Point> getPosicionesDulces() {
        return posicionesDulces;
    }

    public Point getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Point posicionActual) {
        this.posicionActual = posicionActual;
    }

    //TODO agregar el toString
}
