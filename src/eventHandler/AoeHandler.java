package eventHandler;
import base.Agent;
import character.Brimstone;
import character.Sage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pane.RootPane;
import util.Coordinate;
import base.AgentManager;

public class AoeHandler implements EventHandler<MouseEvent>{
    private final Agent agent;
    private final Coordinate from;
    private final Coordinate destination;
    private final AgentManager agentManager;
    AoeHandler(Agent agent, Coordinate destination){
        this.agent = agent;
        this.from = agent.getCurrentLocation();
        this.agentManager = AgentManager.getInstance();
        this.destination = destination;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(agent instanceof Sage){
            ((Sage) agent).skillSage();
            agentManager.goNextIteration();
            RootPane.getInstance().hideBottomPane();
        }
        if(agent instanceof Brimstone){
            ((Brimstone) agent).skillBrimstone();
            agentManager.goNextIteration();
            RootPane.getInstance().hideBottomPane();
        }
    }

}
