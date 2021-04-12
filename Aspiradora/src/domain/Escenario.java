package domain;

import java.util.List;

public class Escenario {

	private String nombre;
	private int[][] posiciones;
	
	public Escenario() {
		this.posiciones = new int[12][9];
	}
	
	public Escenario(String name){
		this.nombre = name;
		this.posiciones = new int[12][9];
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int[][] getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(int[][] posiciones) {
		this.posiciones = posiciones;
	}

	public Escenario clone(){
		Escenario newEscenario = new Escenario();
		
		newEscenario.setNombre(this.getNombre());
		newEscenario.setPosiciones(this.getPosiciones());
		
		return newEscenario;
	}
	
	
	public String toString(){
		return nombre;
	}

}
