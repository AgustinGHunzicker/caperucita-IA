package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.Escenario;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class EstadoCaperucita extends SearchBasedAgentState {
	
    private List<Escenario> habitacionesSucias;
    private List<Escenario> mapaHabitaciones;
    private Escenario escenarioJuego;
    private Point posicionActual;
    private Point posicionLobo;
    private Point posicionFlores;
    private List<Point> posicionesDulces;
    private int vidasRestantes;
    
    private static final Point UNKNOWN = new Point(-1, -1);
    
    public EstadoCaperucita(Ambiente escenario) {
    	escenarioJuego = new Escenario();
    	posicionActual = escenario.getEnvironmentState().getposicionCaperucita();
    	posicionLobo = new Point();
    	posicionFlores = new Point();
    	posicionesDulces = new ArrayList<Point>();
    	habitacionesSucias = new ArrayList<Escenario>();
		mapaHabitaciones = new ArrayList<Escenario>();
		vidasRestantes = 0;
    	
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
    	newState.setPosicionLobo(this.getPosicionLobo());
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
    	
    	//En base a la suciedad percibida y la posicion actual, actualizamos (de ser necesario)
    	//la lista de habitaciones sucias.
    	//Escenario posicionActual = this.getposicion();
    	//int suciedadPercibida = percepcion.getsuciedad();
    	
    	//Si percibimos suciedad, tenemos que agregar la posicion actual a la lista de
    	//habitaciones sucias (si es que no lo esta ya)
    	/*
    	if(suciedadPercibida == 1){
    		boolean yaExiste = false;
    		for(Escenario h : this.gethabitacionesSucias())
    			if(h.getNombre().equals(posicionActual.getNombre()))
    				yaExiste = true;
    		if(!yaExiste)
    			this.gethabitacionesSucias().add(this.getposicion());
    	}
    	//Si no percibimos suciedad, tenemos que remover la posicion actual de la lista de
    	//habitaciones sucias (si es que aun esta en la lista)
    	else{
    		Escenario habActual = null;
    		for(Escenario h : this.gethabitacionesSucias())
    			if(h.getNombre().equals(posicionActual.getNombre()))
    				habActual = h;
    		if(habActual != null)
    	this.gethabitacionesSucias().remove(habActual);
    	}
    	
    	*/
        
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        //Este método también debe tomar los valores del escenario particular
    	
        posicionLobo = UNKNOWN;
        posicionFlores = UNKNOWN;
        posicionesDulces = new ArrayList<Point>();
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
        
    	//boolean mismaPosicion = estadoComparado.getposicion().getNombre().equals(this.getposicion().getNombre());
    	/*boolean mismasHabitacionesSucias = true;
    	boolean mismasHabitacionesVisitadas = true;
    	mismasHabitacionesSucias = this.gethabitacionesSucias().size() == estadoComparado.gethabitacionesSucias().size();
    	if(mismasHabitacionesSucias){
    		String[] nombresActuales = getArrayOfNames(this.gethabitacionesSucias());
    		String[] nobresComparadas = getArrayOfNames(estadoComparado.gethabitacionesSucias());
    		Arrays.sort(nombresActuales);
    		Arrays.sort(nobresComparadas);
    		for(int i=0;i<nombresActuales.length;i++)
    			if(!(nombresActuales[i].equals(nobresComparadas[i])))
    				mismasHabitacionesSucias = false;
    	}
    	
    	for(int i=0;i < this.getmapaHabitaciones().size();i++){
    		mismasHabitacionesVisitadas = mismasHabitacionesVisitadas &&
    			(this.getmapaHabitaciones().get(i).isVisitada() ==
    				estadoComparado.getmapaHabitaciones().get(i).isVisitada());
    	}
    	*/
    	
        //return (mismaPosicion && mismasHabitacionesSucias && mismasHabitacionesVisitadas);
    	return true;
    }

    // The following methods are agent-specific:
    public Point getposicionCaperucita() {
    	return this.posicionActual;
    }
    
    public void setPosicionCaperucita(Point posicion) {
    	this.posicionActual = posicion;
    }
    
    public Point getPosicionLobo() {
    	return this.posicionLobo;
    }
    
    public void setPosicionLobo(Point posicion) {
    	this.posicionLobo = posicion;
    }
    
    public Point getPosicionFlores() {
    	return this.posicionFlores;
    }
    
    public void setPosicionFlores(Point posicion) {
    	this.posicionFlores = posicion;
    }
    
    public List<Point> getPosicionesDulces(){
    	return this.posicionesDulces;
    }
    
    public void setPosicionesDulces(List<Point> posiciones) {
    	this.posicionesDulces = posiciones;
    }
    
    public List<Escenario> gethabitacionesSucias(){
        return habitacionesSucias;
    }
     
    public void sethabitacionesSucias(List<Escenario> arg){
        habitacionesSucias = arg;
    }
     
    public int getVidasRestantes(){
        return this.vidasRestantes;
    }
     
     public void setVidasRestantes(int vidas){
        this.vidasRestantes = vidas;
     }
	
     private String[] getArrayOfNames(List<Escenario> habitaciones){
    	 String[] arrayOfNames = new String[habitaciones.size()];
    	 
    	 for(int i=0;i<habitaciones.size();i++)
    		 arrayOfNames[i] = habitaciones.get(i).getNombre();
    	 
    	 return arrayOfNames;
     }
     
}

