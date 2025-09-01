package com.slotmachine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Lever {
    
    private final ImageView leverStandImage;
    private final ImageView leverHeadImage;
    private final Rectangle clip;
    private final Group floatingHead;
    private final StackPane leverAlignmentBox;
    private boolean isAnimating = false;
    private Runnable onPullAction;

    public Lever() {
        // === Base visuals ===
        leverStandImage = new ImageView(new Image(getClass().getResource("/images/LeverStand.png").toExternalForm()));
        leverStandImage.setFitWidth(400); // match outerFrame width
        leverStandImage.setFitHeight(500); // match outerFrame height
        leverStandImage.setPreserveRatio(true); // stretch to fit if needed
        leverStandImage.setTranslateX(-41);
        leverStandImage.setTranslateY(-90);
        
        clip = new Rectangle(400, 500);      
        clip.setTranslateX(0);
        leverStandImage.setClip(clip);
        //LinearGradient gradient = new LinearGradient(
        //        0, 0, 0, 1, // from top to bottom
        //       true, // proportional
        //        CycleMethod.NO_CYCLE,
        //        new Stop(0, Color.web("#9A1E77")),
        //        new Stop(1, Color.web("#9E1F7B"))
        //    );
        
        //mask.setFill(gradient);
        
        // === Lever head (circle) and mask ===
        leverHeadImage = new ImageView(new Image(getClass().getResource("/images/LeverHead.png").toExternalForm()));
        leverHeadImage.setFitWidth(150); // match outerFrame width
        leverHeadImage.setFitHeight(150); // match outerFrame height
        leverHeadImage.setPreserveRatio(true); // stretch to fit if needed
        //leverHeadImage.setTranslateX(-41);
        //leverHeadImage.setTranslateY(-210);
        
        
        StackPane circleWithMask = new StackPane(leverHeadImage);
        floatingHead = new Group(circleWithMask); // avoids layout shifts
        floatingHead.setTranslateX(-41);
        floatingHead.setTranslateY(-210);

        leverAlignmentBox = new StackPane(leverStandImage, floatingHead);
        leverAlignmentBox.setAlignment(Pos.CENTER);

        // === Click interaction ===
        leverHeadImage.setOnMouseClicked(e -> 
                {AudioClip leverSound = new AudioClip(getClass().getResource("/audio/Lever.mp3").toExternalForm());
                    leverSound.play();

                    PauseTransition delay = new PauseTransition(Duration.seconds(0.3)); // Adjust based on sound length
                    delay.setOnFinished(event -> pullLever());
                    delay.play();

                    AudioClip chimeSound = new AudioClip(getClass().getResource("/audio/Chime.mp3").toExternalForm());
                    chimeSound.play();});

        leverStandImage.setOnMouseClicked(e -> 
                {AudioClip leverSound = new AudioClip(getClass().getResource("/audio/Lever.mp3").toExternalForm());
                    leverSound.play();

                    PauseTransition delay = new PauseTransition(Duration.seconds(0.3)); // Adjust based on sound length
                    delay.setOnFinished(event -> pullLever());
                    delay.play();

                    AudioClip chimeSound = new AudioClip(getClass().getResource("/audio/Chime.mp3").toExternalForm());
                    chimeSound.play();});
    }

    private void pullLever() {
        if (isAnimating) return;
        isAnimating = true;

        AudioClip rollSound = new AudioClip(getClass().getResource("/audio/Rolling.mp3").toExternalForm());
        rollSound.play();

        TranslateTransition bounce = new TranslateTransition(Duration.millis(200), leverHeadImage);
        bounce.setByY(455);
        bounce.setAutoReverse(true);
        bounce.setCycleCount(2);

        Timeline stretch = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(clip.translateYProperty(), 0)),
                new KeyFrame(Duration.millis(135), new KeyValue(clip.translateYProperty(), 380)), // sync with ball Y
                new KeyFrame(Duration.millis(600), new KeyValue(clip.translateYProperty(), 0))
           );

        bounce.play();
        stretch.play();
        stretch.setOnFinished(e -> {
                    isAnimating = false;
                    if (onPullAction != null) onPullAction.run();
            });
    }

    public void setOnPull(Runnable action) {
        this.onPullAction = action;
    }
    
    public void triggerPull() {
        AudioClip chimeSound = new AudioClip(getClass().getResource("/audio/Chime.mp3").toExternalForm());
                    chimeSound.play();
        pullLever();
    }

    public StackPane getNode() {
        return leverAlignmentBox;
    }
}
