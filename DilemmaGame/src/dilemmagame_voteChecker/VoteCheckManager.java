package dilemmagame_voteChecker;

import dilemmagame.Message;
import dilemmagame.PlayerSocket;
import dilemmagame_voteButtons.VoteColor;
import java.util.*;

public class VoteCheckManager {

    private Map<VoteColor, List<PlayerSocket>> votes = new HashMap<>();

    public VoteCheckManager() {
        votes.put(VoteColor.BLACK, new ArrayList<>());
        votes.put(VoteColor.WHITE, new ArrayList<>());
        votes.put(VoteColor.RED, new ArrayList<>());
        votes.put(null, new ArrayList<>());
    }

    private void clearVotes() {
        for (VoteColor v : votes.keySet()) {
            votes.get(v).clear();
        }
    }
    
    private void buildVotesArrays(List<PlayerSocket> currentPlayers) {
        for (PlayerSocket p : currentPlayers) {
            votes.get(p.getMessage().getVote()).add(p);
        }
    }

    public void checkVotes(List<PlayerSocket> currentPlayers) {
        clearVotes();
        buildVotesArrays(currentPlayers);

        //check black
        if (votes.get(VoteColor.BLACK).size() == 1) {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.BLACK).contains(p)) {
                    m.addPoints(10);
                } else {
                    m.addPoints(-1);
                }
            }
        } else {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.BLACK).contains(p)) {
                    m.addPoints(-5);
                } else {
                    m.addPoints(2);
                }
            }
        }

        //check white
        if (votes.get(VoteColor.WHITE).size() < currentPlayers.size() / 2) {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.WHITE).contains(p)) {
                    m.addPoints(1);
                } else {
                    m.addPoints(-1);
                }
            }
        } else {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.WHITE).contains(p)) {
                    m.addPoints(-1);
                } else {
                    m.addPoints(1);
                }
            }
        }

        //check red
        if (votes.get(VoteColor.RED).size() == currentPlayers.size()) {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.RED).contains(p)) {
                    m.addPoints(-2);
                } else {
                    m.addPoints(0);
                }
            }
        } else {
            for (PlayerSocket p : currentPlayers) {
                Message m = p.getMessage();
                if (votes.get(VoteColor.RED).contains(p)) {
                    m.addPoints(1);
                } else {
                    m.addPoints(0);
                }
            }
        }

        //check nulls
        for (PlayerSocket p : votes.get(null)) {
            Message m = p.getMessage();
            m.addPoints(-5);
        }

    }
}
