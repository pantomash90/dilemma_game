package dilemmagame;

import java.io.*;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;

public class DilemmaGameClient {

    private Socket client;
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;

    private GameFrame game;
    private boolean votingAllowed, newlyCreated;
    private int time;

    DilemmaGameClient() {

        String name = JOptionPane.showInputDialog("type your name");
        if (name.equals(""))
            name = "X";
        
        game = new GameFrame(name);
        votingAllowed = false;
        newlyCreated = true;
        time = 0;

        try {
            client = new Socket(DilemmaGameServer.HOST, DilemmaGameServer.PORT);

            if (client.isConnected()) {
                toServer = new ObjectOutputStream(client.getOutputStream());
                fromServer = new ObjectInputStream(client.getInputStream());
                send();
            }

            while (!client.isClosed()) {
                receive();
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

    }

    public void send() throws IOException {
        toServer.reset();
        if (newlyCreated) {
            toServer.writeObject(game.generateNewMessage());
            votingAllowed = true;
            newlyCreated = false;
        } else if (votingAllowed && this.time == 1) {
            toServer.writeObject(game.generateNewMessage());
            votingAllowed = false;
        }
    }

    public void receive() throws IOException {
        try {
            Object object = fromServer.readObject();
            if(object instanceof Message) {
                Message message = (Message) object;
                
                this.time = message.getTime();
                game.notifyObservers(message);
                game.updateOpponents(null);

                if (this.time == 2)
                    votingAllowed = true;
                
            } else if (object instanceof List) {
                List list = (List) object;
                game.updateOpponents(list);
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        new DilemmaGameClient();
    }
}
