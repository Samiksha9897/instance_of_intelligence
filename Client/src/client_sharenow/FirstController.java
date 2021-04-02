package client_sharenow;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class FirstController {
    public static Socket sock;
    public static InputStream input;
   public static OutputStream output;
    public static PrintStream os;
    public static BufferedReader stdin;
    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private Label check_label;


    @FXML
    public void onsignupclicked(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) signup.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }
    //    check is a user that is set volatile so that a new check reference is not created in new thread..
    volatile User check = null;

    public void onloginclicked(){
        check=null;
        LoginRequest loginRequest;
        if(!usernameTextField.getText().isEmpty()&&!setPasswordField.getText().isEmpty())
            loginRequest = new LoginRequest(usernameTextField.getText(),HashGenerator.hash(setPasswordField.getText()));

        else{
            check_label.setText("Enter Both Fields");
            return;
        }
        new Thread(() -> {
            try{
                Socket socket = new Socket(Main.serverip,Main.portno);
                Main.socket=socket;
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Main.oos = objectOutputStream;
                objectOutputStream.writeObject(loginRequest);
                objectOutputStream.flush();
                Main.ois = objectInputStream;
                check = (User)objectInputStream.readObject();
                Main.user=check;
                //Main.socket=socket;
                System.out.println("login request completed");
            }catch (Exception e){
                check=new User();
                check.setVerificationStatus(String.valueOf(LoginStatus.FAILED_FROM_CLIENT));
                System.out.println(e);
            }finally {
                Platform.runLater(() -> {
                    if(check.getVerificationStatus().equals(String.valueOf(LoginStatus.FAILED_FROM_CLIENT)))
                        check_label.setText("Server Unreachable");
                    else if(check.getVerificationStatus().equals(String.valueOf(LoginStatus.INCORRECT_PASSWORD)))
                        check_label.setText("Invalid Credentials");
                    else if (check.getVerificationStatus().toUpperCase().equals(String.valueOf(LoginStatus.OTHER)))
                        check_label.setText("Server-Side Error");
                });
            }

        }).start();


//        here the operations are done in a thread so that ui does not become unresponsive.


        while (check==null){
            System.out.println("check is still null");
        }
//while loop so that inside the thread value of check is changed


        if(check!=null){

            if(check.getVerificationStatus().equals(String.valueOf(LoginStatus.FAILED_FROM_CLIENT)))
                check_label.setText("Server Unreachable");
            else if (check.getVerificationStatus().equals(String.valueOf(LoginStatus.INCORRECT_PASSWORD)))
                check_label.setText("Invalid Credentials");
            else if(check.getVerificationStatus().toUpperCase().equals(String.valueOf(LoginStatus.OTHER)))
                check_label.setText("Server-Side Error");
            else if(check.getVerificationStatus().toUpperCase().equals(String.valueOf(LoginStatus.VERIFIED))){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Cgui.fxml"));
                System.out.println("login completed now setting new scene");
                try {
                    sock= Main.socket;
                    System.out.println("Printstream created");
                    os=new PrintStream(sock.getOutputStream());
                    os.println("printing stream");
                    //stdin=new BufferedReader(new InputStreamReader(System.in));
                    input = new DataInputStream(sock.getInputStream());
                    output = new DataOutputStream(sock.getOutputStream());
                    System.out.println("All streams created");
                    fxmlLoader.load();


                } catch (Exception eIO) {
                    eIO.printStackTrace();
                }

                //Controller_products controller = fxmlLoader.getController();
                System.out.println("getting into file transfer");


                Parent p = fxmlLoader.getRoot();

                Stage primaryStage = (Stage) signup.getScene().getWindow();
                primaryStage.setScene(new Scene(p,1080,900));

            }
        }



    }
    public void onadminclicked(){
        Stage primaryStage = (Stage) signup.getScene().getWindow();
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        }catch(IOException e){
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(root, 1081, 826));
    }



}
