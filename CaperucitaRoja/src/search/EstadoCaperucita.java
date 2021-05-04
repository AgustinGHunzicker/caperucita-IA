package search;

import domain.ConsoleDebug;
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

    private Escenario escenario;
    private Point posicionActual;
    private HashSet<Point> dulcesJuntados;
    private HashSet<Point> flores;
    private int vidasRestantes;

    public EstadoCaperucita() {
        initState();
    }

    @Override
    public void initState() {
        escenario = new Escenario();
        posicionActual = UNKNOWN;
        dulcesJuntados = new HashSet<>();
        flores = new HashSet<>();
        vidasRestantes = 3;
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita newState = new EstadoCaperucita();

        EstadoCelda[][] newCeldas = new EstadoCelda[Escenario.LIMITE_DERECHA + 1][Escenario.LIMITE_ABAJO + 1];
        for (int movHoriz = 0; movHoriz <= Escenario.LIMITE_DERECHA; movHoriz++) {
            for (int movVert = 0; movVert <= Escenario.LIMITE_ABAJO; movVert++) {
                newCeldas[movHoriz][movVert] = this.getEscenario().getCeldas()[movHoriz][movVert];
            }
        }
        newState.getEscenario().setCeldas(newCeldas);
        //newState.setEscenario(this.escenario);


        newState.setPosicionCaperucita(new Point(this.getPosicionCaperucita().x, this.getPosicionCaperucita().y));

        HashSet<Point> dulces = new HashSet<>();
        for (Point dulce : this.getDulcesJuntados())
            dulces.add(new Point(dulce.x, dulce.y));
        newState.setDulcesJuntados(dulces);


        HashSet<Point> flores = new HashSet<>();
        for (Point flor : this.getFlores())
            flores.add(new Point(flor.x, flor.y));
        newState.setFlores(flores);

        newState.setVidasRestantes(this.getVidasRestantes());

        return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        CaperucitaPerception perception = (CaperucitaPerception) p;
        this.setPosicionCaperucita(perception.getPosicionActual());


        if (!perception.getPosicionLobo().equals(UNKNOWN))
            this.getEscenario().setPosicionCelda(perception.getPosicionLobo().x, perception.getPosicionLobo().y, EstadoCelda.LOBO);


        for (Point dulce : perception.getPosicionesDulces()) {
            this.getEscenario().setPosicionCelda(dulce.x, dulce.y, EstadoCelda.DULCE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadoCaperucita)) return false;
        EstadoCaperucita that = (EstadoCaperucita) o;
        return vidasRestantes == that.vidasRestantes && Objects.equals(posicionActual, that.posicionActual) && Objects.equals(dulcesJuntados, that.dulcesJuntados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posicionActual, dulcesJuntados, vidasRestantes);
    }

    @Override
    public String toString() {
        if (ConsoleDebug.get().isDebugging()) {
            return "\n"+Consola.textoColoreadoRed("- Posición actual: " + Consola.celdaToString(posicionActual)) +
                    "\n" + Consola.textoColoreadoRed("- Dulces juntados: " + Consola.celdaToString(dulcesJuntados)) +
                    "\n" + Consola.textoColoreadoRed("- Vidas restantes: " + vidasRestantes) +
                    "\n" + escenario +
                    "\n ----------------------------------------------------\n";
        }


        return Consola.textoColoreadoRed("- Posición actual: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoRed("- Dulces juntados: " + Consola.celdaToString(dulcesJuntados)) +
                "\n" + Consola.textoColoreadoRed("- Vidas restantes: " + vidasRestantes);
    }

    public HashSet<Point> getDulcesJuntados() {
        return dulcesJuntados;
    }

    public void setDulcesJuntados(HashSet<Point> dulcesJuntados) {
        this.dulcesJuntados = dulcesJuntados;
    }

    public void addDulceJuntado(Point dulceJuntado) {
        this.dulcesJuntados.add(dulceJuntado);
    }

    public Point getPosicionCaperucita() {
        return this.posicionActual;
    }

    public void setPosicionCaperucita(Point posicion) {
        this.posicionActual = posicion;
    }

    public int getVidasRestantes() {
        return this.vidasRestantes;
    }

    public void reiniciarNivel(Point posicionReiniciada) {
        this.posicionActual = posicionReiniciada;
        this.dulcesJuntados = new HashSet<>();
        this.vidasRestantes--;
    }

    public void setVidasRestantes(int vidasRestantes) {
        this.vidasRestantes = vidasRestantes;
    }

    public Escenario getEscenario() {
        return escenario;
    }

    public void setEscenario(Escenario escenario) {
        this.escenario = escenario;
    }

    public HashSet<Point> getFlores() {
        return flores;
    }

    public void setFlores(HashSet<Point> flores) {
        this.flores = flores;
    }
}

