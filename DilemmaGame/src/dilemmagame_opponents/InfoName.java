package dilemmagame_opponents;

import dilemmagame.Message;

public class InfoName extends Info {
    
    InfoName(String value) {
        super("Name");
        infoLabel.setText(value);
    }

    @Override
    public void update(Message message) {
        infoLabel.setText(message.getName());
    }
}
