package client_sharenow;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class File_Controller {
    @FXML
    Label user_email, file_uploaded;
    @FXML
    Button logout, name;
    @FXML
    ListView files;
    @FXML
    TextArea file_description;
    @FXML
    TextField filename;
    List<Files> serverlist;
    static List<Files> selected_files = new ArrayList<Files>();

    User user = Main.user;

    public void initialize() {
        file_description.setEditable(false);
        user_email.setText(Main.user.getEmail());
        files.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selected_files.clear();


    }

    @FXML
    public void handleclickedfiles() {
        Files file = (Files)files.getSelectionModel().getSelectedItem();
//        set the Text fields and image of the selected file..
        String temp = fileinfo(file);
        file_description.setText(temp);
        file_added.setText("");


    }

    private void setUser_email(String fullnme){

        user_email.setText(fullnme);
    }

    private String fileinfo(Files files){
        String temp="";
        temp=temp+"Name: "+Files.getName()+"\n";
        temp=temp+"Type: "+Files.getType()+"\n";
        temp=temp+"Date uploaded: "+Files.getUpload()+"\n";
        temp=temp+"Expiry Date: "+Files.getExpiry()+"\n";
        temp=temp+"Uploader Name: "+Files.getUser().getUserName()+"\n";

        return temp;


    }

    @FXML
    public void onlogoutclicked(){
        Stage primaryStage = (Stage) logout.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
}
