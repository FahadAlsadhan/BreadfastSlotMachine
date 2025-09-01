package com.slotmachine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SlotsMachineApp extends Application {
    @Override
    public void start(Stage stage) {
        String[] imagePaths = {
            "/images/Strawberry.png",
            "/images/Chocolate.png",
            "/images/Banana.png",
            "/images/Honey.png",
            "/images/Cheese.png",
            "/images/Cucumber.png",
            "/images/Chicken.png",
            "/images/Tomato.png",
            "/images/Biscoff.png",
            "/images/Lettuce.png",
            "/images/Mango.png",
            "/images/Nutella.png",
            "/images/Oreo.png",
            "/images/Zaatar.png"
        };

        Image[] images = ImageLoader.loadImages(imagePaths);
        SlotMachineUI machineUI = new SlotMachineUI(images);
        
        GridPane root = new GridPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 100%, #ab0382, #1A0025);");
        
        root.add(machineUI.getNode(), 0, 0);

        Scene scene = new Scene(root, 700, 400);
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
                machineUI.triggerLever();
            }
        });
        
        stage.setTitle("Flavor Generator");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
