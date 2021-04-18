package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.HashSet;
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
    private Point posicionLobo;
    private HashSet<Point> posicionesDulces;
    private int vidasRestantes;
    
    private static final Point UNKNOWN = new Point(-1, -1);
    
    public EstadoCaperucita(Ambiente escenario) {
    	escenarioJuego = new Escenario();
    	posicionActual = escenario.getEnvironmentState().getposicionCaperucita();
    	posicionFlores = new Point(-1,-1);
    	posicionLobo   = new Point(-1,-1);
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
    	Point noExiste = new Point(-1,-1);
    	
    	//Lugares libres para mover a la Izquierda
    	for (int i = 0; i < percepcion.getMovIzquierda(); i++) escenarioConocido[posY][posX-i] = 0;
    	
    	//Lugares libres para mover a la Derecha
    	for (int i = 0; i < percepcion.getMovDerecha(); i++) escenarioConocido[posY][posX+i] = 0;
    	
    	//Lugares libres para mover a Arriba
    	for (int i = 0; i < percepcion.getMovArriba(); i++) escenarioConocido[posY+i][posX] = 0;
    	
    	//Lugares libres para mover a Abajo
    	for (int i = 0; i < percepcion.getMovAbajo(); i++) escenarioConocido[posY-i][posX] = 0;
    	
    	//Si sabe donde esta el camino de flores lo guarda
    	if (!percepcion.getCaminoFlores().equals(noExiste)) {
    		//Se lo carga a caperucita
    		this.posicionFlores = percepcion.getCaminoFlores();
    		//Se actualiza el escenario
    		escenarioConocido[(int)this.posicionFlores.getX()][(int)this.posicionFlores.getY()] = 5; 
    	}
    	
    	// Agrego las posiciones de los dulces percibidos
        for (Point i : percepcion.getDulces()) {
        	//Se lo agrego a caperucita
        	posicionesDulces.add(i);
        	//Actualizo el escenario
        	escenarioConocido[(int)i.getX()][(int)i.getY()] = 2; 
        }
        
        //TODO Pongo la posicion del lobo, puede ser que antes sabia donde esta, 
        // pero como el lobo se mueve, puede que ya no este a la vista, 
        // por lo que para el estado de caperucita y su escenario no se sabe donde esta, solo lo sabe el ambiente      
        //Ver aca no se si que el estado actual ya es un objeto copiado o se crea siempre
        
        //Borro la posicion vieja del lobo
        if (!this.posicionLobo.equals(noExiste)) 
        	escenarioConocido[(int)this.posicionLobo.getX()][(int)this.posicionLobo.getY()] = 0;
        
        //Si se donde esta ahora, lo agrego
        if (!percepcion.getPosicionLobo().equals(noExiste)) {
        	this.posicionLobo = percepcion.getPosicionLobo();
        	escenarioConocido[(int)this.posicionLobo.getX()][(int)this.posicionLobo.getY()] = 4;
        }

    	//Actualizo las posciones del escenario
    	this.escenarioJuego.setPosiciones(escenarioConocido);
        
        //TODO En este momento ya tiene actualizado el estado, debe invocar al metodo de busqueda de caminos
        // Search donde elije que operador aplicar
    	      
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
    
    	@SuppressWarnings("unused")
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

