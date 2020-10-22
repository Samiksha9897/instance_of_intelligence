package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

public class function {
    @FXML
    private Button b1;

    @FXML
    private ListView lv;

    public void choose(ActionEvent e)
    {
        FileChooser r= new FileChooser();
        File s=r.showOpenDialog(null);
        if(s!=null)
        {
            lv.getItems().add(s.getAbsoluteFile());
        }
        else
        {
            System.out.print("no file");
        }

    }


}
