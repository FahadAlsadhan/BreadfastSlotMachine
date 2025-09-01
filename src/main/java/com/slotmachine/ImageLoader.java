package com.slotmachine;

import javafx.scene.image.Image;

import java.io.InputStream;

public class ImageLoader {
    public static Image[] loadImages(String[] paths) {
        Image[] images = new Image[paths.length];

        for (int i = 0; i < paths.length; i++) {
            InputStream stream = ImageLoader.class.getResourceAsStream(paths[i]);
            if (stream != null) {
                images[i] = new Image(stream);
            } else {
                System.err.println("Missing image: " + paths[i]);
                images[i] = new Image("https://via.placeholder.com/80"); // fallback
            }
        }

        return images;
    }
}
