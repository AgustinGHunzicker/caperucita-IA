package domain;

import java.util.List;

public class Escenario {

	private String nombre;
	
	public Escenario() {
		
	}
	
	public Escenario(String name){
		this.nombre = name;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Escenario clone(){
		Escenario newEscenario = new Escenario();
		
		newEscenario.setNombre(this.getNombre());
		
		return newEscenario;
	}
	
	
	public String toString(){
		return nombre;
	}
	
	/*
	public static boolean todasVisitadas(List<Escenario> habitaciones){
		boolean allVisited = true;
		
		for(Escenario h : habitaciones)
			allVisited = allVisited && h.isVisitada();
		
		return allVisited;
	}
	*/

}
