package frsf.cidisi.exercise.caperucita.search;

import domain.Escenario;
import enumeration.Consola;
import enumeration.EstadoCelda;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;

public class EstadoCaperucita extends SearchBasedAgentState {
    private final static Point UNKNOWN = new Point(-1, -1);

    private Escenario escenarioJuego;
    private Ambiente ambienteActual;
    private CaperucitaPerception ultimaPerception;
    private Point posicionActual;
    private Point posicionFlores;
    private Point posicionLobo;
    private HashSet<Point> posicionesDulces;
    private HashSet<Point> posicionesArboles;
    private HashSet<Point> dulcesJuntados;
    private int vidasRestantes;

    public EstadoCaperucita(Ambiente ambiente) {
        ambienteActual = ambiente;
        ultimaPerception = ambiente.getPercept();
        posicionActual = ambiente.getEnvironmentState().getPosicionCaperucita();
        initState();
    }

    @Override
    public void initState() {
        /*------- Genera la percepción del ambiente --------*/
        //TODO Este método también debe tomar los valores del escenario particular - ademas inicia valores previamente inicializados
        escenarioJuego = new Escenario();
        posicionFlores = UNKNOWN;
        posicionLobo = UNKNOWN;
        posicionesArboles = new HashSet<>();
        posicionesDulces = new HashSet<>();
        dulcesJuntados = new HashSet<>();
        vidasRestantes = 3;
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita newState = new EstadoCaperucita(this.getAmbienteActual());
        newState.setVidasRestantes(this.getVidasRestantes());
        newState.setPosicionCaperucita(this.getPosicionCaperucita());
        newState.setPosicionesDulces(this.getPosicionesDulces());
        newState.setPosicionesArboles(this.getPosicionesArboles());
        return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        this.setUltimaPerception((CaperucitaPerception) p);

        // Traigo las posiciones conocidas por caperucita hasta el momento
        EstadoCelda[][] celdasConocidas = getEscenarioJuego().getCeldas();

        int posX = (int) posicionActual.getX();
        int posY = (int) posicionActual.getY();

        // Lugares libres para mover a la Izquierda
        for (int movIzquierda = 0; movIzquierda <= getUltimaPerception().getCantMovimientosIzquierda(); movIzquierda++)
            celdasConocidas[posX - movIzquierda][posY] = EstadoCelda.VACIA;

        // Lugares libres para mover a la Derecha
        for (int movDerecha = 0; movDerecha <= ultimaPerception.getCantMovimientosDerecha(); movDerecha++)
            celdasConocidas[posX + movDerecha][posY] = EstadoCelda.VACIA;

        // Lugares libres para mover a Arriba
        for (int movArriba = 0; movArriba <= ultimaPerception.getCantMovimientosArriba(); movArriba++)
            celdasConocidas[posX][posY - movArriba] = EstadoCelda.VACIA;

        // Lugares libres para mover a Abajo
        for (int movAbajo = 0; movAbajo <= ultimaPerception.getCantMovimientosAbajo(); movAbajo++)
            celdasConocidas[posX][posY + movAbajo] = EstadoCelda.VACIA;


        this.posicionActual = ultimaPerception.getPosicionActual();
        celdasConocidas[(int) this.posicionActual.getX()][(int) this.posicionActual.getY()] = EstadoCelda.CAPERUCITA;

        // Si sabe donde esta el camino de flores lo guarda
        if (!ultimaPerception.getPosicionFlores().equals(UNKNOWN)) {
            this.posicionFlores = ultimaPerception.getPosicionFlores();
            celdasConocidas[(int) this.posicionFlores.getX()][(int) this.posicionFlores.getY()] = EstadoCelda.FLORES;
        }

        // Agrego las posiciones de los dulces percibidos
        for (Point celdaDulce : ultimaPerception.getPosicionesDulces()) {
            if (!dulcesJuntados.contains(celdaDulce)) posicionesDulces.add(celdaDulce); //TODO revisar si esto funciona
            celdasConocidas[(int) celdaDulce.getX()][(int) celdaDulce.getY()] = EstadoCelda.DULCE;
        }

        // Agrego las posiciones de los árboles percibidos
        for (Point celdaArbol : ultimaPerception.getPosicionesArboles()) {
            posicionesArboles.add(celdaArbol);
            celdasConocidas[(int) celdaArbol.getX()][(int) celdaArbol.getY()] = EstadoCelda.ARBOL;
        }

        /*TODO Pongo la posicion del lobo, puede ser que antes sabia donde esta,
        pero como el lobo se mueve, puede que ya no este a la vista,
                por lo que para el estado de caperucita y su escenario no se sabe donde esta, solo lo sabe el ambiente
        Ver aca no se si que el estado actual ya es un objeto copiado o se crea siempre */


        // Si se donde esta ahora, lo agrego
        if (!ultimaPerception.getPosicionLobo().equals(UNKNOWN)) {
            this.posicionLobo = ultimaPerception.getPosicionLobo();
            celdasConocidas[(int) this.posicionLobo.getX()][(int) this.posicionLobo.getY()] = EstadoCelda.LOBO;
        }

        // TODO Borro la posicion vieja del lobo
        if (!this.posicionLobo.equals(UNKNOWN))
            celdasConocidas[(int) this.posicionLobo.getX()][(int) this.posicionLobo.getY()] = EstadoCelda.VACIA;


        getEscenarioJuego().setCeldas(celdasConocidas);
        // Actualizo las posiciones del escenario


        //TODO En este momento ya tiene actualizado el estado, debe invocar al metodo de busqueda de caminos
        // Search donde elije que operador aplicar

    }

