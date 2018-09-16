package dilemmagame_opponents;

import dilemmagame.Message;
import dilemmagame_voteButtons.VoteColor;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private Message message;
    private List<Info> opInf;
    
    public InfoPanel(Message message) {
        this.message = message;
        opInf = new ArrayList();
        
        setLayout(new GridLayout(0, 1));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), ""));
    }
    
    public InfoPanel addInfoName() {
        Info o = new InfoName( message.getName() );
        add(o);
        opInf.add(o);
        return this;
    }
    
    public InfoPanel addInfoPoints() {
        Info o = new InfoPoints( message.getPoints() );
        add(o);
        opInf.add(o);
        return this;
    }
    
    public InfoPanel addInfoVote() {
        VoteColor vc = message.getVote();
        String s = (vc == null) ? "no vote" : vc.toString();
        
        Info o = new InfoVote( s );
        add(o);
        opInf.add(o);
        return this;
    }
    
    public InfoPanel addInfoTime() {
        Info o = new InfoTime( message.getTime() );
        add(o);
        opInf.add(o);
        return this;
    }
    
    public List<Info> getOpInf() {
        return opInf;
    }
    
    public VoteColor getVote() {
        return message.getVote();
    }
}