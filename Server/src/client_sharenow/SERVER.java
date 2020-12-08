package client_sharenow;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class SERVER {
    private  client_sharenow.SocketServer ss;
    private final SimpleDateFormat sdf;
    int counter = 0;


    private ArrayList<ClientHandler> a1, a2;
    int port;


    public SERVER(int port, SocketServer socketServer, SimpleDateFormat sdf) {
        this(port, null);
    }

    public SERVER(int port,client_sharenow.SocketServer ss) {

        this.port = port;
        //this.ss = ss;
        a1 = new ArrayList<ClientHandler>();
        sdf = new SimpleDateFormat("HH:mm:ss");
        a2 = new ArrayList<ClientHandler>();
    }

    public void start() {
        boolean keepgoing = true;

        try {
            ServerSocket server = new ServerSocket(port);
            ss.Filesdisplay();
            while (keepgoing) {
                counter++;
                Socket clientSocket = server.accept();
                display("Client " + counter + " is Connected");

                ClientHandler t = new ClientHandler(clientSocket, ss, sdf, counter);
                a1.add(t);
                t.start();
            }
        } catch (Exception e) {
            display("Error in Connection!");
        }
    }

    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        if (ss == null)
            System.out.println(time);
        else
            ss.appendEvent(time + "\n");
    }

}

