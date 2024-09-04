package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ObjectMetadata {
    String[] imageNames;
    HashMap<String, BufferedImage> objectImages = new HashMap<>();
    boolean collision;
    String type;

    public ObjectMetadata(boolean collision, String type, String ...images) {
        this.collision = collision;
        this.imageNames = images;
        loadImages();
    }

    public void loadImages() {
        try {
            for (String name : imageNames) {
                objectImages.put(name, ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("object/" + name + ".png"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
