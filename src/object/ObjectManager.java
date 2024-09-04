package object;

import main.GamePanel;
import utils.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectManager {

    GamePanel gamePanel;
    public ObjectMetadata[] objectMetadata;
    public ArrayList<Object> objects = new ArrayList<>();

    // object images should be listed in order they would appear, i.e. wood_chest should be before wood_chest_open
    private final String[][] objectNames = {{"wood_chest", "true"}, {"iron_chest", "true"}};

    public ObjectManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        objectMetadata = new ObjectMetadata[10];

        loadObjectImages();
        loadObjectMap();
    }

    public void loadObjectImages() {
        try {
            for (int i = 0; i < objectNames.length; i++) {
                objectMetadata[i] = new ObjectMetadata();
                objectMetadata[i].object = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("object/" + objectNames[i][0] + ".png")));
                objectMetadata[i].collision = (Objects.equals(objectNames[i][1], "true"));
                objectMetadata[i].type = objectNames[i][0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadObjectMap() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("maps/" + Config.map + ".txt");
        if (stream == null) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        boolean readingObjects = false;

        while(true) {

            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                line = null;
                e.printStackTrace();
            }

            if (line == null) break;

            if (!readingObjects) {
                if (line.equals(Config.MapFormatting.objectDelimiter)) {
                    readingObjects = true;
                }
            } else {
                String[] splitLine = line.split(" ");

                BufferedImage[] objectImages = new BufferedImage[10];

                int imagesAdded = 0;

                // each object can have multiple images, e.g. a chest has an image for being closed and an image for being open
                for (ObjectMetadata omd : objectMetadata) {
                    if (omd.type.startsWith(splitLine[0])) {
                        objectImages[imagesAdded] = omd.object;
                        imagesAdded++;
                    }
                }

                objects.add(new Object(splitLine[0], Integer.parseInt(String.valueOf(splitLine[1])), Integer.parseInt(String.valueOf(splitLine[2])), objectImages));
            }
        }

        for(Object i: objects) {
            System.out.println(i.type);
        }
    }

    public void draw(Graphics2D g2d) {
        for(Object i: objects) {
            // check if the object is visible, no point rendering it otherwise
            // render object
            System.out.println(i.type);
        }
    }

}
