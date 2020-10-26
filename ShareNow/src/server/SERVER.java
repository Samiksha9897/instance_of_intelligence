package server;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;



public class SERVER {
    private static ServerSocket server = null;
    private static Socket clientSocket = null;
    private SocketServer ss;
    private boolean keepgoing;
private SimpleDateFormat sdf;
int counter=0;


    private ArrayList<ClientHandler> a1;
    int port;


    public SERVER(int port) {
        this(port, null);
    }

    public SERVER(int port, SocketServer ss) {

        this.port = port;
        this.ss = ss;
        a1 = new ArrayList<ClientHandler>();
        sdf=new SimpleDateFormat("HH:mm:ss");
    }

    public void start() {
        keepgoing = true;

        try {
            server = new ServerSocket(port);
            while(keepgoing){
                counter++;
            clientSocket = server.accept();
            display("Client " + counter + " is Connected ");

            ClientHandler t = new ClientHandler(clientSocket,ss,sdf,counter);
            a1.add(t);
            t.start();
        }} catch (Exception e) {
            display("Error in Connection!");
        }
    }
    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        if(ss == null)
            System.out.println(time);
        else
            ss.appendEvent(time + "\n");
    }

}

class ClientHandler extends Thread{


    private Socket clientSocket;
    private BufferedReader in=null;
    SocketServer ss;
    SimpleDateFormat sdf;
    int counter;
String FileName;
    public ClientHandler(Socket clientSocket,SocketServer ss,SimpleDateFormat sdf,int counter){

        this.clientSocket=clientSocket;
        this.ss=ss;
        this.sdf=sdf;
        this.counter=counter;
    }
    private void display(String msg) {
        String time = sdf.format(new Date()) + " " + msg;
        if(ss == null)
            System.out.println(time);
        else
            ss.appendEvent(time + "\n");
    }

    public void run(){

        try{
            in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientoption;

            while((clientoption=in.readLine())!=null){
                switch(clientoption){
                    case "1":
                        receivefiles();
                        continue;
                    case "2":
                        String fileName;
                        while((fileName=in.readLine())!=null) {
                            sendfiles(fileName);
                        }
                        continue;
                    case "3":

                        System.exit(1);
                        break;
                    default :
                       display("Wrong choice choosen");
                        break;

                }
            }

        }
        catch(IOException e){

        }
    }
    public void FilesDisplay(String FileName,long size,String extension){
     File file=new File(FileName);
     String str=file.getAbsolutePath() + " " + "Client " +counter + " " +size + " " +extension;
        ss.listfiles(str);
    }
    public void receivefiles(){

        try{

            int bytesread,time=10;
            DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output=new DataOutputStream(clientSocket.getOutputStream());
            String FileName=dis.readUTF();
            String extension = "";
            int i=FileName.lastIndexOf('.');
            if(i>= 0)
                extension=FileName.substring(i+1);

            File file=new File("C:\\Users\\lenovo\\IdeaProjects\\ShareNow\\Files",FileName);
            FileOutputStream fos=new FileOutputStream(file);
            long size=dis.readLong();
            byte[] buffer=new byte[1024];
            while(size>0 && (bytesread=dis.read(buffer,0, (int) Math.min(buffer.length, size))) != -1) {
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
            FileInputStream fis=new FileInputStream(myfile);
            BufferedInputStream bis=new BufferedInputStream(fis);
            DataInputStream dis=new DataInputStream(bis);
            dis.read(mybyte,0,mybyte.length);
            OutputStream os= clientSocket.getOutputStream();
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeUTF(myfile.getName());
            dos.writeLong(mybyte.length);
            dos.write(mybyte,0,mybyte.length);
            dos.flush();




           display(fileName +" is sent to the Client "+ counter);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }




}

