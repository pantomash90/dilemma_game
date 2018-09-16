package dilemmagame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class DilemmaGameServer {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 6341;

    private ServerSocket server;
    private Thread acceptingClients;

    private GameTracker tracker;

    DilemmaGameServer() {

        tracker = new GameTracker();

        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

        startAcceptingClients();
        programLoop();

    }

    private void startAcceptingClients() {
        acceptingClients = new Thread(() -> {
            while (true) {
                try {
                    PlayerSocket newPlayer = new PlayerSocket(server.accept());
                    tracker.addPlayer(newPlayer);
                    newPlayer.receive();

                    //TODO check name
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }, "acceptingClients");
        acceptingClients.start();
    }

    private void programLoop() {
        long delta, timerSec = 0;
        int roundCounter = 0, ROUND_LENGTH = 10;
        long lastTime = System.nanoTime();

        while (true) {
            long now = System.nanoTime();
            delta = now - lastTime;
            timerSec += delta;

            if (timerSec >= 1000000000) {
                int current = ROUND_LENGTH - roundCounter++;

                tracker.checkDisconnected();
                tracker.updateTime(current);

                //send time and round's results to clients
                tracker.send();

                //end of round
                if (current == 1) {
                        //receive votes from players
                        tracker.receive();

                        //check votes & update players' points
                        tracker.checkVotes();
                    roundCounter = 0;
                }

                tracker.removeDisconnected();

                timerSec %= 1000000000;
            }

            lastTime = now;
        }
    }

    public static void main(String[] args) {
        new DilemmaGameServer();
    }
}
