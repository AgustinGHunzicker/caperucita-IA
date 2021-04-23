package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;

public class MainTest {
    public static void main(String[] args) {
        Escenario e = new Escenario();

        e.generarEscenario(1);
        System.out.println(e);
        e.generarEscenario(2);
        System.out.println(e.toString());
        e.generarEscenario(3);
        System.out.println(e);
    }
}
