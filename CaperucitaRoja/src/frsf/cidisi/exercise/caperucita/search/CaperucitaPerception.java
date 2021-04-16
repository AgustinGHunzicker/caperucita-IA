package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import domain.Escenario;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class CaperucitaPerception extends Perception {

    public static int UNKNOWN_PERCEPTION = -1;
    public static int NADA = 1;
    public static int DULCE = 2;
    public static int LOBO = 4;
    public static int META = 5;
	private int izquierda;
	private int derecha;
	private int arriba;
	private int abajo;
	private Point caminoFlores;
	private List<Point> dulces;

    public  CaperucitaPerception() {
    	//Aquí crearimos los objetos, listas, arrays que componen la percepción
    	izquierda = UNKNOWN_PERCEPTION;
    	derecha = UNKNOWN_PERCEPTION;
    	arriba = UNKNOWN_PERCEPTION;
    	abajo = UNKNOWN_PERCEPTION;
    	caminoFlores = new Point(UNKNOWN_PERCEPTION, UNKNOWN_PERCEPTION);
    	dulces = new ArrayList<Point>();
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
    
    public void setDulcesPerception(List<Point> dulces) {
    	this.dulces = dulces;
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
