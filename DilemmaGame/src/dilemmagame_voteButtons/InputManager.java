package dilemmagame_voteButtons;

import dilemmagame.Message;
import dilemmagame_opponents.Info;
import dilemmagame_opponents.InfoPanel;
import dilemmagame_opponents.PlayerInfoObserver;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;

public class InputManager extends JPanel {
    
    private VoteButton btnBlack, btnWhite, btnRed;
    private VoteColor vote;
    
    public InputManager() {
        btnBlack = new VoteButtonBlack(this);
        btnWhite = new VoteButtonWhite(this);
        btnRed = new VoteButtonRed(this);
        vote = null;
        
        setSize(640, 120);
        setLayout(new GridLayout(1, 4));
        add(btnBlack);
        add(btnWhite);
        add(btnRed);
        setVisible(true);
    }
    
    public List<Info> setInfoPanel(Message message) {
        InfoPanel o = new InfoPanel(message).addInfoName().addInfoPoints().addInfoVote().addInfoTime();
        add(o);
        return o.getOpInf();
    }
    
    public void setVote(VoteColor vote) {
        this.vote = vote;
    }
    
    public VoteColor getVote() {
        return this.vote;
    }
}
