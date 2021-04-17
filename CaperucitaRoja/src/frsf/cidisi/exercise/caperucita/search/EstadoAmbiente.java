package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import domain.Escenario;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class EstadoAmbiente extends EnvironmentState {
	
    private Escenario escenarioJuego;
    private Point posicionCaperucita;
    private Point posicionLobo;
    private Point posicionFlores;
    private List<Point> posicionesDulces;
	
    public EstadoAmbiente() {
        escenarioJuego = new Escenario();
    	posicionCaperucita = new Point();
        posicionLobo = new Point();
        posicionFlores = new Point();
        posicionesDulces = new ArrayList<Point>();
    	
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

    	//El estado inicial del estado del Ambiente; el escenario de entrada
    	
    	//sorteo del escenario
    	int nroEsc = getRandomN(1,3);
    	escenarioJuego.generarEscenario(nroEsc);
    	
    	//se setea en el escenario la posición de caperucita
    	boolean repetido = true;
    	int x;
    	int y;
        do {
        	x = getRandomN(2,13);
        	y = getRandomN(2,8);
        	if (escenarioJuego.getPosicionXY(x, y) == 0) {
        		posicionCaperucita = new Point(x, y);
        		escenarioJuego.setPosicionXY(x, y, 3);
        		repetido = false;
        	}
        } while(repetido);
        
        //se setea en el escenario la posición del lobo
        repetido = true;
        do {
        	x = getRandomN(2,13);
        	y = getRandomN(2,8);
        	if (escenarioJuego.getPosicionXY(x, y) == 0) {
        		posicionLobo = new Point(x, y);
        		escenarioJuego.setPosicionXY(x, y, 4);
        		repetido = false;
        	}
        } while(repetido);
        
        //se setea las posiciones de los dulces
        int cantFlores = 3;
        Point posicionDulce = new Point();
        do {
            repetido = true;
        	x = getRandomN(2,13);
        	y = getRandomN(2,8);
        	if (escenarioJuego.getPosicionXY(x, y) == 0) {
        		posicionDulce.setLocation(x, y);
        		posicionesDulces.add(posicionDulce);
        		escenarioJuego.setPosicionXY(x, y, 2);
        		repetido = false;
        		cantFlores--;
        	}
        } while(repetido || cantFlores > 0);        
    }
    
    private int getRandomN(int min, int max) {
    	//range
    	int rango = max - min + 1;
    	int rand = (int)(Math.random() * rango) + min;
    	return rand;
    }    
    
    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
    	
        String str = "";
        
        return str;
    }
    
     public Point getposicionCaperucita(){
        return posicionCaperucita;
     }
     
     public void setPosicionCaperucita(Point posicionCap) {
    	 posicionCaperucita = posicionCap;
     }
     
     public Escenario getEscenario() {
    	 return escenarioJuego;
     }
}

