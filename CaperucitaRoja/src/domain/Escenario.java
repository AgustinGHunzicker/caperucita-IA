package domain;

import enumeration.Consola;
import enumeration.EstadoCelda;

import java.awt.*;
import java.util.HashSet;

public class Escenario {
    public static final int LIMITE_IZQUIERDA = 0;
    public static final int LIMITE_DERECHA = 13;
    public static final int LIMITE_ARRIBA = 0;
    public static final int LIMITE_ABAJO = 8;

    private String nombre;
    private EstadoCelda[][] celdas;

    public Escenario() {
        this.nombre = "";
        this.celdas = new EstadoCelda[LIMITE_DERECHA + 1][LIMITE_ABAJO + 1];
        //---------- CELDAS VACÍAS ----------
        for (int posHorizontal = LIMITE_IZQUIERDA; posHorizontal <= LIMITE_DERECHA; posHorizontal++) {
            for (int posVertical = LIMITE_ARRIBA; posVertical <= LIMITE_ABAJO; posVertical++) {
                this.celdas[posHorizontal][posVertical] = EstadoCelda.VACIA;
            }
        }
    }

    /**
     * Genera un tipo de escenario según el parámetro pasado como argumento
     */
    public void generarEscenario(int nroEsc) {
        this.nombre = "Escenario Nº " + nroEsc;

        //---------- ÁRBOLES ----------
        for (int posHorizontal = LIMITE_IZQUIERDA; posHorizontal <= LIMITE_DERECHA; posHorizontal++) {
            this.celdas[posHorizontal][LIMITE_ARRIBA] = EstadoCelda.ARBOL;
            this.celdas[posHorizontal][LIMITE_ABAJO] = EstadoCelda.ARBOL;
        }

        for (int posVertical = LIMITE_ARRIBA; posVertical <= LIMITE_ABAJO; posVertical++) {
            this.celdas[LIMITE_IZQUIERDA][posVertical] = EstadoCelda.ARBOL;
            this.celdas[LIMITE_DERECHA][posVertical] = EstadoCelda.ARBOL;
        }

        // generar escenarios predeterminados
        switch (nroEsc) {
            case 1: {
                //---------- FLORES ----------
                this.celdas[7][7] = EstadoCelda.FLORES;
                this.celdas[7][8] = EstadoCelda.FLORES;

                //---------- ÁRBOLES ----------
                for (int j = 1; j <= 7; j++) {
                    this.celdas[1][j] = EstadoCelda.ARBOL;
                    this.celdas[2][j] = EstadoCelda.ARBOL;
                }

                this.celdas[3][4] = EstadoCelda.ARBOL;
                this.celdas[4][2] = EstadoCelda.ARBOL;
                this.celdas[4][4] = EstadoCelda.ARBOL;
                this.celdas[4][5] = EstadoCelda.ARBOL;
                this.celdas[5][5] = EstadoCelda.ARBOL;
                this.celdas[5][6] = EstadoCelda.ARBOL;
                this.celdas[6][6] = EstadoCelda.ARBOL;
                this.celdas[6][7] = EstadoCelda.ARBOL;
                this.celdas[7][1] = EstadoCelda.ARBOL;
                this.celdas[7][6] = EstadoCelda.ARBOL;
                this.celdas[8][4] = EstadoCelda.ARBOL;
                this.celdas[9][3] = EstadoCelda.ARBOL;
                this.celdas[9][6] = EstadoCelda.ARBOL;
                this.celdas[11][1] = EstadoCelda.ARBOL;
                this.celdas[11][6] = EstadoCelda.ARBOL;
                this.celdas[11][7] = EstadoCelda.ARBOL;

                for (int j = 1; j <= 7; j++) {
                    this.celdas[12][j] = EstadoCelda.ARBOL;
                }
                break;
            }
            case 2: {
                //---------- FLORES ----------
                this.celdas[6][7] = EstadoCelda.FLORES;
                this.celdas[6][8] = EstadoCelda.FLORES;

                //---------- ÁRBOLES ----------
                for (int j = 1; j <= 7; j++) {
                    this.celdas[1][j] = EstadoCelda.ARBOL;
                    this.celdas[2][j] = EstadoCelda.ARBOL;
                }

                this.celdas[3][1] = EstadoCelda.ARBOL;
                this.celdas[3][2] = EstadoCelda.ARBOL;
                this.celdas[3][3] = EstadoCelda.ARBOL;
                this.celdas[3][7] = EstadoCelda.ARBOL;
                this.celdas[4][1] = EstadoCelda.ARBOL;
                this.celdas[4][3] = EstadoCelda.ARBOL;
                this.celdas[4][4] = EstadoCelda.ARBOL;
                this.celdas[4][7] = EstadoCelda.ARBOL;
                this.celdas[5][4] = EstadoCelda.ARBOL;
                this.celdas[5][5] = EstadoCelda.ARBOL;
                this.celdas[5][7] = EstadoCelda.ARBOL;
                this.celdas[7][1] = EstadoCelda.ARBOL;
                this.celdas[7][2] = EstadoCelda.ARBOL;
                this.celdas[7][4] = EstadoCelda.ARBOL;
                this.celdas[7][5] = EstadoCelda.ARBOL;
                this.celdas[7][7] = EstadoCelda.ARBOL;
                this.celdas[8][1] = EstadoCelda.ARBOL;
                this.celdas[8][2] = EstadoCelda.ARBOL;
                this.celdas[9][1] = EstadoCelda.ARBOL;
                this.celdas[9][4] = EstadoCelda.ARBOL;
                this.celdas[9][5] = EstadoCelda.ARBOL;
                this.celdas[9][6] = EstadoCelda.ARBOL;
                this.celdas[10][1] = EstadoCelda.ARBOL;
                this.celdas[10][2] = EstadoCelda.ARBOL;

                for (int j = 1; j <= 7; j++) {
                    this.celdas[11][j] = EstadoCelda.ARBOL;
                    this.celdas[12][j] = EstadoCelda.ARBOL;
                }
                break;

            }
            case 3: {
                //---------- FLORES ----------
                this.celdas[3][0] = EstadoCelda.FLORES;
                this.celdas[3][1] = EstadoCelda.FLORES;

                //---------- ÁRBOLES ----------
                for (int j = 1; j <= 7; j++) {
                    this.celdas[2][j] = EstadoCelda.ARBOL;
                    this.celdas[1][j] = EstadoCelda.ARBOL;
                }

                this.celdas[3][4] = EstadoCelda.ARBOL;
                this.celdas[3][7] = EstadoCelda.ARBOL;
                this.celdas[4][1] = EstadoCelda.ARBOL;
                this.celdas[4][2] = EstadoCelda.ARBOL;
                this.celdas[4][4] = EstadoCelda.ARBOL;
                this.celdas[4][7] = EstadoCelda.ARBOL;
                this.celdas[5][7] = EstadoCelda.ARBOL;
                this.celdas[6][2] = EstadoCelda.ARBOL;
                this.celdas[7][5] = EstadoCelda.ARBOL;
                this.celdas[7][6] = EstadoCelda.ARBOL;
                this.celdas[8][1] = EstadoCelda.ARBOL;
                this.celdas[8][2] = EstadoCelda.ARBOL;
                this.celdas[9][1] = EstadoCelda.ARBOL;
                this.celdas[9][2] = EstadoCelda.ARBOL;
                this.celdas[9][3] = EstadoCelda.ARBOL;
                this.celdas[9][5] = EstadoCelda.ARBOL;
                this.celdas[9][7] = EstadoCelda.ARBOL;
                this.celdas[10][1] = EstadoCelda.ARBOL;
                this.celdas[10][2] = EstadoCelda.ARBOL;
                this.celdas[10][6] = EstadoCelda.ARBOL;
                this.celdas[10][7] = EstadoCelda.ARBOL;
                this.celdas[11][1] = EstadoCelda.ARBOL;
                this.celdas[11][2] = EstadoCelda.ARBOL;
                this.celdas[11][3] = EstadoCelda.ARBOL;
                this.celdas[11][7] = EstadoCelda.ARBOL;

                for (int j = 1; j <= 7; j++) {
                    this.celdas[12][j] = EstadoCelda.ARBOL;
                }
                break;
            }
        }
    }

