package domain;

import enumeration.EstadoCelda;

public class Escenario {

    private String nombre;
    private EstadoCelda[][] celdas;

    public Escenario() {
        this.celdas = new EstadoCelda[14][9];
    }

    /**
     * Genera un tipo de escenario según el parámetro pasado como argumento
     */
    public void generarEscenario(int nroEsc) {
        this.nombre = "Escenario Nº"+nroEsc;
        this.celdas = new EstadoCelda[14][9];

        // BORDES escenario = árboles
        for (int i = 0; i < 14; i++) {
            this.celdas[i][0] = EstadoCelda.ARBOL;
            this.celdas[i][8] = EstadoCelda.ARBOL;
        }

        for (int j = 0; j < 9; j++) {
            this.celdas[0][j] = EstadoCelda.ARBOL;
            this.celdas[13][j] = EstadoCelda.ARBOL;
        }

        // casilleros vacíos
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 8; j++) {
                this.celdas[i][j] = EstadoCelda.VACIO;
            }
        }

        // generar escenarios predeterminados
        switch (nroEsc) {
            case 1: {
                // camino flores
                this.celdas[7][7] = EstadoCelda.FLORES;
                this.celdas[7][8] = EstadoCelda.FLORES;

                // árboles
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
                //camino flores
                this.celdas[6][7] = EstadoCelda.FLORES;
                this.celdas[6][8] = EstadoCelda.FLORES;

                //arboles
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
                //camino flores
                this.celdas[3][0] = EstadoCelda.FLORES;
                this.celdas[3][1] = EstadoCelda.FLORES;

                //arboles
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

    /** Imprime la matriz del escenario, representando el estado de las celdas como emojis. */
    public void imprimirEscenario() {
        final String ANSI_RESET_BACKGROUND = "\u001B[0m";
        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        final String ANSI_ORANGE = "\033[48;2;255;165;0m";

        final String EMOJI_EMPTY = "\uD83D\uDCA0";
        final String EMOJI_TREE = "\uD83C\uDF32";
        final String EMOJI_LITTLE_HOOD = "\uD83D\uDC69";
        final String EMOJI_WOLF = "\uD83D\uDC3A";
        final String EMOJI_CANDY = "\uD83C\uDF6C";
        final String EMOJI_FLOWER = "\uD83C\uDF39";

        final String vacio = ANSI_BLACK_BACKGROUND + EMOJI_EMPTY + ANSI_RESET_BACKGROUND;
        final String arbol = ANSI_BLACK_BACKGROUND + ANSI_GREEN + EMOJI_TREE + ANSI_GREEN + ANSI_RESET_BACKGROUND;
        final String caperucita = ANSI_BLACK_BACKGROUND + ANSI_RED + EMOJI_LITTLE_HOOD + ANSI_RED + ANSI_RESET_BACKGROUND;
        final String lobo = ANSI_BLACK_BACKGROUND + ANSI_PURPLE + EMOJI_WOLF + ANSI_PURPLE + ANSI_RESET_BACKGROUND;
        final String dulce = ANSI_BLACK_BACKGROUND + ANSI_CYAN + EMOJI_CANDY + ANSI_CYAN + ANSI_RESET_BACKGROUND;
        final String flor = ANSI_BLACK_BACKGROUND + ANSI_YELLOW + EMOJI_FLOWER + ANSI_BLACK +ANSI_YELLOW + ANSI_RESET_BACKGROUND;

        System.out.println(this.nombre);
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 14; i++) {
                switch (this.celdas[i][j]) {
                    case VACIO:
                        System.out.print(vacio);
                        break;
                    case ARBOL:
                        System.out.print(arbol);
                        break;
                    case CAPERUCITA:
                        System.out.print(caperucita);
                        break;
                    case LOBO:
                        System.out.print(lobo);
                        break;
                    case DULCE:
                        System.out.print(dulce);
                        break;
                    case FLORES:
                        System.out.print(flor);
                        break;
                }
            }

            System.out.println("");
        }

        System.out.println("");
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

    public EstadoCelda getPosicionXY(int x, int y) {
        return this.celdas[x][y];
    }

    public void setPosicionXY(int x, int y, EstadoCelda objetoCasillero) {
        this.celdas[x][y] = objetoCasillero;
    }

    public Escenario clone() {
        Escenario newEscenario = new Escenario();
        newEscenario.setNombre(this.getNombre());
        newEscenario.setCeldas(this.getCeldas());
        return newEscenario;
    }

    public String toString() {
        return nombre;
    }
}
