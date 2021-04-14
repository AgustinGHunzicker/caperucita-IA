package domain;

import java.util.List;

public class Escenario {

	private String nombre;
	private int[][] posiciones;
	
	public Escenario() {
		this.posiciones = new int[14][9];
	}
	
	public void generarEscenario(int nroEsc) {
		this.posiciones = new int[14][9];
		
		//Se inicializa los arboles y los casilleros vacíos
		
		//los bordes del escenario son siempre arboles
    	for (int i = 1; i <= 14; i++) {
	   		this.posiciones[i][1] = 1;
    		this.posiciones[i][9] = 1;
    	}
		    	
		for (int j = 1; j <= 9; j++) {
	   		this.posiciones[1][j] = 1;
	   		this.posiciones[14][j] = 1;
    	}
		
		//casilleros vacíos
		for (int i = 2; i <= 13; i++) {
			for (int j = 2; j <= 8; j++) {
				this.posiciones[i][j] = 0;
			}
		}
		
		//generar escenarios predeterminados
		switch(nroEsc) {
			case 1: {
				//camino flores
				this.posiciones[8][8] = 5;
				this.posiciones[8][9] = 5;
				
				//arboles
				for (int j = 2; j <= 8; j++) {
					this.posiciones[2][j] = 1;
					this.posiciones[3][j] = 1;
				}
				
				this.posiciones[4][5] = 1;
				this.posiciones[5][3] = 1;
				this.posiciones[5][5] = 1;
				this.posiciones[5][6] = 1;
				this.posiciones[6][6] = 1;
				this.posiciones[6][7] = 1;
				this.posiciones[7][7] = 1;
				this.posiciones[7][8] = 1;
				this.posiciones[8][2] = 1;
				this.posiciones[8][7] = 1;
				this.posiciones[9][5] = 1;
				this.posiciones[10][4] = 1;
				this.posiciones[10][7] = 1;
				this.posiciones[12][2] = 1;
				this.posiciones[12][7] = 1;
				this.posiciones[12][8] = 1;
				
				for (int j = 2; j <= 8; j++) {
					this.posiciones[13][j] = 1;
				}
				break;
			}
			case 2: {
				//camino flores
				this.posiciones[7][8] = 5;
				this.posiciones[7][9] = 5;
				
				//arboles
				for (int j = 2; j <= 8; j++) {
					this.posiciones[2][j] = 1;
					this.posiciones[3][j] = 1;
				}
				
				this.posiciones[4][2] = 1;
				this.posiciones[4][3] = 1;
				this.posiciones[4][4] = 1;
				this.posiciones[4][8] = 1;
				this.posiciones[5][2] = 1;
				this.posiciones[5][4] = 1;
				this.posiciones[5][5] = 1;
				this.posiciones[5][8] = 1;
				this.posiciones[6][5] = 1;
				this.posiciones[6][6] = 1;
				this.posiciones[6][8] = 1;
				this.posiciones[8][2] = 1;
				this.posiciones[8][3] = 1;
				this.posiciones[8][5] = 1;
				this.posiciones[8][6] = 1;
				this.posiciones[8][8] = 1;
				this.posiciones[9][2] = 1;
				this.posiciones[9][3] = 1;
				this.posiciones[10][2] = 1;
				this.posiciones[10][5] = 1;
				this.posiciones[10][6] = 1;
				this.posiciones[10][7] = 1;
				this.posiciones[11][2] = 1;
				this.posiciones[11][3] = 1;
				
				for (int j = 2; j <= 8; j++) {
					this.posiciones[12][j] = 1;
					this.posiciones[13][j] = 1;
				}
				break;
				
			}
			case 3: {
				//camino flores
				this.posiciones[4][1] = 5;
				this.posiciones[4][2] = 5;
				
				//arboles
				for (int j = 2; j <= 8; j++) {
					this.posiciones[2][j] = 1;
					this.posiciones[3][j] = 1;
				}
				
				this.posiciones[4][5] = 1;
				this.posiciones[4][8] = 1;
				this.posiciones[5][2] = 1;
				this.posiciones[5][3] = 1;
				this.posiciones[5][5] = 1;
				this.posiciones[5][8] = 1;
				this.posiciones[6][8] = 1;
				this.posiciones[7][3] = 1;
				this.posiciones[8][6] = 1;
				this.posiciones[8][7] = 1;
				this.posiciones[9][2] = 1;
				this.posiciones[10][2] = 1;
				this.posiciones[10][3] = 1;
				this.posiciones[10][4] = 1;
				this.posiciones[10][6] = 1;
				this.posiciones[10][8] = 1;
				this.posiciones[11][2] = 1;
				this.posiciones[11][3] = 1;
				this.posiciones[11][7] = 1;
				this.posiciones[11][8] = 1;
				this.posiciones[12][2] = 1;
				this.posiciones[12][3] = 1;
				this.posiciones[12][4] = 1;
				this.posiciones[12][8] = 1;
				
				for (int j = 2; j <= 8; j++) {
					this.posiciones[13][j] = 1;
				}
				break;
			}
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int[][] getPosiciones(){
		return this.posiciones;
	}
	
	public void setPosiciones(int[][] posiciones) {
		this.posiciones = posiciones;
	}

	public int getPosicionXY(int x, int y) {
		return this.posiciones[x][y];
	}

	public void setPosicionXY(int x, int y, int objetoCasillero) {
		this.posiciones[x][y] = objetoCasillero;
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
