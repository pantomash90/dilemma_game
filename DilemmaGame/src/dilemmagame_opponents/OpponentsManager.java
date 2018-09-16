package dilemmagame_opponents;

import dilemmagame.Message;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.JPanel;

public class OpponentsManager extends JPanel {
    
    private List<Message> opponentsMessages;
    private List<InfoPanel> opponents;
    
    public OpponentsManager() {
        setLayout(new GridLayout(1, 0));
        opponents = new ArrayList();
    }
    
    public void setOpponents(List opponentsMessages) {
        this.opponentsMessages = opponentsMessages;
        update();
    }
    
    private void update() {
        Message first;
        
        try { first = opponentsMessages.get(0); }
        catch(NullPointerException | IndexOutOfBoundsException ex) { first = null; }
        
        removeAll();
        
        if(first != null && first.getTime() == 10) {
            for(int i = 0; i < opponentsMessages.size(); i++) {
                Message m = opponentsMessages.get(i);
                add(new InfoPanel(m).addInfoName().addInfoPoints().addInfoVote());
            }            
            repaint();
        }

    }
}
