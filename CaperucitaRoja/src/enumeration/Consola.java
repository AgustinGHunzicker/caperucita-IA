package enumeration;

import frsf.cidisi.faia.agent.search.SearchAction;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

public enum Consola {
    ANSI_RESET_BACKGROUND("\u001B[0m"),
    ANSI_BLACK_BACKGROUND("\u001B[40m"),
    ANSI_RED_BACKGROUND("\u001B[41m"),
    ANSI_GREEN_BACKGROUND("\u001B[42m"),
    ANSI_YELLOW_BACKGROUND("\u001B[43m"),
    ANSI_BLUE_BACKGROUND("\u001B[44m"),
    ANSI_PURPLE_BACKGROUND("\u001B[45m"),
    ANSI_CYAN_BACKGROUND("\u001B[46m"),
    ANSI_WHITE_BACKGROUND("\u001B[47m"),

    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m"),
    ANSI_WHITE("\u001B[37m"),
    ANSI_ORANGE("\033[48;2;255;165;0m"),

    EMOJI_EMPTY("\uD83D\uDCA0"),
    EMOJI_TREE("\uD83C\uDF32"),
    EMOJI_LITTLE_HOOD("\uD83D\uDC69"),
    EMOJI_WOLF("\uD83D\uDC3A"),
    EMOJI_CANDY("\uD83C\uDF6C"),
    EMOJI_FLOWER("\uD83C\uDF39");

    private static boolean debug = true;

    private final String value;
    private static final Point UNKNOWN = new Point(-1,-1);

    Consola(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    public static String textoColoreadoGreen(String texto) {
        if(debug)
            return "" + ANSI_GREEN_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    public static String textoColoreadoCyan(String texto) {
        if(debug)
            return "" + ANSI_CYAN_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    public static String textoColoreadoYellow(String texto) {
        if(debug)
            return "" + ANSI_YELLOW_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    public static String textoColoreadoRed(String texto) {
        if(debug)
            return "" + ANSI_RED_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    public static String textoColoreadoWhite(String texto) {
        if(debug)
            return "" + ANSI_WHITE_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    public static String textoColoreadoPurple(String texto) {
        if(debug)
            return "" + ANSI_PURPLE_BACKGROUND + ANSI_BLACK + texto + ANSI_BLACK + " " + ANSI_RESET_BACKGROUND;
        return texto;
    }

    //nada que ver aca, pero era para centralizar
    public static String celdaToString(Point celda) {
        if (celda.equals(UNKNOWN)) {
            return "UNKNOWN";
        }
        return "[x:" + ((int) celda.getX()) + ", y:" + ((int) celda.getY()) + "]";
    }

    public static String celdaToString(List<Point> celdas) {
        if (celdas.isEmpty())
            return "UNKNOWN";

        StringBuilder texto = new StringBuilder();
        for (Point celda : celdas) {
            texto.append(celdaToString(celda)).append(", ");
        }
        return texto.toString();
    }

    public static String celdaToString(HashSet<Point> celdas) {
        if (celdas.isEmpty())
            return "UNKNOWN";

        StringBuilder texto = new StringBuilder();
        for (Point celda : celdas) {
            texto.append(celdaToString(celda)).append(", ");
        }
        texto.deleteCharAt(texto.length()-1);
        texto.deleteCharAt(texto.length()-1);
        return texto.toString();
    }

    public static void printExecution1(SearchAction action, Point punto){
        //System.out.println(Consola.textoColoreadoPurple("Probando " + action + " " + Consola.celdaToString(punto)));
    }

    public static void printExecution2(SearchAction action){
        //System.out.println(Consola.textoColoreadoPurple("Usando " + action));
    }
}
