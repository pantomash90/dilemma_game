package dilemmagame_opponents;

import dilemmagame.Message;
import java.util.List;

public interface PlayerInfoSubject {
    public void addObserversList(List<Info> observers);
    public void notifyObservers(Message message);
}
