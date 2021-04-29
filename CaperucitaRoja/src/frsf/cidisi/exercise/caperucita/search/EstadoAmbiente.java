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
    private final static Point UNKNOWN = new Point(-1, -1);


    private Escenario escenario;
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
        //TODO descomentar
        /*int numeroEscenario = getRandomNumber(1, 3);
        getEscenario().generarEscenario(numeroEscenario);
       // getEscenario().generarEscenario(2);

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

        Point caperucita = new Point(4, 2);
        Point lobo = new Point(4, 6);
        Point dulce1 = new Point(8, 3);
        Point dulce2 = new Point(6, 3);
        Point dulce3 = new Point(9, 5);

        setPosicionCaperucita(caperucita);
        setPosicionLobo(lobo);
        getPosicionesDulces().add(dulce1);
        getPosicionesDulces().add(dulce2);
        getPosicionesDulces().add(dulce3);

        escenario.setPosicionCelda(caperucita.x, caperucita.y, EstadoCelda.CAPERUCITA);
        escenario.setPosicionCelda(lobo.x, lobo.y, EstadoCelda.LOBO);
        escenario.setPosicionCelda(dulce1.x, dulce1.y, EstadoCelda.DULCE);
        escenario.setPosicionCelda(dulce2.x, dulce2.y, EstadoCelda.DULCE);
        escenario.setPosicionCelda(dulce3.x, dulce3.y, EstadoCelda.DULCE);
    }

    private int getRandomNumber(int min, int max) {
        int rango = max - min + 1;
        return (int) (Math.random() * rango) + min;
    }

    @Override
    public String toString() {
        return escenario + Consola.textoColoreadoGreen("- Posición caperucita: " + Consola.celdaToString(posicionCaperucita)) +
                "\n" + Consola.textoColoreadoGreen("- Posición lobo: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoGreen("- Posición dulces: " + Consola.celdaToString(posicionesDulces)) +
                "\n ---------------------------------------------------- \n";
    }

    public Point getPosicionCaperucita() {
        return posicionCaperucita;
    }

    public void setPosicionCaperucita(Point posicionCap) {
        if (!posicionCaperucita.equals(UNKNOWN))
            escenario.setPosicionCelda(posicionCaperucita.x, posicionCaperucita.y, EstadoCelda.VACIA);

        if (!posicionCap.equals(UNKNOWN)) {
            escenario.setPosicionCelda(posicionCap.x, posicionCap.y, EstadoCelda.CAPERUCITA);
            posicionCaperucita = posicionCap;
        }
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

    public void actualizarPosicionCaperucita(int x, int y) {
        escenario.setPosicionCelda(x, y, EstadoCelda.CAPERUCITA);
        posicionCaperucita.setLocation(x, y);
    }
}

