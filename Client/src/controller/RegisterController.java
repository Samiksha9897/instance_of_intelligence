package controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.xml.bind.DatatypeConverter;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {

    public Stage STAGE;
    private String host;
    private LoginController LIWC;
    private double xOffset;
    private double yOffset;


    @FXML
    private Label fullnameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private Label setPasswordLabel;

    @FXML
    private TextField FullnameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField contactTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private AnchorPane anchorPaneMainHeader;

    @FXML
    private Button registerButton;


    private void initComponents() {
        anchorPaneMainHeader.setOnMousePressed(event -> {
            xOffset = STAGE.getX() - event.getScreenX();
            yOffset = STAGE.getY() - event.getScreenY();
        });

        anchorPaneMainHeader.setOnMouseDragged(event -> {
            STAGE.setX(event.getScreenX() + xOffset);
            STAGE.setY(event.getScreenY() + yOffset);
        });

        FullnameTextField.setOnKeyReleased(event -> onKeyReleased(event.getCode()));

        usernameTextField.setOnKeyReleased(event -> onKeyReleased(event.getCode()));

        EmailTextField.setOnKeyReleased(event -> onKeyReleased(event.getCode()));

        contactTextField.setOnKeyReleased(event -> onKeyReleased(event.getCode()));

        setPasswordField.setOnKeyReleased(event -> onKeyReleased(event.getCode()));

        registerButton.setOnMouseClicked(event -> saveChanges());


    }

    public void Initialization(Stage _stage, String host) {
        this.host = host;
        LIWC = LoginController;
        STAGE = _stage;
        initComponents();


    }

    private void saveChanges() {
        if (!isCorrect()) return;
        String passwordHash = null;
        if (Objects.equals(setPasswordField.getText(), ""))
            passwordHash = getHash(setPasswordField.getText());
        STAGE.close();

    }

    private String getHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return DatatypeConverter.printHexBinary(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void onKeyReleased(KeyCode keyCode) {
        if (keyCode == KeyCode.ENTER) {
            if (isChange()) {
                if (isChange()) {
                    saveChanges();
                }
            }

        } else {
            if (isChange())
                isChange();
        }
    }

    private boolean isChange() {
        if (!Objects.equals(FullnameTextField.getText(), CONTACTDATA.getFullname)
                || !Objects.equals(usernameTextField.getText(), CONTACTDATA.getusername)
                || !Objects.equals(EmailTextField.getText(), CONTACTDATA.getEmail)
                || !Objects.equals(contactTextField.getText(), CONTACTDATA.getcontact)
                || !Objects.equals(setPasswordField.getText(), "")) {
            registerButton.setDisable(false);
            return true;
        }
        registerButton.setDisable(true);
        return false;

    }

    private boolean isCorrect() {
        if (!regex(FullnameTextField.getText(), "^[A-Yaa-zA-Za-z-]+$")) {
            Dialogs.showAlert("Enter combination of letters or alphabets.Full Name was not entered correctly");
            return false;
        }
        if (!regex(usernameTextField.getText(), "^[A-Za-z0-9@#&_]{3,}$")) {
            Dialogs.showAlert("username should be combination of letters,numbers and special characters(@ # & _).Minimum4 characters");
            return false;
        }
        if (!regex(EmailTextField.getText(), "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
            Dialogs.showAlert("Incorrect Email!");
            return false;
        }
        if (!regex(setPasswordField.getText(), "^[A-Za-z0-9_]{6,}$")) {
            Dialogs.showAlert("password should be combination of letters,numbers and an underscore(at least 6 characters.", "Password not entered correctly");
            return false;
        }
        return true;

    }

    private boolean regex(String text,String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        return m.matches();
    }
}

