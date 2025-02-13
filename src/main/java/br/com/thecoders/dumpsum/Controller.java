package br.com.thecoders.dumpsum;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Controller {

    @FXML
    public Button btnOpen;

    @FXML
    public Button btnCheckSum;

    @FXML
    public TextField tfSha1;

    @FXML
    public TextField tfSha256;

    @FXML
    public TextField tfFilePath;

    @FXML
    public ProgressIndicator piProgress;

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getWindow());

        if (file == null) return;

        tfFilePath.setText(file.toPath().toString());
    }

    @FXML
    private void checksum() {

        if (tfFilePath.getText().isEmpty()) return;

        new Thread(() -> {
            try {
                new CheckSum().perform(tfFilePath.getText());
            } catch (IOException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}