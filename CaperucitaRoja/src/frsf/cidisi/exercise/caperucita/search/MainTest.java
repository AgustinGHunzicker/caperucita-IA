package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;

public class MainTest {
    public static void main(String[] args) {
        Escenario e = new Escenario();

        e.generarEscenario(1);
        e.imprimirEscenario();
        e.generarEscenario(2);
        e.imprimirEscenario();
        e.generarEscenario(3);
        e.imprimirEscenario();
    }
}
