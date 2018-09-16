package dilemmagame_voteButtons;

import java.awt.Color;

public class VoteButtonWhite extends VoteButton {

    VoteButtonWhite(InputManager input) {
        super(input);
        this.vote = VoteColor.WHITE;
        this.setBackground(Color.white);
    }
}
