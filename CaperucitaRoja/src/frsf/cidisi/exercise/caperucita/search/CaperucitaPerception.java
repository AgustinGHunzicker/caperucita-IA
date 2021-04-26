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
    private HashSet<Point>  posicionFlores;
    private Point posicionLobo;
    private Point posicionActual; //
    private HashSet<Point> posicionesDulces;
    private HashSet<Point> posicionesArboles;

    public CaperucitaPerception() {
        percepcionCeldasIzquierda = EstadoCelda.VACIA;
        percepcionCeldasDerecha = EstadoCelda.VACIA;
        percepcionCeldasArriba = EstadoCelda.VACIA;
        percepcionCeldasAbajo = EstadoCelda.VACIA;
        cantMovimientosIzquierda = 0;
        cantMovimientosDerecha = 0;
        cantMovimientosArriba = 0;
        cantMovimientosAbajo = 0;
        posicionFlores = new HashSet<Point>();
        posicionLobo = UNKNOWN;
        posicionesDulces = new HashSet<>();
        posicionesArboles = new HashSet<>();
    }

    public CaperucitaPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
        System.out.println(Consola.textoColoreadoYellow("ENTRO A INIT PERCEPCION"));
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

    public void setPosicionesArboles(HashSet<Point> posicionesArboles) {
        this.posicionesArboles = posicionesArboles;
    }

    public HashSet<Point>  getPosicionFlores() {
        return posicionFlores;
    }

    public void setPosicionFlores(HashSet<Point> posFlores) {
        this.posicionFlores = posFlores;
    }

    public void addPosicionFlores(Point posFlores) {
        this.posicionFlores.add(posFlores);
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
