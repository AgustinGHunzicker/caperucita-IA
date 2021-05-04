package search;

import domain.ConsoleDebug;
import enumeration.Consola;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {
        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;

        boolean estaEnFlores = false;

        for (Point posicionFlor : estadoCaperucita.getFlores()) {
            if (posicionFlor.equals(estadoCaperucita.getPosicionCaperucita())) {
                estaEnFlores = true;
                break;
            }
        }

        if(ConsoleDebug.get().isShowingLogs())
            System.out.println(Consola.textoColoreadoGreen("Objetivo: "+(estaEnFlores && estadoCaperucita.getVidasRestantes() > 0)));

        return estaEnFlores && estadoCaperucita.getVidasRestantes() > 0;
    }

    @Override
    public String toString() {
        return "ObjetivoCaperucita";
    }
}