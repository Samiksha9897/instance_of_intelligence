package server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import server.SERVER;

import java.io.File;
import java.net.ServerSocket;

public class SocketServer extends Application {

    public Button START;
 public TextArea txt1;
    public ListView list1;

    private SERVER server=null;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader connectLoader=new FXMLLoader();
        connectLoader.setLocation(getClass().getResource("/sample.fxml"));
           Parent root = connectLoader.load();
            Scene scene = new Scene(root, 964, 701);

        primaryStage.setTitle("Server GUI");

        final Button START=new Button("START");
START.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        StartButton();
    }
});
final TextArea text=new TextArea();
text.setEditable(false);
text.setScrollTop(100);

primaryStage.setScene(scene);
primaryStage.show();
    }


public void StartButton(){

        try{
        server=new SERVER(5000,this);
       appendEvent("SERVER STARTED");
        appendEvent("\nWaiting for Clients\n");
new ServerRunning().start();
}
        catch(Exception e){
          appendEvent("\nServer Already in use");
            return;
        }

    }

   void listfiles(String str){
        list1.getItems().add(str);
   }

    void appendEvent(String str){
        txt1.appendText(str);

    }
    void Filesdisplay() {
        int i = 0;

        File directory = new File("C:\\Users\\lenovo\\IdeaProjects\\ShareNow\\Files");
        String files[] = directory.list();
        for (i = 0; i < files.length; i++) {
            String str = files[i];
            listfiles(str);
        }
    }
  class ServerRunning extends Thread{
        public void run(){
server.start();

        }
  }

}