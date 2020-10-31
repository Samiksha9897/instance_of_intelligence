package server_sharenow;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static ServerSocket server=null;
    private static Socket clientSocket=null;

    public static void main(String[] args){
        //int counter=0;
        try{
            server=new ServerSocket(5000);

            System.out.println("Server Started!!");

        }
        catch(Exception e){
            System.out.println("Server already in use");
            System.exit(1);
        }
        while(true){
            try{
                //counter++;
                clientSocket= server.accept();
                System.out.println("Client " + " is Connected " + clientSocket);

                Thread t=new Thread(new ClientHandler(clientSocket));
                t.start();
            }
            catch(Exception e){
                System.out.println("Error in Connection!");
            }
        }
    }
}
