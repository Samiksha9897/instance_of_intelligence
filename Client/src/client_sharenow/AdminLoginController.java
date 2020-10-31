package client_sharenow;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AdminLoginController {

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label Titlestatus;
    AdminLoginRequest alr;
    volatile String check;

    public void onloginclicked(ActionEvent actionEvent) {
        alr=new AdminLoginRequest(usernameTextField.getText(),setPasswordField.getText());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket socket = new Socket(Main.serverip,Main.portno);
                    Main.socket =socket;
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    Main.oos = oos;
                    oos.writeObject(alr);
//                    create an admin object containing login details of admin..
                    oos.flush();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Main.ois = ois;
//                    receive success status.
                    check = (String)ois.readObject();
                    if(check.equals(String.valueOf(AdminLoginStatus.SUCCESS))){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage primaryStage = (Stage) backButton.getScene().getWindow();
                                Parent root = null;
                                try {

                                    root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                                primaryStage.setScene(new Scene(root, 1081, 826));
                            }
                        });
                    }else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Titlestatus.setText("Error");
                            }
                        });

                    }


                }catch (Exception e){
                    System.out.println(e);
                }


            }
        }).start();
    }

    public void onbackclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) backButton.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("first.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}


