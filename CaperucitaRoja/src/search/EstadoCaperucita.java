package search;

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

    private Ambiente ambiente;
    private Escenario escenarioDescubierto;

    private Point posicionActual;

    private HashSet<Point> posicionFlores;
    private HashSet<Point> posicionesDulces;
    private HashSet<Point> posicionesArboles;
    private HashSet<Point> dulcesJuntados;

    private int vidasRestantes;
    private int floresJuntadas;

    private EstadoCelda percepcionCeldasDerecha;
    private EstadoCelda percepcionCeldasIzquierda;
    private EstadoCelda percepcionCeldasArriba;
    private EstadoCelda percepcionCeldasAbajo;

    private int cantMovimientosDerecha;
    private int cantMovimientosIzquierda;
    private int cantMovimientosArriba;
    private int cantMovimientosAbajo;

    public EstadoCaperucita(Ambiente ambiente) {
        this.ambiente = ambiente;
        initState();
    }

    @Override
    public void initState() {
        //TODO Este método también debe tomar los valores del escenario particular - ademas inicia valores previamente inicializados

        // todo USAR ESTO PARA PONER A CAPERUCITA EN SU SITIO INICIAL DESPUES DE MORIR
        escenarioDescubierto = new Escenario();

        posicionActual = UNKNOWN;

        posicionFlores = new HashSet<>();
        posicionesArboles = new HashSet<>();
        posicionesDulces = new HashSet<>();
        dulcesJuntados = new HashSet<>();

        floresJuntadas = 0;
        vidasRestantes = 3;

        percepcionCeldasDerecha = EstadoCelda.ARBOL;
        percepcionCeldasIzquierda = EstadoCelda.ARBOL;
        percepcionCeldasArriba = EstadoCelda.ARBOL;
        percepcionCeldasAbajo = EstadoCelda.ARBOL;

        cantMovimientosDerecha = 0;
        cantMovimientosIzquierda = 0;
        cantMovimientosArriba = 0;
        cantMovimientosAbajo = 0;
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        EstadoCaperucita newState = new EstadoCaperucita(this.getAmbiente());

        EstadoCelda[][] newCeldas = new EstadoCelda[Escenario.LIMITE_DERECHA + 1][Escenario.LIMITE_ABAJO + 1];

        for (int movHoriz = 0; movHoriz <= Escenario.LIMITE_DERECHA; movHoriz++) {
            for (int movVert = 0; movVert <= Escenario.LIMITE_ABAJO; movVert++) {
                newCeldas[movHoriz][movVert] = this.getEscenarioDescubierto().getCeldas()[movHoriz][movVert];
            }
        }
        newState.getEscenarioDescubierto().setCeldas(newCeldas);

        newState.setPosicionCaperucita(new Point(this.getPosicionCaperucita().x, this.getPosicionCaperucita().y));

        HashSet<Point> arboles = new HashSet<>();
        for (Point arbol : this.getPosicionesArboles())
            arboles.add(new Point(arbol.x, arbol.y));
        newState.setPosicionesArboles(arboles);

        for (Point dulce : this.getPosicionesDulces())
            newState.getPosicionesDulces().add(dulce);

        for (Point dulce : this.getDulcesJuntados())
            newState.getDulcesJuntados().add(dulce);

        for (Point flor : this.getPosicionFlores())
            newState.getPosicionFlores().add(flor);

        newState.setFloresJuntadas(this.getFloresJuntadas());
        newState.setVidasRestantes(this.getVidasRestantes());

        //Percepciones de vista hacia los lados
        newState.setPercepcionCeldasAbajo(this.getPercepcionCeldasAbajo());
        newState.setPercepcionCeldasArriba(this.getPercepcionCeldasArriba());
        newState.setPercepcionCeldasDerecha(this.getPercepcionCeldasDerecha());
        newState.setPercepcionCeldasIzquierda(this.getPercepcionCeldasIzquierda());

        //Movimientos posibles hacia los lados
        newState.setCantMovimientosAbajo(this.getCantMovimientosAbajo());
        newState.setCantMovimientosArriba(this.getCantMovimientosArriba());
        newState.setCantMovimientosDerecha(this.getCantMovimientosDerecha());
        newState.setCantMovimientosIzquierda(this.getCantMovimientosIzquierda());

        return newState;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {

        CaperucitaPerception perception = (CaperucitaPerception) p;
        EstadoCelda[][] celdasConocidas = getEscenarioDescubierto().getCeldas();

        this.setPosicionCaperucita(perception.getPosicionActual());

        this.setPercepcionCeldasAbajo(perception.getPercepcionCeldasAbajo());
        this.setPercepcionCeldasArriba(perception.getPercepcionCeldasArriba());
        this.setPercepcionCeldasDerecha(perception.getPercepcionCeldasDerecha());
        this.setPercepcionCeldasIzquierda(perception.getPercepcionCeldasIzquierda());

        this.setCantMovimientosIzquierda(perception.getCantMovimientosIzquierda());
        this.setCantMovimientosDerecha(perception.getCantMovimientosDerecha());
        this.setCantMovimientosArriba(perception.getCantMovimientosArriba());
        this.setCantMovimientosAbajo(perception.getCantMovimientosAbajo());

        // Traigo las posiciones conocidas por caperucita hasta el momento
        int posX = perception.getPosicionActual().x;
        int posY = perception.getPosicionActual().y;
        // Lugares libres para mover a la Izquierda
        for (int movIzquierda = 1; movIzquierda <= perception.getCantMovimientosIzquierda(); movIzquierda++)
            celdasConocidas[posX - movIzquierda][posY] = EstadoCelda.VACIA;

        // Lugares libres para mover a la Derecha
        for (int movDerecha = 1; movDerecha <= perception.getCantMovimientosDerecha(); movDerecha++)
            celdasConocidas[posX + movDerecha][posY] = EstadoCelda.VACIA;

        // Lugares libres para mover a Arriba
        for (int movArriba = 1; movArriba <= perception.getCantMovimientosArriba(); movArriba++)
            celdasConocidas[posX][posY - movArriba] = EstadoCelda.VACIA;

        // Lugares libres para mover a Abajo
        for (int movAbajo = 1; movAbajo <= perception.getCantMovimientosAbajo(); movAbajo++)
            celdasConocidas[posX][posY + movAbajo] = EstadoCelda.VACIA;


        //----------- DULCES -----------
        // todo si no lo juntó que lo agregue // getDulceJuntados()
        for (Point dulce : perception.getPosicionesDulces())
            this.getPosicionesDulces().add(dulce);

        for (Point celdaDulce : this.getPosicionesDulces()) {
            celdasConocidas[celdaDulce.x][celdaDulce.y] = EstadoCelda.DULCE;
        }


        //----------- ÁRBOLES -----------
        for (Point arbol : perception.getPosicionesArboles())
            this.getPosicionesArboles().add(arbol);

        for (Point celdaArbol : this.getPosicionesArboles()) {
            celdasConocidas[celdaArbol.x][celdaArbol.y] = EstadoCelda.ARBOL;
        }

        //----------- FLORES -----------
        for (Point flor : perception.getPosicionFlores())
            this.getPosicionFlores().add(flor);

        for (Point celdaFlor : this.getPosicionFlores()) {
            celdasConocidas[celdaFlor.x][celdaFlor.y] = EstadoCelda.FLORES;
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
        return "\n" + Consola.textoColoreadoRed("- Posición actual: " + Consola.celdaToString(posicionActual)) +
                "\n" + Consola.textoColoreadoRed("- Posiciones flores: " + Consola.celdaToString(posicionFlores)) +
                "\n" + Consola.textoColoreadoRed("- Posiciones dulces: " + Consola.celdaToString(posicionesDulces)) +
                "\n" + Consola.textoColoreadoRed("- Dulces juntados: " + Consola.celdaToString(dulcesJuntados)) +
                "\n" + Consola.textoColoreadoRed("- Flores pisadas: " + floresJuntadas) +
                "\n" + Consola.textoColoreadoRed("- Vidas restantes: " + vidasRestantes) +
                "\n" + Consola.textoColoreadoGreen("- Escenario descubierto") +
                escenarioDescubierto +
                "\n ----------------------------------------------------\n";

    }

    public EstadoAmbiente getEstadoAmbiente() {
        return ambiente.getEnvironmentState();
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public int getFloresJuntadas() {
        return floresJuntadas;
    }

    public void setFloresJuntadas(int floresJuntadas) {
        this.floresJuntadas = floresJuntadas;
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

    public HashSet<Point> getPosicionFlores() {
        return this.posicionFlores;
    }

    public void setPosicionFlores(HashSet<Point> posicion) {
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

    public Escenario getEscenarioDescubierto() {
        return escenarioDescubierto;
    }

   /* public void imprimirEscenarioDescubierto() {
        if (!this.posicionActual.equals(UNKNOWN))
            escenarioDescubierto.setPosicionCelda(this.posicionActual.x, this.posicionActual.y, EstadoCelda.CAPERUCITA);

        //System.out.println(escenarioDescubierto);

        if (!this.posicionActual.equals(UNKNOWN))
            escenarioDescubierto.setPosicionCelda(this.posicionActual.x, this.posicionActual.y, EstadoCelda.VACIA);
    }*/

    public HashSet<Point> getPosicionesArboles() {
        return posicionesArboles;
    }

    public void setPosicionesArboles(HashSet<Point> posicionesArboles) {
        this.posicionesArboles = posicionesArboles;
    }

    public void setEscenarioDescubierto(Escenario escenarioDescubierto) {
        this.escenarioDescubierto = escenarioDescubierto;
    }

    public EstadoCelda getPercepcionCeldasDerecha() {
        return percepcionCeldasDerecha;
    }

    public void setPercepcionCeldasDerecha(EstadoCelda percepcionCeldasDerecha) {
        this.percepcionCeldasDerecha = percepcionCeldasDerecha;
    }

    public EstadoCelda getPercepcionCeldasIzquierda() {
        return percepcionCeldasIzquierda;
    }

    public void setPercepcionCeldasIzquierda(EstadoCelda percepcionCeldasIzquierda) {
        this.percepcionCeldasIzquierda = percepcionCeldasIzquierda;
    }

    public EstadoCelda getPercepcionCeldasArriba() {
        return percepcionCeldasArriba;
    }

    public void setPercepcionCeldasArriba(EstadoCelda percepcionCeldasArriba) {
        this.percepcionCeldasArriba = percepcionCeldasArriba;
    }

    public EstadoCelda getPercepcionCeldasAbajo() {
        return percepcionCeldasAbajo;
    }

    public void setPercepcionCeldasAbajo(EstadoCelda percepcionCeldasAbajo) {
        this.percepcionCeldasAbajo = percepcionCeldasAbajo;
    }

    public int getCantMovimientosDerecha() {
        return cantMovimientosDerecha;
    }

    public void setCantMovimientosDerecha(int cantMovimientosDerecha) {
        this.cantMovimientosDerecha = cantMovimientosDerecha;
    }

    public int getCantMovimientosIzquierda() {
        return cantMovimientosIzquierda;
    }

    public void setCantMovimientosIzquierda(int cantMovimientosIzquierda) {
        this.cantMovimientosIzquierda = cantMovimientosIzquierda;
    }

    public int getCantMovimientosArriba() {
        return cantMovimientosArriba;
    }

    public void setCantMovimientosArriba(int cantMovimientosArriba) {
        this.cantMovimientosArriba = cantMovimientosArriba;
    }

    public int getCantMovimientosAbajo() {
        return cantMovimientosAbajo;
    }

    public void setCantMovimientosAbajo(int cantMovimientosAbajo) {
        this.cantMovimientosAbajo = cantMovimientosAbajo;
    }
}

