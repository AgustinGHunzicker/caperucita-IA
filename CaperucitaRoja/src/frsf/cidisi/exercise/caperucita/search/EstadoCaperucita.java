package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import domain.Escenario;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class EstadoCaperucita extends SearchBasedAgentState {
	
    private Escenario escenarioJuego;
    private Point posicionActual;
    private Point posicionFlores;
    private HashSet<Point> posicionesDulces;
    private int vidasRestantes;
    
    private static final Point UNKNOWN = new Point(-1, -1);
    
    public EstadoCaperucita(Ambiente escenario) {
    	escenarioJuego = new Escenario();
    	posicionActual = escenario.getEnvironmentState().getposicionCaperucita();
    	posicionFlores = new Point();
    	posicionesDulces = new HashSet<Point>();
		vidasRestantes = 3;
    	
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
    	
    	EstadoCaperucita newState = new EstadoCaperucita(null); //TODO: actualizar cuando se tenga el Ambiente
    	
    	//Los atributos de tipo primitvos se pasan por copia
    	newState.setVidasRestantes(this.getVidasRestantes());
    	newState.setPosicionCaperucita(this.getposicionCaperucita());
    	newState.setPosicionesDulces(this.getPosicionesDulces());
        
    	return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
    	
    	CaperucitaPerception percepcion = (CaperucitaPerception) p;
    		
    	//Traigo las posiciones conocidas por caperucita hasta el momento
    	int escenarioConocido[][] = escenarioJuego.getPosiciones();
    	int posX = (int)posicionActual.getX();
    	int posY = (int)posicionActual.getY();
    	
    	//Lugares libres para mover a la Izquierda
    	for (int i = 0; i < percepcion.getIzquierda(); i++) escenarioConocido[posY][posX-i] = 0;
    	
    	//Lugares libres para mover a la Derecha
    	for (int i = 0; i < percepcion.getDerecha(); i++) escenarioConocido[posY][posX+i] = 0;
    	
    	//Lugares libres para mover a Abajo
    	for (int i = 0; i < percepcion.getAbajo(); i++) escenarioConocido[posY-i][posX] = 0;
    	
    	//Lugares libres para mover a Arriba
    	for (int i = 0; i < percepcion.getArriba(); i++) escenarioConocido[posY+i][posX] = 0;
    	
    	//TODO Despues de los movimientos libres (0) viene un arbol? Posiblemente es mejor inicializar la matriz toda en 1 del escenario de caperucita, asi no neceistamos preocuparnos por eso
    	    	
    	//Si no conocia el camino de flores y enconro la posicion en la ultima percepcion, lo seteo
    	if (!percepcion.getCaminoFlores().equals(new Point()) && !posicionFlores.equals(new Point())) {
    		posicionFlores = percepcion.getCaminoFlores();
    		escenarioConocido[(int)posicionFlores.getX()][(int)posicionFlores.getY()] = 5; 
    	}
    	
    	// Agrego las posiciones de los dulces percibidos
        for (Point i : percepcion.getDulces()) posicionesDulces.add(i);
        
        //TODO  deberia actualizarlo en la matriz a los dulces? entonces porque tenemos un arreglo de dulces?
        
        //TODO en la percepcion deberia tener la posicion del lobo? asi actualizo la matriz?
        
    	//Actualizo las posciones del escenario
    	escenarioJuego.setPosiciones(escenarioConocido);
        
        //TODO En este momento ya tiene actualizado el estado, debe invocar al metodo de busqueda de caminos
        // SearchBasedAgentState()
    	      
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        //Este método también debe tomar los valores del escenario particular
    	
        posicionFlores = UNKNOWN;
        posicionesDulces = new HashSet<Point>();
        vidasRestantes = 3;
    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
    	//TODO: hacer un toString para mostrar la solución de caperucita
    	
        return "";
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
    	//Cu�ndo dos estados son iguales lo definimos nosotros
    	//En este caso definimos que dos estado son iguales cuando estamos en la misma
    	//habitaci�n y la lista de habitaciones limpias es la misma (y la lista de visitadas!!)
    	EstadoCaperucita estadoComparado = (EstadoCaperucita) obj;
        
    	return true;
    }

    // The following methods are agent-specific:
    public Point getposicionCaperucita() {
    	return this.posicionActual;
    }
    
    public void setPosicionCaperucita(Point posicion) {
    	this.posicionActual = posicion;
    }
        
    public Point getPosicionFlores() {
    	return this.posicionFlores;
    }
    
    public void setPosicionFlores(Point posicion) {
    	this.posicionFlores = posicion;
    }
    
    public HashSet<Point> getPosicionesDulces(){
    	return this.posicionesDulces;
    }
    
    public void setPosicionesDulces(HashSet<Point> posiciones) {
    	this.posicionesDulces = posiciones;
    }
       
    public int getVidasRestantes(){
        return this.vidasRestantes;
    }
     
     public void setVidasRestantes(int vidas){
        this.vidasRestantes = vidas;
     }    
}

