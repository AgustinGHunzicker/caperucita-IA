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
        
        HashSet<Point> posDulces = new HashSet<Point>();
        
    	//Creamos la percepcion que el agente obtendrá, a partir del estado actual del ambiente
        CaperucitaPerception perception = new CaperucitaPerception();

        this.verIzquierda(escenario, posXCap, posYCap);
        this.verDerecha(escenario, posXCap, posYCap);
        this.verArriba(escenario, posXCap, posYCap);
        this.verAbajo(escenario, posXCap, posYCap);

        // setear parametros de la perception
        // no deberiamos iniciar con 0, si son la cantidad de movimientos que puede hacer?
        perception.setIzquierdaPerception(CaperucitaPerception.NADA);
        perception.setDerechaPerception(CaperucitaPerception.NADA);
        perception.setArribaPerception(CaperucitaPerception.NADA);
        perception.setAbajoPerception(CaperucitaPerception.NADA);
        
        perception.setDulcesPerception(posDulces);
        
        //--prioridades
        //lobo
        //flores
        //dulces
        //nada
        
//      args.add(posLobo);
//      //
//      //args.add(hayFlores);
//      args.add(flores);
//      //
//      args.add(cantPosiciones);
//      //
//      //args.add(cantDulces);
//      args.add(posDulces);
        
        
        
        //comprobar si el lobo está en alguna de las direcciones
       	if (!args.get(0).equals(-1)) perception.setIzquierdaPerception(CaperucitaPerception.LOBO);	
       	else if (!args.get(0).equals(-1))
        
       	//comprobar si hay dulces en alguna de las direccionesflores
       	if(cantDulces[0] > 0) {
       		perception.setIzquierdaPerception(CaperucitaPerception.DULCE);
       	}
       	if (cantDulces[1] > 0) {
       		perception.setDerechaPerception(CaperucitaPerception.DULCE);
       	}
       	
       	//comprobar si se percibió el camino de flores
        if (hayFlores[0] == true) {
        	perception.setIzquierdaPerception(CaperucitaPerception.META);
        	perception.setFloresPerception(flores);
        }
        else if (hayFlores[1] == true) {
        	perception.setDerechaPerception(CaperucitaPerception.META);
        	perception.setFloresPerception(flores);
        }
        else if (hayFlores[2] == true) {
        	perception.setArribaPerception(CaperucitaPerception.META);
        	perception.setFloresPerception(flores);
        }
        else if (hayFlores[3] == true) {
        	perception.setAbajoPerception(CaperucitaPerception.META);
        	perception.setFloresPerception(flores);
        }
       	
        // Return the perception
        return perception;
    }
    
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
    
    
    
    public String toString() {
        return environmentState.toString();
    }

    //Este m�todo indica bajo qu� condici�n se considera que el agente ha fallado
    public boolean agentFailed(Action actionReturned) {
    	boolean failed = false;

        return failed;
    }
    
    
}
