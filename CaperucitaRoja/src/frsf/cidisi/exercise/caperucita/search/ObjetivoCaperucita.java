package frsf.cidisi.exercise.caperucita.search;

import enumeration.Consola;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

import java.awt.*;

public class ObjetivoCaperucita extends GoalTest {

    @Override
    public boolean isGoalState(AgentState agentState) {

        EstadoCaperucita estadoCaperucita = (EstadoCaperucita) agentState;

        //System.out.println(Consola.textoColoreadoGreen("isGoalState = "+(estadoCaperucita.getPosicionCaperucita().equals(estadoCaperucita.getPosicionFlores()) && estadoCaperucita.getVidasRestantes() > 0))+". Vidas: "+estadoCaperucita.getVidasRestantes());

        System.out.println(Consola.textoColoreadoGreen("isGoalState, flores: "+estadoCaperucita.getPosicionFlores().toString()));
        System.out.println(Consola.textoColoreadoGreen("isGoalState, caperucita: "+estadoCaperucita.getPosicionCaperucita().toString()));

        System.out.println("flores:" + estadoCaperucita.getFloresJuntadas());
        if (estadoCaperucita.getFloresJuntadas() > 0 && estadoCaperucita.getVidasRestantes() > 0)
            return true;
        else
            return false;
        //return estadoCaperucita.getPosicionCaperucita().equals(estadoCaperucita.getPosicionFlores()) && estadoCaperucita.getVidasRestantes() > 0;
    }



    @Override
    public String toString() {
        return "ObjetivoCaperucita";
    }
}