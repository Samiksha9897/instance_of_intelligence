package client_sharenow;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class AdminController {

    @FXML
    Button showfiles;

    @FXML
    Button removefile;

    @FXML
    Button logout;

    public void onfilesclicked(ActionEvent actionEvent){

        Stage primaryStage = (Stage) showfiles.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("File.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onremoveclicked(ActionEvent actionEvent){
        Stage primaryStage = (Stage)  removefile.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource(".fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

    public void onlogoutclicked(ActionEvent actionEvent){

        Stage primaryStage = (Stage)  logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }

}


