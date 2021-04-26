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

    private Escenario escenario;
    private final List<Point> posicionesDulces;
    private Point posicionCaperucita;
    private Point posicionLobo;

    public EstadoAmbiente() {
        escenario = new Escenario();
        posicionCaperucita = new Point();
        posicionLobo = new Point();
        posicionesDulces = new ArrayList<Point>();
        this.initState();
    }

    @Override
    public void initState() {
        /* TODO descomentar
        int numeroEscenario = getRandomNumber(1, 3);
        getEscenario().generarEscenario(numeroEscenario);

        int x;
        int y;
        //--------- POSICIÓN CAPERUCITA ---------
        boolean hayPosicionCaperucita = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
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
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
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
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                getPosicionesDulces().add(new Point(x, y));
                getEscenario().setPosicionCelda(x, y, EstadoCelda.DULCE);
                cantFlores--;
            }
        } while (cantFlores > 0);
        */

        // TODO eliminar, es para pruebas fijas
        getEscenario().generarEscenario(2);

        setPosicionCaperucita(new Point(6, 3));
        getEscenario().setPosicionCelda(6, 3, EstadoCelda.CAPERUCITA);

        setPosicionLobo(new Point(6, 1));
        getEscenario().setPosicionCelda(6, 1, EstadoCelda.LOBO);

        getPosicionesDulces().add(new Point(5, 2));
        getEscenario().setPosicionCelda(5, 2, EstadoCelda.DULCE);

        getPosicionesDulces().add(new Point(8, 3));
        getEscenario().setPosicionCelda(8, 3, EstadoCelda.DULCE);

        getPosicionesDulces().add(new Point(6, 6));
        getEscenario().setPosicionCelda(6, 6, EstadoCelda.DULCE);
    }

    private int getRandomNumber(int min, int max) {
        int rango = max - min + 1;
        return (int) (Math.random() * rango) + min;
    }

    @Override
    public String toString() {
        return "\n ----------------------------------------------------" +
                escenario + Consola.textoColoreadoGreen("- Posición caperucita: " + Consola.celdaToString(posicionCaperucita)) +
                "\n" + Consola.textoColoreadoGreen("- Posición lobo: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoGreen("- Posición dulces: " + Consola.celdaToString(posicionesDulces)) +
                "\n ---------------------------------------------------- \n";
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

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public void setPosicionLobo(Point posicionLobo) {
        this.posicionLobo = posicionLobo;
    }

    public List<Point> getPosicionesDulces() {
        return posicionesDulces;
    }
}

