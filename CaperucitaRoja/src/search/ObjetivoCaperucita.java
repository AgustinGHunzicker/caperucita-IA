package search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;

        boolean estaEnFlores = false;
        for (Point posicionFlor : estadoCaperucita.getPosicionFlores()) {
            if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                estaEnFlores = true;
                break;
            }
        }

        //System.out.println(Consola.textoColoreadoGreen("Objetivo: vidas: "+estadoCaperucita.getVidasRestantes() ));
        return (estadoCaperucita.getFloresJuntadas() > 0 || estaEnFlores ) && estadoCaperucita.getVidasRestantes() > 0;
    }

    @Override
    public String toString() {
        return "ObjetivoCaperucita";
    }
}