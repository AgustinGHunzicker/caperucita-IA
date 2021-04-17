package domain;

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
    	for (int i = 0; i < 14; i++) {
	   		this.posiciones[i][0] = 1;
    		this.posiciones[i][8] = 1;
    	}
		    	
		for (int j = 0; j < 9; j++) {
	   		this.posiciones[0][j] = 1;
	   		this.posiciones[13][j] = 1;
    	}
		
		//casilleros vacíos
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < 8; j++) {
				this.posiciones[i][j] = 0;
			}
		}
		
		//generar escenarios predeterminados
		switch(nroEsc) {
			case 1: {
				//camino flores
				this.posiciones[7][7] = 5;
				this.posiciones[7][8] = 5;
				
				//arboles
				for (int j = 1; j <= 7; j++) {
					this.posiciones[1][j] = 1;
					this.posiciones[2][j] = 1;
				}
				
				this.posiciones[3][4] = 1;
				this.posiciones[4][2] = 1;
				this.posiciones[4][4] = 1;
				this.posiciones[4][5] = 1;
				this.posiciones[5][5] = 1;
				this.posiciones[5][6] = 1;
				this.posiciones[6][6] = 1;
				this.posiciones[6][7] = 1;
				this.posiciones[7][1] = 1;
				this.posiciones[7][6] = 1;
				this.posiciones[8][4] = 1;
				this.posiciones[9][3] = 1;
				this.posiciones[9][6] = 1;
				this.posiciones[11][1] = 1;
				this.posiciones[11][6] = 1;
				this.posiciones[11][7] = 1;
				
				for (int j = 1; j <= 7; j++) {
					this.posiciones[12][j] = 1;
				}
				break;
			}
			case 2: {
				//camino flores
				this.posiciones[6][7] = 5;
				this.posiciones[6][8] = 5;
				
				//arboles
				for (int j = 1; j <= 7; j++) {
					this.posiciones[1][j] = 1;
					this.posiciones[2][j] = 1;
				}
				
				this.posiciones[3][1] = 1;
				this.posiciones[3][2] = 1;
				this.posiciones[3][3] = 1;
				this.posiciones[3][7] = 1;
				this.posiciones[4][1] = 1;
				this.posiciones[4][3] = 1;
				this.posiciones[4][4] = 1;
				this.posiciones[4][7] = 1;
				this.posiciones[5][4] = 1;
				this.posiciones[5][5] = 1;
				this.posiciones[5][7] = 1;
				this.posiciones[7][1] = 1;
				this.posiciones[7][2] = 1;
				this.posiciones[7][4] = 1;
				this.posiciones[7][5] = 1;
				this.posiciones[7][7] = 1;
				this.posiciones[8][1] = 1;
				this.posiciones[8][2] = 1;
				this.posiciones[9][1] = 1;
				this.posiciones[9][4] = 1;
				this.posiciones[9][5] = 1;
				this.posiciones[9][6] = 1;
				this.posiciones[10][1] = 1;
				this.posiciones[10][2] = 1;
				
				for (int j = 1; j <= 7; j++) {
					this.posiciones[11][j] = 1;
					this.posiciones[12][j] = 1;
				}
				break;
				
			}
			case 3: {
				//camino flores
				this.posiciones[3][0] = 5;
				this.posiciones[3][1] = 5;
				
				//arboles
				for (int j = 1; j <= 7; j++) {
					this.posiciones[1][j] = 1;
					this.posiciones[2][j] = 1;
				}
				
				this.posiciones[3][4] = 1;
				this.posiciones[3][7] = 1;
				this.posiciones[4][1] = 1;
				this.posiciones[4][2] = 1;
				this.posiciones[4][4] = 1;
				this.posiciones[4][7] = 1;
				this.posiciones[5][7] = 1;
				this.posiciones[6][2] = 1;
				this.posiciones[7][5] = 1;
				this.posiciones[7][6] = 1;
				this.posiciones[8][1] = 1;
				this.posiciones[8][2] = 1;
				this.posiciones[9][1] = 1;
				this.posiciones[9][2] = 1;
				this.posiciones[9][3] = 1;
				this.posiciones[9][5] = 1;
				this.posiciones[9][7] = 1;
				this.posiciones[10][1] = 1;
				this.posiciones[10][2] = 1;
				this.posiciones[10][6] = 1;
				this.posiciones[10][7] = 1;
				this.posiciones[11][1] = 1;
				this.posiciones[11][2] = 1;
				this.posiciones[11][3] = 1;
				this.posiciones[11][7] = 1;
				
				for (int j = 1; j <= 7; j++) {
					this.posiciones[12][j] = 1;
				}
				break;
			}
		}
	}
	
	public void imprimirEscenario() {
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 14; i++)
				System.out.print(this.posiciones[i][j]);
			System.out.println("");
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
