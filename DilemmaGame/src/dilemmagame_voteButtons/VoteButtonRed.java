package dilemmagame_voteButtons;

import java.awt.Color;

public class VoteButtonRed extends VoteButton {

    VoteButtonRed(InputManager input) {
        super(input);
        this.vote = VoteColor.RED;
        this.setBackground(Color.red);
    }
}
