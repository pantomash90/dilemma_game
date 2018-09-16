package dilemmagame_voteButtons;

import java.awt.Color;

public class VoteButtonBlack extends VoteButton {

    VoteButtonBlack(InputManager input) {
        super(input);
        this.vote = VoteColor.BLACK;
        this.setBackground(Color.black);
        //setToolTipText("aaaaaaa");
    }
}
