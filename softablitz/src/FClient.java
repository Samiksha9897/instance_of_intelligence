import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class FClient {
    public static int PORT=8932;
    public static int BUFFER_SIZE=16*1024;
    public static void main(String[] args) throws Exception {
        String fileName = null;
        String ip=null;
        try {
            ip= args[0];
            fileName = args[1];

        } catch (Exception e) {
            System.out.println("Pass file name as command line argument");
        }

        File file = new File(fileName);
        Socket socket = new Socket(ip, PORT);

        InputStream in=socket.getInputStream();

        OutputStream out=new FileOutputStream("received.zip");

        byte [] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) > 0) {
            out.write(buffer,0,bytesRead);
        }

        out.close();
        in.close();
        socket.close();
        System.out.println("done clinet");
    }
}
