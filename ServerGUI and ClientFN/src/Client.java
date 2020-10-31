import java.net.*;
import java.io.*;


public class Client {

    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;
    private static InputStream input;
    private static OutputStream output;


    public static void main(String[] args) throws IOException {


        boolean runin = true;
        while (true) {
            try {
                sock = new Socket("localhost", 5000);
                stdin = new BufferedReader(new InputStreamReader(System.in));

                output = sock.getOutputStream();
                input = sock.getInputStream();
            } catch (Exception e) {
                System.err.println("Cannot connect to the server, try again later.");
                return;
            }

            os = new PrintStream(sock.getOutputStream());

            try {
                while (runin) {
                    switch (Integer.parseInt(selectAction())) {
                        case 1:
                            os.println("1");
                            sendFile();
                            continue;
                        case 2:
                            os.println("2");
                            System.out.print("Enter file name: ");
                            fileName = stdin.readLine();
                            os.println(fileName);
                            receiveFile(fileName);
                            System.out.println("abc");
                           break;
                        case 3:
                            runin = false;
                            break;
                        default:
                            System.out.println("Wrong Choice Choosen");
                    }

                }
            } catch (Exception e) {
                System.err.println("not valid input");
            }

        }

    }

    public static String selectAction() throws IOException {
        System.out.println("1. Send file.");
        System.out.println("2. Recieve file.");
        System.out.println("3. Exit.");
        System.out.print("\nMake selection: ");

        return stdin.readLine();
    }


    public static void sendFile() {
        try {
            System.out.print("Enter file name: ");
            fileName = stdin.readLine();
            /*System.out.println("Enter the number of times it should be downloaded");
            String nooftimes = stdin.readLine();

             */
            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];
            if (!myFile.exists()) {
                System.out.println("File does not exist..");
                return;
            }


            FileInputStream fis = new FileInputStream(myFile);
            DataOutputStream dos = new DataOutputStream(output);
            dos.writeUTF(myFile.getName());
            dos.flush();
            dos.writeLong(myFile.length());
            dos.flush();
            System.out.println(myFile.getAbsolutePath());
            int read = 0;
            while ((read = fis.read()) != -1) {
                dos.writeByte(read);
            }

dos.flush();
            System.out.println("File " + fileName + " sent to Server.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiveFile(String fileName) {
        try {


            int bytesread, time = 10;

            DataInputStream dis = new DataInputStream(input);

            File file = new File("C:\\Users\\lenovo\\IdeaProjects\\ShareNow\\Client received", fileName);
            OutputStream output = new FileOutputStream(file);
            String FileName = dis.readUTF();
            long size = dis.readLong();
            byte[] buffer = new byte[1024];
            System.out.println("abc");
            while(size>0 && (bytesread=dis.read(buffer,0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesread);
                size -= bytesread;

            }
            output.flush();


            System.out.println(FileName + " is received from Client" + sock);


        } catch (IOException e) {
            e.printStackTrace();


        }

    }
}
