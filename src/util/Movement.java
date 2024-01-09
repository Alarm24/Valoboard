package util;

import base.Agent;
import base.Player;

public class Movement {
    private final Player player;
    private final Agent agent;
    private final Coordinate from;
    private final Coordinate to;

    public Movement(Player player,Agent agent, Coordinate from, Coordinate to) {
        this.player = player;
        this.agent = agent;
        this.from = from;
        this.to = to;
        processMovement();
    }

    private void processMovement() {
        agent.clearAgentIcon();
        if(!agent.isSlowState()){
            agent.setCurrentLocation(to);
        }
        agent.visualizeAgent();
        agent.setIsMoved();
    }

    public String toString() {
        return " move " + agent.toString() + " form " + from.toString() + " to " + to.toString();
    }

}
