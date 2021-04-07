package domain;

import java.util.List;

public class Escenario {

	private String nombre;
	private boolean visitada;
	
	public Escenario(){
		this.visitada = false;
	}
	
	public Escenario(String nombre){
		this.nombre = nombre;
		this.visitada = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public Escenario clone(){
		Escenario newHabitacion = new Escenario();
		
		newHabitacion.setNombre(this.getNombre());
		newHabitacion.setVisitada(this.isVisitada());
		
		return newHabitacion;
	}
	
	public String toString(){
		return nombre+"-"+visitada;
	}
	
	public static boolean todasVisitadas(List<Escenario> habitaciones){
		boolean allVisited = true;
		
		for(Escenario h : habitaciones)
			allVisited = allVisited && h.isVisitada();
		
		return allVisited;
	}

}
