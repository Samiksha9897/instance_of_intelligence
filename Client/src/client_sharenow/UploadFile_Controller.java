package client_sharenow;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.net.*;
import java.io.*;
import java.util.*;


public class UploadFile_Controller {

    User user = Main.user;

    @FXML
    Label user_email,status;

    @FXML
    TextField name;

    @FXML
    TextField filesize,filetype;

    @FXML
    DatePicker upload_date,expdate;

    volatile int file_status;

    @FXML
    public void onuploadclicked(ActionEvent actionEvent){
        File file = new File();
        if(name.getText().isEmpty()||filetype.getText().isEmpty()||filesize.getText().isEmpty())
        {
            status.setText("Enter all fields");
            return;
        }
        file.setName(name.getText());
        file.setFileType(filetype.getText());
        file.setFileSize(Integer.parseInt(filesize.getText()));
        Date upload;
        Date exp;  //,enddate
        if(upload_date.getValue()!=null && expdate.getValue()!=null){
            upload = Date.from(upload_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            exp = Date.from(expdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }else{
            status.setText("Enter dates");
            return;
        }

        file.setExpiry(exp);
        file.setUpload(upload);

        FileAddRequest pra = new FileAddRequest(file);
        //pra.setDate(enddate);

        new Thread(new Runnable() {

                        @Override
                        public void run() {
                            BufferedReader stdin;
                            try {
                                Socket sock = Main.socket;
                                //byte[] mybytearray = new byte[(int) .length()];
                                /*if(!myFile.exists()) {
                                    System.out.println("File does not exist..");
                                    return;
                                }*/

                                FileInputStream fis = new FileInputStream(file);
                                OutputStream os = sock.getOutputStream();


                                DataOutputStream dos = new DataOutputStream(os);
                                dos.writeUTF(file.getName());
                                dos.writeLong(file.length());
                                System.out.println(file.getAbsolutePath());
                                int read = 0;
                                while ((read = fis.read()) != -1)
                                    dos.writeByte(read);
                                dos.flush();
                                file_status = (int) fis.read();
                                //System.out.println("File "+fileName+" sent to Server.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            }

        }).start();

    }


}



