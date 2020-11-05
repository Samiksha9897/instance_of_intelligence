package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class SERVER {
    private final server.SocketServer ss;
    private final SimpleDateFormat sdf;
    int counter = 0;


    private ArrayList<ClientHandler> a1, a2;
    int port;


    public SERVER(int port) {
        this(port, null);
    }

    public SERVER(int port, server.SocketServer ss) {

        this.port = port;
        this.ss = ss;
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

class ClientHandler extends Thread {


    private final Socket clientSocket;
    server.SocketServer ss;
    SimpleDateFormat sdf;
    int counter,count;
    volatile boolean runin;
    private static BufferedInputStream input;
    private static BufferedOutputStream output;


    private application.ClientGUI client;

    public ClientHandler(Socket clientSocket, server.SocketServer ss, SimpleDateFormat sdf, int counter) {

        this.clientSocket = clientSocket;
        this.ss = ss;
        this.sdf = sdf;
        this.counter = counter;
        runin=true;
        count=0;
        try {
            input = new BufferedInputStream(clientSocket.getInputStream());
            output = new BufferedOutputStream(clientSocket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        if (ss == null)
            System.out.println(time);
        else
            ss.appendEvent(time + "\n");
    }

    public void run() {

        try {


            InputStreamReader is=new InputStreamReader(input);
            BufferedReader in=new BufferedReader(is);
            String clientoption;

            while (runin) {
                if((clientoption = in.readLine()) == null){
                    break;
                }

                switch (clientoption) {
                    case "1" :

                        receivefiles();
                        continue;
                    case "2":
                        String fileName;
                        if((fileName = in.readLine()) != null)
                            sendfiles(fileName);
                        break;
                    case "3":
                        runin=false;
                        break;
                    default:
                        display("Wrong choice choosen");
                        break;

                }
            }

        } catch (IOException e) {

        }
    }

    public void FilesDisplay(String FileName, long size,String extension) {
        File file = new File(FileName);
        String str = file + " " + "Client " + counter + " " + size+ " " +extension ;
        ss.listfiles(str);

    }

    public void receivefiles(){

        try{

            int bytesread,time=10;
            DataInputStream dis=new DataInputStream(input);

            String FileName=dis.readUTF();
            String extension = "";
            int i=FileName.lastIndexOf('.');
            if(i>= 0)
                extension=FileName.substring(i+1);

            File file=new File("C:\\Users\\lenovo\\IdeaProjects\\ShareNow\\Files",FileName);

            FileOutputStream fos=new FileOutputStream(file);

            long size=dis.readLong();
            byte[] buffer=new byte[1024];
            while(size>0 && (bytesread=dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesread);

                size -= bytesread;

            }
            fos.flush();

            display(FileName + " is received from Client " + counter);
            FilesDisplay(FileName,size,extension);


        }

        catch(IOException e){
            e.printStackTrace();


        }

    }
    public void sendfiles(String fileName){
        try{
            int second = 0;

            File myfile=new File("C:\\Users\\lenovo\\IdeaProjects\\ShareNow\\Files",fileName);
            byte mybyte[]=new byte[(int)myfile.length()];

            System.out.println(myfile.getAbsolutePath());
            FileInputStream fis = new FileInputStream(myfile);

            DataOutputStream dos=new DataOutputStream(output);
            dos.writeUTF(myfile.getName());
            dos.flush();
            dos.writeLong(mybyte.length);
            dos.flush();
            int read=0;
            while((read =fis.read()) != -1)
                dos.writeByte(read);

            dos.flush();

            display(fileName +" is sent to the Client "+ counter);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }




}

