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
        
    	Escenario escenario = this.getEnvironmentState().getEscenario();
    			
    	//Creamos la percepcion que el agente obtendrá, a partir del estado actual del ambiente
        CaperucitaPerception perception = new CaperucitaPerception();
		
        //Pimero chequeamos donde está caperucita
        int posXCap = (int) this.getEnvironmentState().getposicionCaperucita().getX();
        int posYCap = (int) this.getEnvironmentState().getposicionCaperucita().getY();
        
        //Miramos a los cuatros lados para saber que percibe Caperucita
        
        boolean arbol = false;
        boolean lobo[] = {false, false, false, false};
        boolean hayFlores[] = {false, false, false, false};
        Point flores = new Point();
        
        //representa cuantas casillas se puede mover Caperucita en las direcciones izq, der, arr y aba
        int cantPosiciones[] = {1, 1, 1, 1};
        //representa cuantos dulces puede encontrar Caperucita en las direcciones izq, der, arr y aba
        int cantDulces[] = {0, 0, 0, 0};
        //representa los casilleros donde están los dulces que puede encontrar Caperucita
        HashSet<Point> posDulces = new HashSet<Point>();
        
        //izquierda
        while(arbol == false && lobo[0] == false) {
        	if (escenario.getPosicionXY(posXCap - cantPosiciones[0], posYCap) == 0) {
        		//puedo seguir avanzando, está vacío
        		cantPosiciones[0]++;
        	}
        	else if (escenario.getPosicionXY(posXCap - cantPosiciones[0], posYCap) == 1) {
        		//hay un arbol, hasta acá nomás se puede llegar
        		arbol = true;
        	}
        	else if (escenario.getPosicionXY(posXCap - cantPosiciones[0], posYCap) == 2) {
        		//hay un dulce, lo tengo que juntar y puedo seguir avanzando
        		Point dulce = new Point(posXCap - cantPosiciones[0], posYCap);
        		posDulces.add(dulce);
        		cantDulces[0]++;
        		cantPosiciones[0]++;
        	}
        	else if (escenario.getPosicionXY(posXCap - cantPosiciones[0], posYCap) == 4) {
        		//está el lobo, no debería moverse en esta dirección
        		lobo[0] = true;
        	}
        	else if (escenario.getPosicionXY(posXCap - cantPosiciones[0], posYCap) == 5) {
        		//Está el camino de flores, la meta
        		flores.setLocation(posXCap - cantPosiciones[0], posYCap);
        		hayFlores[0] = true;
        	}
        }
        
        arbol = false;
        
        //derecha
        while(arbol == false && lobo[1] == false) {
        	if (escenario.getPosicionXY(posXCap + cantPosiciones[1], posYCap) == 0) {
        		//puedo seguir avanzando, está vacío
        		cantPosiciones[1]++;
        	}
        	else if (escenario.getPosicionXY(posXCap + cantPosiciones[1], posYCap) == 1) {
        		//hay un arbol, hasta acá nomás se puede llegar
        		arbol = true;
        	}
        	else if (escenario.getPosicionXY(posXCap + cantPosiciones[1], posYCap) == 2) {
        		//hay un dulce, lo tengo que juntar y puedo seguir avanzando
        		Point dulce = new Point(posXCap + cantPosiciones[1], posYCap);
        		posDulces.add(dulce);
        		cantDulces[1]++;
        		cantPosiciones[1]++;
        	}
        	else if (escenario.getPosicionXY(posXCap + cantPosiciones[1], posYCap) == 4) {
        		//está el lobo, no debería moverse en esta dirección
        		lobo[1] = true;
        	}
        	else if (escenario.getPosicionXY(posXCap + cantPosiciones[1], posYCap) == 5) {
        		//Está el camino de flores, la meta
        		flores.setLocation(posXCap + cantPosiciones[1], posYCap);
        		hayFlores[1] = true;
        	}
        }
        
        arbol = false;
        
        //arriba
        while(arbol == false && lobo[2] == false) {
        	if (escenario.getPosicionXY(posXCap, posYCap - cantPosiciones[2]) == 0) {
        		//puedo seguir avanzando, está vacío
        		cantPosiciones[2]++;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap - cantPosiciones[2]) == 1) {
        		//hay un arbol, hasta acá nomás se puede llegar
        		arbol = true;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap - cantPosiciones[2]) == 2) {
        		//hay un dulce, lo tengo que juntar y puedo seguir avanzando
        		Point dulce = new Point(posXCap, posYCap - cantPosiciones[2]);
        		posDulces.add(dulce);
        		cantDulces[2]++;
        		cantPosiciones[2]++;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap - cantPosiciones[2]) == 4) {
        		//está el lobo, no debería moverse en esta dirección
        		lobo[2] = true;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap - cantPosiciones[2]) == 5) {
        		//Está el camino de flores, la meta
        		flores.setLocation(posXCap, posYCap - cantPosiciones[2]);
        		hayFlores[2] = true;
        	}
        }
        
        arbol = false;
        
        //abajo
        while(arbol == false && lobo[3] == false) {
        	if (escenario.getPosicionXY(posXCap, posYCap + cantPosiciones[3]) == 0) {
        		//puedo seguir avanzando, está vacío
        		cantPosiciones[3]++;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap + cantPosiciones[3]) == 1) {
        		//hay un arbol, hasta acá nomás se puede llegar
        		arbol = true;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap + cantPosiciones[3]) == 2) {
        		//hay un dulce, lo tengo que juntar y puedo seguir avanzando
        		Point dulce = new Point(posXCap, posYCap + cantPosiciones[3]);
        		posDulces.add(dulce);
        		cantDulces[3]++;
        		cantPosiciones[3]++;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap + cantPosiciones[3]) == 4) {
        		//está el lobo, no debería moverse en esta dirección
        		lobo[3] = true;
        	}
        	else if (escenario.getPosicionXY(posXCap, posYCap + cantPosiciones[3]) == 5) {
        		//Está el camino de flores, la meta
        		flores.setLocation(posXCap, posYCap + cantPosiciones[3]);
        		hayFlores[3] = true;
        	}
        }
        
        //setear parametros de la perception
        // no deberiamos iniciar con 0, si son la cantidad de movimientos que puede hacer?
        perception.setIzquierdaPerception(CaperucitaPerception.NADA);
        perception.setDerechaPerception(CaperucitaPerception.NADA);
        perception.setArribaPerception(CaperucitaPerception.NADA);
        perception.setAbajoPerception(CaperucitaPerception.NADA);
        
        perception.setDulcesPerception(posDulces);
        
        //comprobar si el lobo está en alguna de las direcciones
       	if (lobo[0] == true) {
       		perception.setIzquierdaPerception(CaperucitaPerception.LOBO);	
       	}
       	else if (lobo[1] == true) {
       		perception.setDerechaPerception(CaperucitaPerception.LOBO);
       	}
       	else if (lobo[2] == true) {
       		perception.setArribaPerception(CaperucitaPerception.LOBO);
       	}
       	else if (lobo[3] == true) {
       		perception.setAbajoPerception(CaperucitaPerception.LOBO);
       	}
        
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
    
    public String toString() {
        return environmentState.toString();
    }

    //Este m�todo indica bajo qu� condici�n se considera que el agente ha fallado
    public boolean agentFailed(Action actionReturned) {
    	boolean failed = false;

    	//Notar que en este punto tenemos 3 posibilidades inmediatas:
    	//1 - Agregar al estado del ambiente el atributo que nos indica falla (energ�a)
    	//2 - Agregar un operador que se denomine "apagar" (que vendr� en "actionReturned")
    	//3 - Modificar GoalBasedAgentSimulator para que pase el AgentState en lugar de Action

        return failed;
    }
    
    
}
