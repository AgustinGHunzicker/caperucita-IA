package search;

import enumeration.Consola;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

import java.awt.*;
import java.util.HashSet;

public class CaperucitaPerception extends Perception {
    private final static Point UNKNOWN = new Point(-1, -1);

    private Point posicionLobo;
    private Point posicionActual;
    private final HashSet<Point> posicionesDulces;

    public CaperucitaPerception() {
        posicionLobo = UNKNOWN;
        posicionesDulces = new HashSet<>();
    }

    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    }

    @Override
    public String toString() {
        return "\n" + Consola.textoColoreadoYellow("- Posición CAPERUCITA: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoYellow("- Posición LOBO: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoYellow("- Posición DULCES: " + Consola.celdaToString(posicionesDulces)) +
                "\n ---------------------------------------------------- \n";
    }

    public Point getPosicionLobo() {
        return posicionLobo;
    }

    public void setPosicionLobo(Point posicionLobo) {
        this.posicionLobo = posicionLobo;
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
