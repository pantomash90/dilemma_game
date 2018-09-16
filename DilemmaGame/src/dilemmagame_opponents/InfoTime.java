package dilemmagame_opponents;

import dilemmagame.Message;

public class InfoTime extends Info {
    
    InfoTime(int value) {
        super("Time");
        infoLabel.setText(Integer.toString(value));
    }

    @Override
    public void update(Message message) {
        infoLabel.setText( Integer.toString( message.getTime() ) );
    }
}
