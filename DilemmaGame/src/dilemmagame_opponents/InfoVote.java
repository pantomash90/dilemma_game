package dilemmagame_opponents;

import dilemmagame.Message;

public class InfoVote extends Info {

    InfoVote(String value) {
        super("Last vote");
        infoLabel.setText(value);
    }

    @Override
    public void update(Message message) {
        if(message.getVote() != null)
            infoLabel.setText( message.getVote().toString() );
        else
            infoLabel.setText("no vote");
    }
}
