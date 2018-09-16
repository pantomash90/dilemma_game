package dilemmagame_voteButtons;

import javax.swing.JButton;

public abstract class VoteButton extends JButton {
    protected VoteColor vote;
    protected InputManager input;
    
    VoteButton(InputManager input) {
        this.input = input;
        
        this.setText("");
        this.setOpaque(true);
        this.addActionListener((e) -> {
            input.setVote(vote);
        });
    }
}
