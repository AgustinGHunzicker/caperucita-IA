package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CaperucitaPerception extends Perception {

    public static int UNKNOWN_PERCEPTION = -1;
    public static int NADA = 1;
    public static int DULCE = 2;
    public static int LOBO = 4;
    public static int META = 5;
    //Bandera que indican que hay en esa direccion
	private int izquierda;
	private int derecha;
	private int arriba;
	private int abajo;
	//Cantidad de movimiento que puede hacer en esa direccion
	private int movIzquierda;
	private int movDerecha;
	private int movArriba;
	private int movAbajo;
	//Posiciones exactas en el escenario percibido
	private Point caminoFlores;
	private Point posicionLobo;
	private Point posicionActual; //
	private HashSet<Point> dulces;
	//Posiblemente necesitemos la cantidad de dulces en esa direccion
	
    public  CaperucitaPerception() {
    	izquierda = UNKNOWN_PERCEPTION;
    	derecha = UNKNOWN_PERCEPTION;
    	arriba = UNKNOWN_PERCEPTION;
    	abajo = UNKNOWN_PERCEPTION;
    	movIzquierda = 0;
    	movDerecha = 0;
    	movArriba = 0;
    	movAbajo = 0;
    	caminoFlores = new Point(UNKNOWN_PERCEPTION, UNKNOWN_PERCEPTION);
    	posicionLobo = new Point(UNKNOWN_PERCEPTION, UNKNOWN_PERCEPTION);
    	dulces = new HashSet<Point>();
    }

    public CaperucitaPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method initializa a perception of the agent.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
		Caperucita agent = (Caperucita) agentIn;
        Ambiente environment = (Ambiente) environmentIn;
        EstadoAmbiente environmentState = environment.getEnvironmentState();
       
        //Acá se crea la percepción inicial del agente
        Point position = environmentState.getposicionCaperucita();
        
    }
    
    public void setIzquierdaPerception(int izqPerception) {
    	this.izquierda = izqPerception;
    }
    
    public void setDerechaPerception(int derPerception) {
    	this.derecha = derPerception;
    }
    
    public void setArribaPerception(int arrPerception) {
    	this.arriba = arrPerception;
    }
    
    public void setAbajoPerception(int abaPerception) {
    	this.abajo = abaPerception;
    }
    
    public void setFloresPerception(Point flores) {
    	this.caminoFlores = flores;
    }
    
    public void setDulcesPerception(HashSet<Point> dulces) {
    	this.dulces = dulces;
    }
    
    
    public int getIzquierda() {
		return izquierda;
	}

	public int getDerecha() {
		return derecha;
	}

	public int getArriba() {
		return arriba;
	}

	public int getAbajo() {
		return abajo;
	}
	
	public int getMovIzquierda() {
		return movIzquierda;
	}

	public void setMovIzquierda(int movIzquierda) {
		this.movIzquierda = movIzquierda;
	}

	public int getMovDerecha() {
		return movDerecha;
	}

	public void setMovDerecha(int movDerecha) {
		this.movDerecha = movDerecha;
	}

	public int getMovArriba() {
		return movArriba;
	}

	public void setMovArriba(int movArriba) {
		this.movArriba = movArriba;
	}

	public int getMovAbajo() {
		return movAbajo;
	}

	public void setMovAbajo(int movAbajo) {
		this.movAbajo = movAbajo;
	}

	public Point getPosicionLobo() {
		return posicionLobo;
	}

	public void setPosicionLobo(Point posicionLobo) {
		this.posicionLobo = posicionLobo;
	}

	public Point getCaminoFlores() {
		return caminoFlores;
	}

	public Set<Point> getDulces() {
		return dulces;
	}

	public Point getPosicionActual() {
		return posicionActual;
	}
	
	@Override
    public String toString() {
        
    	/*String str = "";
        
        str += "La habitaci�n est� ";
        str += (suciedad == 1)?"sucia.":"limpia.";
		
        return str.toString();
        */
    	return "";
    }

    // The following methods are agent-specific:	
    /* 
    public int getsuciedad(){
        return suciedad;
     }
     
     public void setsuciedad(int arg){
        this.suciedad = arg;
     }
     */
	
}
