package dilemmagame;

import dilemmagame_voteButtons.VoteColor;
import java.io.Serializable;

public class Message implements Serializable {

    private String name;
    private int points, time;
    private VoteColor vote;

    public Message(String name, int points, int time, VoteColor vote) {
        this.name = name;
        this.points = points;
        this.vote = vote;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        //System.out.println("player: " + this.name + " gets " + points + " points");
        this.points += points;
    }

    public VoteColor getVote() {
        return vote;
    }

    public void setVote(VoteColor vote) {
        this.vote = vote;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "name: " + this.name + " points: " + this.points + " time: " + this.time + " vote: " + this.vote;
    }
}
