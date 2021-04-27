package frsf.cidisi.exercise.caperucita.search;

import enumeration.Consola;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;
        System.out.println(Consola.textoColoreadoGreen("isGoalState, flores: "+estadoCaperucita.getPosicionFlores().toString() +" - caperucita " +estadoCaperucita.getPosicionCaperucita().toString()));

        for (Point p : estadoCaperucita.getPosicionFlores())
            if (p.equals(estadoCaperucita.getPosicionCaperucita())) return true;
        return estadoCaperucita.getFloresJuntadas() > 0 && estadoCaperucita.getVidasRestantes() > 0;
    }

    @Override
    public String toString() {
        return "ObjetivoCaperucita";
    }
}