    @Override
    public String toString() {
        return "\n" + Consola.textoColoreadoRed("- Posición actual: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoRed("- Posición lobo: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreadoRed("- Posición flores: " + Consola.celdaToString(posicionFlores)) +
                "\n" + Consola.textoColoreadoRed("- Posición dulces: " + Consola.celdaToString(posicionesDulces)) +
                "\n" + Consola.textoColoreadoRed("- Vidas restantes: " + vidasRestantes) +
                "\n ---------------------------------------------------- \n";
    }

    public HashSet<Point> getDulcesJuntados() {
        return dulcesJuntados;
    }

    public void setDulcesJuntados(HashSet<Point> dulcesJuntados) {
        this.dulcesJuntados = dulcesJuntados;
    }

    public Point getPosicionCaperucita() {
        return this.posicionActual;
    }

    public void setPosicionCaperucita(Point posicion) {
        this.posicionActual = posicion;
    }

    public Point getPosicionFlores() {
        return this.posicionFlores;
    }

    public void setPosicionFlores(Point posicion) {
        this.posicionFlores = posicion;
    }

    public HashSet<Point> getPosicionesDulces() {
        return this.posicionesDulces;
    }

    public void setPosicionesDulces(HashSet<Point> posiciones) {
        this.posicionesDulces = posiciones;
    }

    public int getVidasRestantes() {
        return this.vidasRestantes;
    }

    public void setVidasRestantes(int vidas) {
        this.vidasRestantes = vidas;
    }

    public CaperucitaPerception getUltimaPerception() {
        return ultimaPerception;
    }

    public void setUltimaPerception(CaperucitaPerception ultimaPerception) {
        this.ultimaPerception = ultimaPerception;
    }

    public Escenario getEscenarioJuego() {
        return escenarioJuego;
    }

    public Ambiente getAmbienteActual() {
        return ambienteActual;
    }

    public void setAmbienteActual(Ambiente ambienteActual) {
        this.ambienteActual = ambienteActual;
    }

    public Point getPosicionLobo() {
        return posicionLobo;
    }

    public void setPosicionLobo(Point posicionLobo) {
        this.posicionLobo = posicionLobo;
    }

    public HashSet<Point> getPosicionesArboles() {
        return posicionesArboles;
    }

    public void setPosicionesArboles(HashSet<Point> posicionesArboles) {
        this.posicionesArboles = posicionesArboles;
    }

    public void setEscenarioJuego(Escenario escenarioJuego) {
        this.escenarioJuego = escenarioJuego;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadoCaperucita)) return false;
        EstadoCaperucita that = (EstadoCaperucita) o;
        return vidasRestantes == that.vidasRestantes && Objects.equals(escenarioJuego, that.escenarioJuego) && Objects.equals(ambienteActual, that.ambienteActual) && Objects.equals(posicionActual, that.posicionActual) && Objects.equals(posicionFlores, that.posicionFlores) && Objects.equals(posicionLobo, that.posicionLobo) && Objects.equals(posicionesDulces, that.posicionesDulces) && Objects.equals(ultimaPerception, that.ultimaPerception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(escenarioJuego, ambienteActual, posicionActual, posicionFlores, posicionLobo, posicionesDulces, vidasRestantes, ultimaPerception);
    }
}

