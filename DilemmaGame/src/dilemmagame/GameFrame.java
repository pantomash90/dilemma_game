package dilemmagame;

import dilemmagame_opponents.Info;
import dilemmagame_opponents.OpponentsManager;
import dilemmagame_opponents.PlayerInfoObserver;
import dilemmagame_opponents.PlayerInfoSubject;
import dilemmagame_voteButtons.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

public class GameFrame extends JFrame implements PlayerInfoSubject {

    private final OpponentsManager opponents;
    private final InputManager input;

    private final String name;
    private int points;
    
    private List<PlayerInfoObserver> observers;

    GameFrame(String name) {
        this.name = name;
        this.points = 0;

        setTitle(name);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        opponents = new OpponentsManager();
        add(opponents);
        
        input = new InputManager();
        add(input);
        
        addObserversList( input.setInfoPanel(new Message(this.name, this.points, 0, null)) );
        
        pack();
        setSize(640, 480);
    }

    public void updateOpponents(List opponents) {
         this.opponents.setOpponents(opponents);
    }

    public Message generateNewMessage() {
        VoteColor v = input.getVote();
        input.setVote(null);
        return new Message(this.name, this.points, 0, v);
    }

    @Override
    public void addObserversList(List<Info> observers) {
        this.observers = new ArrayList();
        
        for(Info o : observers) {
            this.observers.add(o);
        }
    }

    @Override
    public void notifyObservers(Message message) {
        for(PlayerInfoObserver o : observers) {
            o.update(message);
        }
    }
}
