package search;

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
    private EstadoCelda percepcionCeldasIzquierda;
    private EstadoCelda percepcionCeldasDerecha;
    private EstadoCelda percepcionCeldasArriba;
    private EstadoCelda percepcionCeldasAbajo;

    //Cantidad de movimiento que puede hacer en esa dirección
    private int cantMovimientosIzquierda;
    private int cantMovimientosDerecha;
    private int cantMovimientosArriba;
    private int cantMovimientosAbajo;

    //Posiciones exactas en el escenario percibido
    private Point posicionLobo;
    private Point posicionActual;
    private final HashSet<Point> posicionFlores;
    private final HashSet<Point> posicionesDulces;
    private final HashSet<Point> posicionesArboles;

    public CaperucitaPerception() {
        percepcionCeldasIzquierda = EstadoCelda.VACIA;
        percepcionCeldasDerecha = EstadoCelda.VACIA;
        percepcionCeldasArriba = EstadoCelda.VACIA;
        percepcionCeldasAbajo = EstadoCelda.VACIA;
        cantMovimientosIzquierda = 0;
        cantMovimientosDerecha = 0;
        cantMovimientosArriba = 0;
        cantMovimientosAbajo = 0;
        posicionLobo = UNKNOWN;
        posicionFlores = new HashSet<>();
        posicionesDulces = new HashSet<>();
        posicionesArboles = new HashSet<>();
    }

    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    }

    @Override
    public String toString() {
        return "\n" + Consola.textoColoreadoYellow("- Posición CAPERUCITA: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoYellow("- Posición LOBO: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoYellow("- Posición DULCES: " + Consola.celdaToString(posicionesDulces)) +
                "\n" + Consola.textoColoreadoYellow("- Posiciones FLORES: " + Consola.celdaToString(posicionFlores)) +
                "\n" + Consola.textoColoreadoYellow("- Posiciones ÁRBOLES: " + Consola.celdaToString(posicionesArboles)) +
                "\n" + Consola.textoColoreadoYellow("- IZQUIERDA:   Percepción - " + percepcionCeldasIzquierda + ". Movimientos disponibles = " + cantMovimientosIzquierda) +
                "\n" + Consola.textoColoreadoYellow("- DERECHA:     Percepción - " + percepcionCeldasDerecha + ". Movimientos disponibles = " + cantMovimientosDerecha) +
                "\n" + Consola.textoColoreadoYellow("- ARRIBA:      Percepción - " + percepcionCeldasArriba + ". Movimientos disponibles = " + cantMovimientosArriba) +
                "\n" + Consola.textoColoreadoYellow("- ABAJO:       Percepción - " + percepcionCeldasAbajo + ". Movimientos disponibles = " + cantMovimientosAbajo) +
                "\n ---------------------------------------------------- \n";
    }

    public void setArribaPerception(EstadoCelda arrPerception) {
        this.percepcionCeldasArriba = arrPerception;
    }

    public void setAbajoPerception(EstadoCelda abaPerception) {
        this.percepcionCeldasAbajo = abaPerception;
    }

    public EstadoCelda getPercepcionCeldasIzquierda() {
        return percepcionCeldasIzquierda;
    }

    public void setPercepcionCeldasIzquierda(EstadoCelda izqPerception) {
        this.percepcionCeldasIzquierda = izqPerception;
    }

    public EstadoCelda getPercepcionCeldasDerecha() {
        return percepcionCeldasDerecha;
    }

    public void setPercepcionCeldasDerecha(EstadoCelda derPerception) {
        this.percepcionCeldasDerecha = derPerception;
    }

    public EstadoCelda getPercepcionCeldasArriba() {
        return percepcionCeldasArriba;
    }

    public EstadoCelda getPercepcionCeldasAbajo() {
        return percepcionCeldasAbajo;
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

    public void addPosicionArbol(Point posicionArbol) {
        if (!posicionArbol.equals(UNKNOWN))
            this.posicionesArboles.add(posicionArbol);
    }

    public HashSet<Point> getPosicionFlores() {
        return posicionFlores;
    }

    public void addPosicionFlores(HashSet<Point> posFlores) {
        this.posicionFlores.addAll(posFlores);
    }

    public HashSet<Point> getPosicionesDulces() {
        return posicionesDulces;
    }

    public void addPosicionesDulces(HashSet<Point> dulces) {
        this.posicionesDulces.addAll(dulces);
    }

    public Point getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(Point posicionActual) {
        this.posicionActual = posicionActual;
    }
}
