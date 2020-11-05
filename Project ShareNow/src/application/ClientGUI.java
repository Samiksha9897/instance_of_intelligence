package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class ClientGUI extends Application {
    private int defaultPort = 5000;
    private String defaultHost = "localhost";
    // to hold the server address an the port number
    @FXML
    private TextField tfServer, tfPort;
    private boolean connected;
    @FXML
    private TextArea txt1;
    public Button Cstart;
    public Button choose;
    public Button send;
    public Button receive;
    public ListView lv;
    public TextField t1=null;
    // the Client object
    // private Client client;
    public application.Client client;
    public File s;
    public ActionEvent e;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader connectLoader=new FXMLLoader();
        connectLoader.setLocation(getClass().getResource("/Cgui.fxml"));
        Parent root = connectLoader.load();
        Scene scene = new Scene(root, 974, 489);

        primaryStage.setTitle("Client GUI");

        final Button Cstart=new Button("START");
        Cstart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartButton();
            }
        });
        final TextArea text=new TextArea();
        text.setEditable(false);
        text.setScrollTop(100);


        final Button choose=new Button("Choose");
        choose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                choose(event);
            }
        });
        final Button send=new Button("UPLOAD");
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                send();
            }
        });
        final Button receive=new Button("DOWNLOAD");
        receive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File s=new File(t1.getText());
                receive();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();


    }



    @FXML
    public void StartButton(){
        try{
            client=new application.Client(5000,this);
            appendEvent("CONNECTED TO SERVER");
            new ClientRunning().start();
        }
        catch(Exception e){
            appendEvent("\nSERVER ALREADY IN USE!");
            return;
        }

    }

    void appendEvent(String str) {
        txt1.appendText(str);

        Font font = Font.font("Georgia", FontWeight.NORMAL, 14);
    }
    @FXML
    void choose(ActionEvent event) {
        this.e=e;
        FileChooser r= new FileChooser();
        s=r.showOpenDialog(null);
        if(s!=null) {
            lv.getItems().add(s.getAbsoluteFile());
            lv.getItems().add(s.getName());
            lv.getItems().add(s.length());
            lv.getItems().add(s.hashCode());
        }

    }

    @FXML
    public void receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File s=new File(t1.getText());
                try {
                    application.Client.receiveFile(s.getName());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }


        ).start();

    }



    @FXML

    public void send() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String fileName = s.getName();
                String filePath = s.getAbsolutePath();
                String directory=s.getParent();
                application.Client.sendFile(directory,fileName);
            }
        }).start();

    }



    // lv.getItems().add(Files.hash(s,Hashing.md5()).toString);
                /*try {

                    //String sharer = s.getText();
                    //String checksum = Files.hash(s, Hashing.md5()).toString();
                    long size = s.length();
*/


    class ClientRunning extends Thread{
        public void run(){

            client.start();
        }
    }
}





