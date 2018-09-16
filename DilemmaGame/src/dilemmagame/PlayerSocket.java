package dilemmagame;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class PlayerSocket {
    
    private final Socket client;
    private final ObjectInputStream fromClient;
    private final ObjectOutputStream toClient;
    private Message message;
    private boolean isClosed;
    
    PlayerSocket(Socket client) throws IOException {
        this.client = client;
        fromClient = new ObjectInputStream(this.client.getInputStream());
        toClient = new ObjectOutputStream(this.client.getOutputStream());
        message = new Message("aaa", 0, 999, null);
        isClosed = false;
        
        client.setSoTimeout(50);
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void send(List list) {
        try {
            //System.out.println("sending to client " + message);
            toClient.reset();
            toClient.writeObject(list);
        } catch (IOException ex) {isClosed = true;}
    }
    
    public void send(Message message) {
        try {
            //System.out.println("sending to client " + message);
            toClient.reset();
            toClient.writeObject(message);
        } catch (IOException ex) {isClosed = true;}
    }
    
    public void receive() {
        Message tmp_message = this.message;
        try {
            message = (Message)fromClient.readObject();
            //System.out.println("receiving from client - player " + message.getName() + " voted " + message.getVote());
        } catch (ClassNotFoundException ex) {
            message = tmp_message;
        } catch (IOException ex) {isClosed = true;} //client closed
    }
    
    public Message getMessage() {
        return this.message;
    }
}
