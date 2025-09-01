package com.slotmachine;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class SlotMachineUI {

    private final SlotReel[] reels = new SlotReel[3];
    private final Lever lever;
    private final HBox mainLayout;

    //private final AudioClip ding;
    
    public SlotMachineUI(Image[] images) {
        // === SLOT BOX SETUP ===
        HBox slotBox = new HBox(10);
        slotBox.setAlignment(Pos.CENTER);
        slotBox.setPadding(new Insets(0));

        for (int i = 0; i < 3; i++) {
            SlotReel reel = new SlotReel(images);
            reels[i] = reel;
            slotBox.getChildren().add(reel.getNode());

        }
        
        // === FRAMES ===
        ImageView slotMachineFrameImage = new ImageView(
        new Image(getClass().getResource("/images/SlotMachineFrame.png").toExternalForm()));
        slotMachineFrameImage.setFitWidth(1170); // match outerFrame width
        slotMachineFrameImage.setFitHeight(860); // match outerFrame height
        slotMachineFrameImage.setPreserveRatio(true); // stretch to fit if needed
        
        
        // Wrap slotBox in a StackPane to fix its size
        StackPane slotBoxWrapper = new StackPane(slotBox);
        slotBoxWrapper.setAlignment(Pos.CENTER);
        slotBoxWrapper.setTranslateY(100);
        
        //Breadfast Logo
        ImageView breadfastLogo = new ImageView(new Image(getClass().getResource("/images/Breadfast.png").toExternalForm()));
        breadfastLogo.setFitWidth(370); // match outerFrame width
        breadfastLogo.setFitHeight(560); // match outerFrame height
        breadfastLogo.setPreserveRatio(true); // stretch to fit if needed
        breadfastLogo.setTranslateY(360);
        
        StackPane slotDisplayStack = new StackPane();
        slotDisplayStack.setAlignment(Pos.CENTER);
        slotDisplayStack.getChildren().addAll(      
                slotMachineFrameImage,
                slotBoxWrapper,
                breadfastLogo
        );
    
        // === LEVER ===
        lever = new Lever();
        lever.setOnPull(this::spin);
        
        VBox leverBox = new VBox(lever.getNode());
        leverBox.setAlignment(Pos.CENTER);
        leverBox.setTranslateX(-406);  // Adjust based on how far you want it to shift right
        leverBox.setTranslateY(-80);  // Adjust based on vertical alignment with frame
        
        // === OVERLAY LEVER ON MACHINE ===
        StackPane machineWithLever = new StackPane(slotDisplayStack, leverBox);
        machineWithLever.setAlignment(Pos.CENTER); // Fine-tune alignment
        machineWithLever.setMaxSize(1170, 860); // Match frame size
        
        // === MAIN LAYOUT ===
        mainLayout = new HBox(machineWithLever);
        mainLayout.setAlignment(Pos.CENTER);
    }

    public void spin() {
        int i =0;
        for (SlotReel reel : reels) {
            reel.spin(4 - i++);
        }
    }
    
    public void triggerLever() {
    lever.triggerPull();  // Make sure Lever.java has this method
    }

    public Node getNode() {
        return mainLayout;
    }
}
