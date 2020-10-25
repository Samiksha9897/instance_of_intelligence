package client_sharenow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Dialogs {
    static public void showError(String error,String title){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Dialogs.class.getResource("../../client_sharenow/dialog_window.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();;
        }
        stage.initStyle(StageStyle.UNDECORATED);
        assert root !=null;
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Oops! An error occurred.");
        stage.initStyle(StageStyle.TRANSPARENT);
        DialogWindowController controller = loader.getController();
        controller.Initialization(stage,title,error,false);
        stage.showAndWait();

    }

    static public boolean showQuestion(String question,String title){
        Stage stage = new Stage();
        FXMLLoader loader =new FXMLLoader(Dialogs.class.getResource("../../client_sharenow/dialog_window.fxml"));
        Parent root=null;
        try{
            root = loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        stage.initStyle(StageStyle.UNDECORATED);
        assert root != null;
        Scene scene = new Scene(root,Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("We are interested in your opinion!");
        stage.initStyle(StageStyle.TRANSPARENT);
        DialogWindowController controller = loader.getController();
        controller.Initialization(stage,title,question,true);
        stage.showAndWait();
        return controller.getAnswer();
    }

    static public void showAlert(String text,String title){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Dialogs.class.getResource("../../client_sharenow/dialog_window.fxml"));
        Parent root=null;
        try{
            root = loader.load();
        }catch(IOException e){
            e.printStackTrace();
        }
        stage.initStyle(StageStyle.UNDECORATED);
        assert root != null;
        Scene scene = new Scene(root,Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Attention!Attention!");
        stage.initStyle(StageStyle.TRANSPARENT);
        DialogWindowController controller = loader.getController();
        controller.Initialization(stage,title,text,false);
        stage.showAndWait();

    }
    }

