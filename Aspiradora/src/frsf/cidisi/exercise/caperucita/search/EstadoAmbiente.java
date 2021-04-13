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
	
    private List<Escenario> habitacionesSucias;
    private Escenario posicionAspiradora;
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
    	
        habitacionesSucias = new ArrayList<Escenario>();
    	posicionAspiradora = new Escenario();
    	
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

    	//El estado inicial del estado del Ambiente; el escenario de entrada
    	
    	for (int i = 1; i <= 14; i++) {
    		for (int j = 1; j <= 9; j++) {
    			escenarioJuego.setPosicionXY(i, j, 0);
    		}
    	}
    	
    	//se setea en el escenario la posición de caperucita
        posicionCaperucita = new Point(getRandomX(), getRandomY());
        escenarioJuego.setPosicionXY((int)(posicionCaperucita.getX()), (int)(posicionCaperucita.getY()), 3);
        
        //se setea en el escenario la posición del lobo
        do {
        	posicionLobo = new Point(getRandomX(), getRandomY()); //chequeo que no se pare encima de caperucita
        } while(posicionLobo.equals(posicionCaperucita));
        
        escenarioJuego.setPosicionXY((int)(posicionLobo.getX()), (int)(posicionLobo.getY()), 4);
        
        //TODO: hacer un algoritmo aleatorio para generar los arboles, los dulces y las flores
        
        //posicionesArboles
    	//posicionFlores = una posicion
        //posicionesDulces = posiciones
    }
    
    private int getRandomX() {
    	//range
    	//se considera una columna menos a cada lado porque en los bordes hay bosque siempre
    	int min = 2;
    	int max = 13;
    	int rango = max - min + 1;
    	int rand = (int)(Math.random() * rango) + min;
    	return rand;
    }
    
    private int getRandomY() {
    	//range
    	//se considera una fila menos a cada lado porque en los bordes hay bosque siempre
    	int min = 2;
    	int max = 8;
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
        
        str += "Habitaciones sucias: {";
        for(Escenario h : habitacionesSucias)
        	str+= h.toString() + ", ";
        str = str.substring(0,str.length()-2);
        str += "}\n";
        
        str += "Posici�n del agente: ";
        str += posicionAspiradora.toString(); 

        return str;
    }

     public List<Escenario> gethabitacionesSucias(){
        return habitacionesSucias;
     }
     
     public void sethabitacionesSucias(List<Escenario> arg){
        habitacionesSucias = arg;
     }
    
     public Escenario getposicionCaperucita(){
        return posicionAspiradora;
     }
     public void setposicionAspiradora(Escenario arg){
        posicionAspiradora = arg;
     }

	public Escenario getPosicionCaperucita() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

