package search;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.*;
import java.util.HashSet;

/**
 * This class represents the real world state.
 */
public class EstadoAmbiente extends EnvironmentState {
    private final static Point UNKNOWN = new Point(-1, -1);

    private EstadoAmbiente estadoInicialAmbiente;

    private Escenario escenario;
    private final HashSet<Point> posicionesDulces;
    private final HashSet<Point> posicionesFlores;
    private Point posicionCaperucita;
    private Point posicionLobo;

    public EstadoAmbiente() {
        escenario = new Escenario();
        posicionCaperucita = UNKNOWN;
        posicionLobo = UNKNOWN;
        posicionesDulces = new HashSet<>();
        posicionesFlores = new HashSet<>();
        this.initState();
    }

    @Override
    public void initState() {
        Point caperucita = null;
        Point lobo = null;
        Point dulce1 = null;
        Point dulce2 = null;
        Point dulce3 = null;


        //TODO descomentar
       /* int numeroEscenario = getRandomNumber(1, 3);
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
                caperucita = new Point(x, y);
                hayPosicionCaperucita = true;
            }

        } while (!hayPosicionCaperucita);

        //--------- POSICIÓN LOBO ---------
        boolean hayPosicionLobo = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                lobo = new Point(x, y);
                hayPosicionLobo = true;
            }
        } while (!hayPosicionLobo);

        //--------- POSICIÓN DULCES ---------
        boolean hayFlor = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                dulce1 = new Point(x, y);
                hayFlor = true;
            }
        } while (!hayFlor);

        hayFlor = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                dulce2 = new Point(x, y);
                hayFlor = true;
            }
        } while (!hayFlor);

        hayFlor = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                dulce3 = new Point(x, y);
                hayFlor = true;
            }
        } while (!hayFlor);*/


/*
        //Escenario 1
        getEscenario().generarEscenario(2);
        caperucita = new Point(9, 2);
        //caperucita = new Point(6, 1);
        lobo = new Point(4, 6);
        dulce1 = new Point(6, 5);
        //dulce2 = new Point(6, 3);
        dulce2 = new Point(6, 3);
        dulce3 = new Point(9, 5);
        */
        // Escenario 2
        getEscenario().generarEscenario(1);
        escenario.setPosicionCelda(3, 4, EstadoCelda.VACIA);
        escenario.setPosicionCelda(5, 7, EstadoCelda.ARBOL);
        escenario.setPosicionCelda(4, 7, EstadoCelda.ARBOL);
        escenario.setPosicionCelda(4, 6, EstadoCelda.ARBOL);
        escenario.setPosicionCelda(7, 1, EstadoCelda.VACIA);

        caperucita = new Point(3, 7);
        //caperucita = new Point(6, 1);
        lobo = new Point(6, 1);
        dulce1 = new Point(6, 5);
        //dulce2 = new Point(6, 3);
        dulce2 = new Point(6, 3);
        dulce3 = new Point(9, 5);

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

        getPosicionesFlores().addAll(escenario.getFlores());
    }

    public void updateWolfPosition() {
        Point newPosition = new Point();
        int x;
        int y;
        boolean hayLobo = false;
        do {
            x = getRandomNumber(Escenario.LIMITE_IZQUIERDA, Escenario.LIMITE_DERECHA);
            y = getRandomNumber(Escenario.LIMITE_ABAJO, Escenario.LIMITE_ARRIBA);
            if (getEscenario().getPosicionCelda(x, y) == EstadoCelda.VACIA) {
                newPosition = new Point(x, y);
                hayLobo = true;
            }
        } while (!hayLobo);
        if(!this.posicionLobo.equals(UNKNOWN) && !this.posicionLobo.equals(new Point()))
            escenario.setPosicionCelda(this.posicionLobo.x, this.posicionLobo.y, EstadoCelda.VACIA);
        this.setPosicionLobo(newPosition);
        escenario.setPosicionCelda(newPosition.x, newPosition.y, EstadoCelda.LOBO);
    }

    public void disabledWolfPosition(){
        escenario.setPosicionCelda(this.posicionLobo.x, this.posicionLobo.y, EstadoCelda.VACIA);
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

    public HashSet<Point> getPosicionesFlores() {
        return posicionesFlores;
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

    public HashSet<Point> getPosicionesDulces() {
        return posicionesDulces;
    }

    public void actualizarPosicionCaperucita(int x, int y) {
        escenario.setPosicionCelda(x, y, EstadoCelda.CAPERUCITA);
        posicionCaperucita.setLocation(x, y);
    }

    public EstadoAmbiente getEstadoInicialAmbiente() {
        return estadoInicialAmbiente;
    }

    public void setEstadoInicialAmbiente(EstadoAmbiente estadoInicialAmbiente) {
        this.estadoInicialAmbiente = estadoInicialAmbiente;
    }

    public void volverEstadoInicial(){
        EstadoAmbiente inicio = this.getEstadoInicialAmbiente();
        this.setEscenario(inicio.escenario);
        this.setPosicionCaperucita(inicio.getPosicionCaperucita());
    }


    public Point getPosicionLobo() {
        return posicionLobo;
    }
}

