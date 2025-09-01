package com.slotmachine;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;

import java.util.Random;

public class SlotReel {
    private static final int IMAGE_HEIGHT = 300;
    private static final int IMAGE_WIDTH = 233;

    private final VBox reelStrip = new VBox();
    private final StackPane container;
    private final Image[] images;
    private final Random random = new Random();

    public SlotReel(Image[] images) {
    this.images = images;
    reelStrip.setAlignment(Pos.TOP_CENTER);

    for (int i = 0; i < 10; i++) {
        for (Image img : images) {
            ImageView iv = new ImageView(img);
            iv.setFitWidth(IMAGE_WIDTH);
            iv.setFitHeight(IMAGE_HEIGHT);
            iv.setPreserveRatio(true);
            reelStrip.getChildren().add(iv);
        }
    }

    container = new StackPane(reelStrip);
    container.setPrefSize(IMAGE_WIDTH, IMAGE_HEIGHT);
    container.setMinSize(IMAGE_WIDTH, IMAGE_HEIGHT);
    container.setMaxSize(IMAGE_WIDTH, IMAGE_HEIGHT);

    Rectangle clip = new Rectangle(IMAGE_WIDTH + 20, IMAGE_HEIGHT + 38);
    container.setClip(clip);
    clip.setTranslateY(-175);
}

    public void spin(int fullCycles) {
        int symbolIndex = random.nextInt(images.length);
        int targetIndex = fullCycles * images.length + symbolIndex;
        double toY = -targetIndex * IMAGE_HEIGHT;

        TranslateTransition scroll = new TranslateTransition(Duration.seconds(3.5), reelStrip);
        scroll.setToY(toY);
        scroll.setInterpolator(Interpolator.SPLINE(0.25, 0.1, 0.25, 1));
        scroll.setOnFinished(e -> reelStrip.setTranslateY(-symbolIndex * IMAGE_HEIGHT));
        scroll.play();
    }

    public StackPane getNode() {
        return container;
    }
}
