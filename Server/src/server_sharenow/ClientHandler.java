package server_sharenow;

import java.io.*;
import java.net.Socket;



class ClientHandler implements Runnable{
    private Socket clientSocket;
   private BufferedReader in=null;
//private int counter;

    public ClientHandler(Socket clientSocket){

        this.clientSocket=clientSocket;
        //this.counter=counter;
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
                            System.out.println("Wrong choice choosen");
                            break;

                    }
                }

        }
        catch(IOException e){

        }
    }
    public void receivefiles(){

        try{

            int bytesread,time=10;
            DataInputStream dis=new DataInputStream(clientSocket.getInputStream());

            String FileName=dis.readUTF();
            DataOutputStream output=new DataOutputStream(clientSocket.getOutputStream());
            long size=dis.readLong();
            byte[] buffer=new byte[1024];
            while(size>0 && (bytesread=dis.read(buffer,0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesread);
                size -= bytesread;

            }



            //System.out.println(FileName + " is received from Client" +counter + " " + clientSocket);



        }

        catch(IOException e){
          e.printStackTrace();


        }

    }
    public void sendfiles(String fileName){
        try{
            int second = 0;

            File myfile=new File(fileName);
            byte mybyte[]=new byte[(int)fileName.length()];
            FileInputStream fis=new FileInputStream(fileName);
            BufferedInputStream bis=new BufferedInputStream(fis);
            DataInputStream dis=new DataInputStream(bis);
            dis.read(mybyte,0,mybyte.length);
            OutputStream os= clientSocket.getOutputStream();
            DataOutputStream dos=new DataOutputStream(os);
            dos.writeUTF(myfile.getName());
            dos.writeLong(mybyte.length);
           dos.write(mybyte,0,mybyte.length);
            dos.flush();


            os.close();
dos.close();
fis.close();
bis.close();
dis.close();
            clientSocket.close();
            //System.out.println("File named " + fileName + " is sent to the Client " + counter);
        }
        catch(Exception e){
           e.printStackTrace();
        }

    }

    public ClientHandler(){

    }




}

