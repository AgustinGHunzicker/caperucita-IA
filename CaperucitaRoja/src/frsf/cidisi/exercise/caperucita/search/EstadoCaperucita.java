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

    private final Escenario escenarioJuego;
    private Ambiente ambienteActual;
    private CaperucitaPerception ultimaPerception;
    private Point posicionActual;
    private Point posicionFlores;
    private Point posicionLobo;
    private HashSet<Point> posicionesDulces;
    private int vidasRestantes;


    private static final Point UNKNOWN = new Point(-1, -1);

    public EstadoCaperucita(Ambiente ambiente) {
        ambienteActual = ambiente;
        escenarioJuego = new Escenario();
        posicionActual = ambiente.getEnvironmentState().getPosicionCaperucita();
        posicionFlores = UNKNOWN;
        posicionLobo = UNKNOWN;
        posicionesDulces = new HashSet<>();
        vidasRestantes = 3;
        ultimaPerception = ambiente.getPercept();
        initState(); // TODO hace lo mismo que hace antes
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita newState = new EstadoCaperucita(this.ambienteActual); //TODO: actualizar cuando se tenga el Ambiente
        newState.setVidasRestantes(this.getVidasRestantes());
        newState.setPosicionCaperucita(this.getPosicionCaperucita());
        newState.setPosicionesDulces(this.getPosicionesDulces());
        return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {

        this.ultimaPerception = (CaperucitaPerception) p;

        //Traigo las posiciones conocidas por caperucita hasta el momento
        EstadoCelda[][] escenarioConocido = escenarioJuego.getCeldas();
        int posX = (int) posicionActual.getX();
        int posY = (int) posicionActual.getY();
        Point noExiste = new Point(-1, -1);

        //Lugares libres para mover a la Izquierda
        for (int i = 0; i < ultimaPerception.getCantMovimientosIzquierda(); i++)
            escenarioConocido[posY][posX - i] = EstadoCelda.VACIA;

        //Lugares libres para mover a la Derecha
        for (int i = 0; i < ultimaPerception.getCantMovimientosDerecha(); i++)
            escenarioConocido[posY][posX + i] = EstadoCelda.VACIA;

        //Lugares libres para mover a Arriba
        for (int i = 0; i < ultimaPerception.getCantMovimientosArriba(); i++)
            escenarioConocido[posY + i][posX] = EstadoCelda.VACIA;

        //Lugares libres para mover a Abajo
        for (int i = 0; i < ultimaPerception.getCantMovimientosAbajo(); i++)
            escenarioConocido[posY - i][posX] = EstadoCelda.VACIA;

        //Si sabe donde esta el camino de flores lo guarda
        if (!ultimaPerception.getPosicionFlores().equals(noExiste)) {
            //Se lo carga a caperucita
            this.posicionFlores = ultimaPerception.getPosicionFlores();
            //Se actualiza el escenario
            escenarioConocido[(int) this.posicionFlores.getX()][(int) this.posicionFlores.getY()] = EstadoCelda.FLORES;
        }

        // Agrego las posiciones de los dulces percibidos
        for (Point i : ultimaPerception.getPosicionesDulces()) {
            //Se lo agrego a caperucita
            posicionesDulces.add(i);
            //Actualizo el escenario
            escenarioConocido[(int) i.getX()][(int) i.getY()] = EstadoCelda.DULCE;
        }

        //TODO Pongo la posicion del lobo, puede ser que antes sabia donde esta,
        // pero como el lobo se mueve, puede que ya no este a la vista,
        // por lo que para el estado de caperucita y su escenario no se sabe donde esta, solo lo sabe el ambiente
        //Ver aca no se si que el estado actual ya es un objeto copiado o se crea siempre

        //Borro la posicion vieja del lobo
        if (!this.posicionLobo.equals(noExiste))
            escenarioConocido[(int) this.posicionLobo.getX()][(int) this.posicionLobo.getY()] = EstadoCelda.VACIA;

        //Si se donde esta ahora, lo agrego
        if (!ultimaPerception.getPosicionLobo().equals(noExiste)) {
            this.posicionLobo = ultimaPerception.getPosicionLobo();
            escenarioConocido[(int) this.posicionLobo.getX()][(int) this.posicionLobo.getY()] = EstadoCelda.LOBO;
        }

        //Actualizo las posciones del escenario
        this.escenarioJuego.setCeldas(escenarioConocido);

        //TODO En este momento ya tiene actualizado el estado, debe invocar al metodo de busqueda de caminos
        // Search donde elije que operador aplicar

    }

    @Override
    public void initState() {
        //TODO Este método también debe tomar los valores del escenario particular - ademas inicia valores previamente inicializados
        posicionFlores = UNKNOWN;
        posicionesDulces = new HashSet<>();
        vidasRestantes = 3;
    }

    // The following methods are agent-specific:
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

    @Override
    public String toString() {
        return "\n" + Consola.textoColoreado("- Posición actual: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreado("- Posición lobo: " + Consola.celdaToString(posicionLobo)) +
                "\n" + Consola.textoColoreado("- Posición flores: " + Consola.celdaToString(posicionFlores)) +
                "\n" + Consola.textoColoreado("- Posición dulces: " + Consola.celdaToString(posicionesDulces)) +
                "\n" + Consola.textoColoreado("- Vidas restantes: " + vidasRestantes) + "\n";
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