    public HashSet<Point> getFlores() {
        HashSet<Point> flores = new HashSet<>();

        for (int i = 0; i <= LIMITE_DERECHA; i++) {
            for (int j = 0; j <= LIMITE_ABAJO; j++) {
                if (this.celdas[i][j].equals(EstadoCelda.FLORES)) {
                    flores.add(new Point(i, j));
                }
            }
        }

        return flores;
    }

    /**
     * Imprime la matriz del escenario, representando el estado de las celdas como emojis.
     */
    @Override
    public String toString() {
        final String vacio = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.EMOJI_EMPTY + Consola.ANSI_RESET_BACKGROUND;
        final String arbol = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.ANSI_GREEN + Consola.EMOJI_TREE + Consola.ANSI_GREEN + Consola.ANSI_RESET_BACKGROUND;
        final String caperucita = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.ANSI_RED + Consola.EMOJI_LITTLE_HOOD + Consola.ANSI_RED + Consola.ANSI_RESET_BACKGROUND;
        final String lobo = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.ANSI_PURPLE + Consola.EMOJI_WOLF + Consola.ANSI_PURPLE + Consola.ANSI_RESET_BACKGROUND;
        final String dulce = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.ANSI_CYAN + Consola.EMOJI_CANDY + Consola.ANSI_CYAN + Consola.ANSI_RESET_BACKGROUND;
        final String flor = "" + Consola.ANSI_BLACK_BACKGROUND + Consola.ANSI_YELLOW + Consola.EMOJI_FLOWER + Consola.ANSI_BLACK + Consola.ANSI_YELLOW + Consola.ANSI_RESET_BACKGROUND;

        StringBuilder escenario = new StringBuilder();
        escenario.append("\n");
        if (!this.nombre.equals(""))
            escenario.append(Consola.textoColoreadoGreen(" --- " + this.nombre + " ---")).append("\n");

        for (int movVertical = LIMITE_ARRIBA; movVertical <= LIMITE_ABAJO; movVertical++) {
            escenario.append(movVertical);

            for (int movHorizontal = LIMITE_IZQUIERDA; movHorizontal <= LIMITE_DERECHA; movHorizontal++) {
                switch (this.celdas[movHorizontal][movVertical]) {
                    case VACIA:
                        escenario.append(vacio);
                        break;
                    case ARBOL:
                        escenario.append(arbol);
                        break;
                    case CAPERUCITA:
                        escenario.append(caperucita);
                        break;
                    case LOBO:
                        escenario.append(lobo);
                        break;
                    case DULCE:
                        escenario.append(dulce);
                        break;
                    case FLORES:
                        escenario.append(flor);
                        break;
                }
            }
            escenario.append("\n");
        }

        return escenario.toString();
    }

    public Escenario clone() {
        Escenario newEscenario = new Escenario();
        newEscenario.setNombre(this.getNombre());
        newEscenario.setCeldas(this.getCeldas());
        return newEscenario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoCelda[][] getCeldas() {
        return this.celdas;
    }

    public void setCeldas(EstadoCelda[][] celdas) {
        this.celdas = celdas;
    }

    public EstadoCelda getPosicionCelda(int x, int y) {
        return this.celdas[x][y];
    }

    public void setPosicionCelda(int x, int y, EstadoCelda objetoCasillero) {
        this.celdas[x][y] = objetoCasillero;
    }
}
