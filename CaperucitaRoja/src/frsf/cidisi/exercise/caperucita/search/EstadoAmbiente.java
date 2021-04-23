package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the real world state.
 */
public class EstadoAmbiente extends EnvironmentState {

    private final Escenario escenario;
    private final List<Point> posicionesDulces;
    private Point posicionCaperucita;
    private Point posicionLobo;

    public EstadoAmbiente() {
        escenario = new Escenario();
        posicionCaperucita = new Point();
        posicionLobo = new Point();
        posicionesDulces = new ArrayList<>();

        this.initState();
    }

    @Override
    public void initState() {
        int numeroEscenario = getRandomNumber(1, 3);
        getEscenario().generarEscenario(numeroEscenario);

        int x;
        int y;

        //--------- POSICIÓN CAPERUCITA ---------
        boolean hayPosicionCaperucita = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ARRIBA, Escenario.LIMITE_ABAJO);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                setPosicionCaperucita(new Point(x, y));
                getEscenario().setPosicionCelda(x, y, EstadoCelda.CAPERUCITA);
                hayPosicionCaperucita = true;
            }

        } while (!hayPosicionCaperucita);

        //--------- POSICIÓN LOBO ---------
        boolean hayPosicionLobo = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ARRIBA, Escenario.LIMITE_ABAJO);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                setPosicionLobo(new Point(x, y));
                getEscenario().setPosicionCelda(x, y, EstadoCelda.LOBO);
                hayPosicionLobo = true;
            }
        } while (!hayPosicionLobo);

        //--------- POSICIÓN DULCES ---------
        int cantFlores = 3;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ARRIBA, Escenario.LIMITE_ABAJO);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                getPosicionesDulces().add(new Point(x, y));
                getEscenario().setPosicionCelda(x, y, EstadoCelda.DULCE);
                cantFlores--;
            }
        } while (cantFlores > 0);

        System.out.println(getEscenario()); //TODO quitar
    }

    private int getRandomNumber(int min, int max) {
        int rango = max - min + 1;
        return (int) (Math.random() * rango) + min;
    }

    @Override
    public String toString() {
        return escenario + Consola.textoColoreado("- Posición caperucita: " + Consola.celdaToString(posicionCaperucita)) +
                "\n" + Consola.textoColoreado("- Posición lobo: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreado("- Posición dulces: " + Consola.celdaToString(posicionesDulces)) + "\n";
    }

    public Point getPosicionCaperucita() {
        return posicionCaperucita;
    }

    public void setPosicionCaperucita(Point posicionCap) {
        posicionCaperucita = posicionCap;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setPosicionLobo(Point posicionLobo) {
        this.posicionLobo = posicionLobo;
    }

    public List<Point> getPosicionesDulces() {
        return posicionesDulces;
    }
}

