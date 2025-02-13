package br.com.thecoders.dumpsum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class App extends Application {

    private static Window window;

    public static Window getWindow() {
        return window;
    }

    private static Controller controller;

    public static Controller getController() {
        return controller;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dumpsum.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        controller = fxmlLoader.getController();
        stage.setResizable(false);

        controller.tfFilePath.setDisable(true);

        stage.setTitle("DumpSum");
        stage.setScene(scene);
        window = stage;
        stage.show();
        stage.setOnCloseRequest(windowEvent -> System.exit(0));

    }

    public static void main(String[] args) {
        launch();
    }
}
