package dilemmagame;

import dilemmagame_voteChecker.VoteCheckManager;
import java.util.*;

public class GameTracker {

    private final List<PlayerSocket> currentPlayers;
    private final List<PlayerSocket> disconnectedPlayers;
    private final VoteCheckManager voteManager;

    GameTracker() {
        currentPlayers = new ArrayList<>();
        disconnectedPlayers = new ArrayList<>();
        voteManager = new VoteCheckManager();
    }

    public void addPlayer(PlayerSocket player) {
        currentPlayers.add(player);
    }
    
    public void checkDisconnected() {
        disconnectedPlayers.clear();
        for (PlayerSocket p : currentPlayers) {
            if (p.isClosed())
                disconnectedPlayers.add(p);
        }
    }

    public void removeDisconnected() {
        for (PlayerSocket p : disconnectedPlayers) {
            currentPlayers.remove(p);
        }
    }

    public void updateTime(int time) {
        for (PlayerSocket p : currentPlayers) {
            p.getMessage().setTime(time);
        }
    }

    public void send() {
        for (PlayerSocket p : currentPlayers) { //send to
            List list = new ArrayList();

            for (PlayerSocket p2 : currentPlayers) { //send info from
                if ( !disconnectedPlayers.contains(p2) && p != p2 ) {
                    //p2.send(m);// <-- send message directly //working
                    list.add(p2.getMessage());
                }
            }
            
            p.send(p.getMessage());
            p.send(list);
        }
    }

    public void receive() {
        for (PlayerSocket p : currentPlayers) {
            p.receive();
        }
    }

    public void checkVotes() {
        voteManager.checkVotes(currentPlayers);
    }

}
