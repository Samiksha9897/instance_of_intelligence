package client_sharenow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("first.fxml"));
        primaryStage.setTitle("File Transfer");
        primaryStage.setScene(new Scene(root, 1081, 826));
//        primaryStage.setMaximized(true);
        primaryStage.show();

    }
    static String serverip = "localhost";
    static int portno = 5000;
    static User user;
    static Socket socket;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;


}