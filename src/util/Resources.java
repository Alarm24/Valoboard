package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Resources {
    public static ImageView loadAgentIcon(String path) {
        ImageView img = loadImageFromPath(path);

        return img;
    }

    public static ImageView loadImageFromPath(String path) {
        return new ImageView(new Image(ClassLoader.getSystemResource(path).toString()));
    }

}
