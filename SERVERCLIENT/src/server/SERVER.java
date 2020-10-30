package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class SERVER {
    private final SocketServer ss;
    private final SimpleDateFormat sdf;
    int counter = 0;


    private ArrayList<ClientHandler> a1, a2;
    int port;


    public SERVER(int port) {
        this(port, null);
    }

    public SERVER(int port, SocketServer ss) {

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
                display("Client " + counter + " is Connected ");

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
    SocketServer ss;
    SimpleDateFormat sdf;
    int counter,count;
   volatile boolean runin;


    public ClientHandler(Socket clientSocket, SocketServer ss, SimpleDateFormat sdf, int counter) {

        this.clientSocket = clientSocket;
        this.ss = ss;
        this.sdf = sdf;
        this.counter = counter;
        runin=true;
        count=0;
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

                    InputStream input=clientSocket.getInputStream();
                    InputStreamReader is=new InputStreamReader(input);
                    BufferedReader in=new BufferedReader(is);
            String clientoption;

            while (runin) {
                if((clientoption = in.readLine()) == null){
                    break;
                }

                switch (clientoption) {
                    case "1":
                        receivefiles();
                        continue;
                    case "2":
                        String fileName;

                        while ((fileName = in.readLine()) != null){
                            sendfiles(fileName);
                        }

                        continue;
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
            InputStream input=clientSocket.getInputStream();
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
            BufferedInputStream bis;
            DataInputStream dis;
           FileInputStream fis = new FileInputStream(myfile);
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);
                dis.read(mybyte, 0, mybyte.length);
                OutputStream os= clientSocket.getOutputStream();
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeUTF(myfile.getName());
            dos.writeLong(mybyte.length);
            dos.write(mybyte,0,mybyte.length);
            display(fileName +" is sent to the Client "+ counter);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }




}

