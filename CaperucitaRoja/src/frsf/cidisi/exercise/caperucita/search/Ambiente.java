package frsf.cidisi.exercise.caperucita.search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import domain.Escenario;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;

public class Ambiente extends Environment {
	
    public Ambiente() {
        // Create the environment state
        this.environmentState = new EstadoAmbiente();
    }

    public EstadoAmbiente getEnvironmentState() {
        return (EstadoAmbiente) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public CaperucitaPerception getPercept() {
        
    	//Recuperamos el estado escenario en cuestion
    	Escenario escenario = this.getEnvironmentState().getEscenario();
    					
        //Pimero chequeamos donde está caperucita
        int posXCap = (int) this.getEnvironmentState().getposicionCaperucita().getX();
        int posYCap = (int) this.getEnvironmentState().getposicionCaperucita().getY();
        
    	//Creamos la percepcion que el agente obtendrá, a partir del estado actual del ambiente
        CaperucitaPerception perception = new CaperucitaPerception();
        
        //Se mira hacia las cuatro direcciones
        VistaCaperucita vistaIzquierda 	= this.verIzquierda(escenario, posXCap, posYCap);
        VistaCaperucita vistaDerecha   	= this.verDerecha(escenario, posXCap, posYCap);
        VistaCaperucita vistaArriba    	= this.verArriba(escenario, posXCap, posYCap);
        VistaCaperucita vistaAbajo 		= this.verAbajo(escenario, posXCap, posYCap);        
        
        //Se carga la bandera de jerarquia de posicionamiento de objetos en cada direccion
       	perception.setIzquierdaPerception(this.getPrioridad(vistaIzquierda));	
       	perception.setDerechaPerception(this.getPrioridad(vistaDerecha));	
       	perception.setArribaPerception(this.getPrioridad(vistaArriba));	
       	perception.setAbajoPerception(this.getPrioridad(vistaAbajo));
       	
       	//Se carga los movimientos permitidos en cada direccion
       	perception.setMovIzquierda(vistaIzquierda.cantPosiciones);
       	perception.setMovDerecha(vistaDerecha.cantPosiciones);
       	perception.setMovArriba(vistaArriba.cantPosiciones);
       	perception.setMovAbajo(vistaAbajo.cantPosiciones);
       	
       	//Se carga las posicion de las flores, si es que las haya visto
       	perception.setFloresPerception(this.floresHay(vistaIzquierda.posFlores, vistaDerecha.posFlores, vistaArriba.posFlores, vistaAbajo.posFlores));
       	
       	//Se carga las posicion del lobo, si es que lo vio
       	perception.setPosicionLobo(this.loboEsta(vistaIzquierda.posLobo, vistaDerecha.posLobo, vistaArriba.posLobo, vistaAbajo.posLobo));
       	
       	//Se pasa la ubicacion actual de caperucita
       	perception.setPosicionActual(new Point(posXCap, posYCap));
       	
       	//Se carga las posicion de los dulces, si hubiese
       	HashSet<Point> dulces = new HashSet<Point>();
       	dulces.addAll(vistaIzquierda.posDulces);
       	dulces.addAll(vistaDerecha.posDulces);
       	dulces.addAll(vistaArriba.posDulces);
       	dulces.addAll(vistaAbajo.posDulces);
       	perception.setDulcesPerception(dulces);
    	
       	//Se retorna la percepcion
        return perception;
    }
    
    /*
     * Clase que funciona como estructura de vista de caperucita hacia una direccion en particular
     */
    private class VistaCaperucita {
    	private Point posLobo;
    	private Point posFlores;
    	private HashSet<Point> posDulces;
    	private int cantPosiciones;
    	
    	public VistaCaperucita() {
    		this.posLobo = new Point(-1,-1);
    		this.posFlores = new Point(-1,-1);
    		this.posDulces = new HashSet<Point>();
    		this.cantPosiciones = 1;
    	}
    	
    }
    
    /*
     * Mira hacia izquierda y devuelve los objetos a la vista hasta chocarse con un arbol o el lobo
     */
    private VistaCaperucita verIzquierda(Escenario escenario, int posXCap, int posYCap) {
    	
    	VistaCaperucita vista = new VistaCaperucita(); 
        boolean arbol = false;
        Point noHay = new Point(-1,-1);

        while(arbol == false && vista.posLobo.equals(noHay)) {
        	
        	switch(escenario.getPosicionXY(posXCap - vista.cantPosiciones, posYCap)) {
        	  case 0: //puedo seguir avanzando, está vacío
          		vista.cantPosiciones++;
        	    break;
        	  case 1: //hay un arbol, hasta acá nomás se puede llegar
          		arbol = true;
        	    break;
        	  case 2: //hay un dulce, lo tengo que juntar y puedo seguir avanzando
          		Point dulce = new Point(posXCap - vista.cantPosiciones, posYCap);
          		vista.posDulces.add(dulce);
          		vista.cantPosiciones++;
          	    break;
          	  case 4: //está el lobo, no debería moverse en esta dirección
          		vista.posLobo.setLocation(posXCap - vista.cantPosiciones, posYCap);
          	    break;
          	  case 5: //Está el camino de flores, la meta
          		vista.posFlores.setLocation(posXCap - vista.cantPosiciones, posYCap);
          	    break;
        	  default: // code block - error pararmeter
        	}
        }
        
        return vista;
    }
    
    /*
     * Mira hacia derecha y devuelve los objetos a la vista hasta chocarse con un arbol o el lobo
     */
    private VistaCaperucita verDerecha(Escenario escenario, int posXCap, int posYCap) {
    	
    	VistaCaperucita vista = new VistaCaperucita(); 
        boolean arbol = false;
        Point noHay = new Point(-1,-1);

        while(arbol == false && vista.posLobo.equals(noHay)) {
        	
        	switch(escenario.getPosicionXY(posXCap + vista.cantPosiciones, posYCap)) {
        	  case 0: //puedo seguir avanzando, está vacío
          		vista.cantPosiciones++;
        	    break;
        	  case 1: //hay un arbol, hasta acá nomás se puede llegar
          		arbol = true;
        	    break;
        	  case 2: //hay un dulce, lo tengo que juntar y puedo seguir avanzando
          		Point dulce = new Point(posXCap + vista.cantPosiciones, posYCap);
          		vista.posDulces.add(dulce);
          		vista.cantPosiciones++;
          	    break;
          	  case 4: //está el lobo, no debería moverse en esta dirección
          		vista.posLobo.setLocation(posXCap + vista.cantPosiciones, posYCap);
          	    break;
          	  case 5: //Está el camino de flores, la meta
          		vista.posFlores.setLocation(posXCap + vista.cantPosiciones, posYCap);
          	    break;
        	  default: // code block - error pararmeter
        	}
        }
        
        return vista;
    }
    
    /*
     * Mira hacia arriba y devuelve los objetos a la vista hasta chocarse con un arbol o el lobo
     */
    private VistaCaperucita verArriba(Escenario escenario, int posXCap, int posYCap) {
    	
    	VistaCaperucita vista = new VistaCaperucita(); 
        boolean arbol = false;
        Point noHay = new Point(-1,-1);

        while(arbol == false && vista.posLobo.equals(noHay)) {
        	
        	switch(escenario.getPosicionXY(posXCap, posYCap + vista.cantPosiciones)) {
        	  case 0: //puedo seguir avanzando, está vacío
          		vista.cantPosiciones++;
        	    break;
        	  case 1: //hay un arbol, hasta acá nomás se puede llegar
          		arbol = true;
        	    break;
        	  case 2: //hay un dulce, lo tengo que juntar y puedo seguir avanzando
          		Point dulce = new Point(posXCap, posYCap + vista.cantPosiciones);
          		vista.posDulces.add(dulce);
          		vista.cantPosiciones++;
          	    break;
          	  case 4: //está el lobo, no debería moverse en esta dirección
          		vista.posLobo.setLocation(posXCap, posYCap + vista.cantPosiciones);
          	    break;
          	  case 5: //Está el camino de flores, la meta
          		vista.posFlores.setLocation(posXCap, posYCap + vista.cantPosiciones);
          	    break;
        	  default: // code block - error pararmeter
        	}
        }
        
        return vista;      
    }
    
    /*
     * Mira hacia abajo y devuelve los objetos a la vista hasta chocarse con un arbol o el lobo
     */
    private VistaCaperucita verAbajo(Escenario escenario, int posXCap, int posYCap) {
    	
    	VistaCaperucita vista = new VistaCaperucita(); 
        boolean arbol = false;
        Point noHay = new Point(-1,-1);

        while(arbol == false && vista.posLobo.equals(noHay)) {
        	
        	switch(escenario.getPosicionXY(posXCap, posYCap - vista.cantPosiciones)) {
        	  case 0: //puedo seguir avanzando, está vacío
          		vista.cantPosiciones++;
        	    break;
        	  case 1: //hay un arbol, hasta acá nomás se puede llegar
          		arbol = true;
        	    break;
        	  case 2: //hay un dulce, lo tengo que juntar y puedo seguir avanzando
          		Point dulce = new Point(posXCap, posYCap - vista.cantPosiciones);
          		vista.posDulces.add(dulce);
          		vista.cantPosiciones++;
          	    break;
          	  case 4: //está el lobo, no debería moverse en esta dirección
          		vista.posLobo.setLocation(posXCap, posYCap - vista.cantPosiciones);
          	    break;
          	  case 5: //Está el camino de flores, la meta
          		vista.posFlores.setLocation(posXCap, posYCap - vista.cantPosiciones);
          	    break;
        	  default: // code block - error pararmeter
        	}
        }
        
        return vista;        
    }
        
    /*
     * -- TODO Prioridades ver si cambiamos primero dulce que flores
     * lobo
     * flores
     * dulces
     * nada
     */
    private int getPrioridad(VistaCaperucita v) {
    	
    	Point noHay = new Point(-1,-1);
    	
       	if (!v.posLobo.equals(noHay)) 
       		return CaperucitaPerception.LOBO;	
       	if (!v.posFlores.equals(noHay)) 
       		return CaperucitaPerception.META;
       	if (!v.posDulces.isEmpty()) 
       		return CaperucitaPerception.DULCE;
       	else 
       		return CaperucitaPerception.NADA;
    }
    
    /*
     * Devuelve la posicion del lobo si esta a la vista
     * caso contrario devuelve un fuera de rango Point(-1, -1)
     */
    private Point loboEsta(Point i, Point d, Point ar, Point ab) {
    	
    	Point noEsta = new Point(-1,-1);
    	
    	if (!i.equals(noEsta)) 
    		return i;
    	if (!d.equals(noEsta)) 
    		return i;
    	if (!ar.equals(noEsta)) 
    		return i;
    	if (!ab.equals(noEsta)) 
    		return i;
    	else
    		return noEsta;
    }
    
    /*
     * Devuelve la posicion de las flores si hay a la vista
     * caso contrario devuelve un fuera de rango Point(-1, -1)
     */
    private Point floresHay(Point i, Point d, Point ar, Point ab) {
    	
    	Point noHay = new Point(-1,-1);
    	
    	if (!i.equals(noHay)) 
    		return i;
    	if (!d.equals(noHay)) 
    		return i;
    	if (!ar.equals(noHay)) 
    		return i;
    	if (!ab.equals(noHay)) 
    		return i;
    	else
    		return noHay;
    }
    
    public String toString() {
        return environmentState.toString();
    }

    //Este m�todo indica bajo qu� condici�n se considera que el agente ha fallado
    public boolean agentFailed(Action actionReturned) {
    	boolean failed = false;

        return failed;
    }
    
    
}
