
import com.simon.websocket.data.Message;
import com.simon.websocket.data.MessageDecoder;
import com.simon.websocket.data.MessageEncoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
@ServerEndpoint(value = "/echo", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class EchoServer {

    private static Queue<Session> allSessions = new ConcurrentLinkedQueue<Session>();


  
    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a con-nection");
        Message message = new Message(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "User has connected")
                .build());
        sendAll(message);
        try {
            Message connectedMessage = new Message(Json.createObjectBuilder()
                    .add("type", "text")
                    .add("data", "User has connected")
                    .build());
            session.getBasicRemote().sendObject(connectedMessage);
        } catch (IOException | EncodeException ex) {
        }
        allSessions.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
        System.out.println("Message from " + session.getId() + ": " + message);
        sendAll(message);
    }

    @OnClose
    public void onClose(Session session) {
        allSessions.remove(session);
        System.out.println("Session " + session.getId() + " has end-ed");
        Message message = new Message(Json.createObjectBuilder()
                .add("type", "text")
                .add("data", "User has disconnected")
                .build());
        sendAll(message);
    }

    @OnError
    public void error(Session session, Throwable t) {
        allSessions.remove(session);
        System.err.println("Error on session " + session.getId());
    }

   
    private void sendAll(Message message) {
        for (Session s : allSessions) {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException ex) {
            }
        }
    }
}


