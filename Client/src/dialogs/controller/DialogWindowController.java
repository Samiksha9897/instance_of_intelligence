package dialogs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class DialogWindowController {
    private Stage STAGE;

    private double xOffset;
    private double yOffset;

    private boolean answer = false;

    @FXML
    private AnchorPane anchorPaneMainHeader;

    @FXML
    private Label titleLabel;

    @FXML
    private Text textText;

    @FXML
    private Button okButton;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    public boolean getAnswer(){
        return answer;
    }

    private void initComponents(){
        anchorPaneMainHeader.setOnMousePressed(event->{
            xOffset = STAGE.getX() - event.getScreenX();
            yOffset = STAGE.getY() - event.getScreenY();
        });

        anchorPaneMainHeader.setOnMouseDragged(event->{
            STAGE.setX(event.getScreenX()+xOffset);
            STAGE.setY(event.getScreenY()+yOffset);
        });

        okButton.setOnMouseClicked(event->STAGE.close());
        yesButton.setOnMouseClicked(event->{
            answer = true;
            STAGE.close();
        });
        noButton.setOnMouseClicked(event->STAGE.close());
    }

    public void Initialization

}