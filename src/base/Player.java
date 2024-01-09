package base;

import character.Brimstone;
import character.Jett;
import character.Sage;
import javafx.scene.control.Label;
import pane.BoardPane;
import pane.BottomPane;
import pane.RootPane;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Agent> ownedAgent = new ArrayList<>();
    private final boolean isAttacker;

    public Player(String name, boolean isAttacker) {
        this.name = name;
        this.isAttacker = isAttacker;
        addAgent();
        visualizeAgent();
    }

    private void addAgent() {
        if (isAttacker) {
            ownedAgent.add(new Jett(3,12,true));
            ownedAgent.add(new Sage(3,9,true));
            ownedAgent.add(new Brimstone(3,15,true));
        }
        else{
            ownedAgent.add(new Jett(17,16,false));
            ownedAgent.add(new Sage(19,14,false));
            ownedAgent.add(new Brimstone(19,16,false));
        }
    }

    private void visualizeAgent() {
        for (Agent oneAgent : ownedAgent) {
            oneAgent.visualizeAgent();
        }
    }
    void processEachRound() {
        addEventHandler();
    }

    private void addEventHandler() {
        for (Agent oneAgent : ownedAgent) {
            Label target = BoardPane.getInstance().getOneCell(oneAgent.getCurrentLocation());
            target.setOnMouseClicked(event -> {
                BottomPane.getInstance().prepareForAgent(oneAgent);
                RootPane.getInstance().showBottomPane();
            });
        }
    }

    List<Agent> getOwnedAgent() {
        return ownedAgent;
    }
}
