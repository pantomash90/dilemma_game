package dilemmagame_opponents;

import dilemmagame.Message;

public class InfoPoints extends Info {

    InfoPoints(int value) {
        super("Points");
        infoLabel.setText(Integer.toString(value));
    }

    @Override
    public void update(Message message) {
        infoLabel.setText( Integer.toString( message.getPoints() ) );
    }
}
