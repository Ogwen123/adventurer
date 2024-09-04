package object;

import entity.Entity;
import main.GamePanel;
import tile.TileManager;
import utils.Config;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectManager {

    GamePanel gamePanel;
    public ArrayList<Object> objects = new ArrayList<>();

    HashMap<String, ObjectMetadata> objectData = new HashMap<>(); // this stores data about each type of object

    public ObjectManager(GamePanel gamePanel) {
        initObjectData();

        this.gamePanel = gamePanel;


        loadObjectMap();
    }

    public void initObjectData() {
        objectData.put("wood_chest", new ObjectMetadata(true, "wood_chest", "wood_chest_closed"));
        objectData.put("iron_chest", new ObjectMetadata(true, "iron_chest", "iron_chest_closed"));
        objectData.put("null", new ObjectMetadata(true, "null", "null"));
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

                objects.add(new Object(Integer.parseInt(String.valueOf(splitLine[1])), Integer.parseInt(String.valueOf(splitLine[2])), objectData.get(splitLine[0])));
            }
        }
    }

    public void draw(Graphics2D g2d) {
        for(Object i: objects) {
            // check if the object is visible, no point rendering it otherwise
            // render object
            i.draw(g2d, gamePanel.cameraX, gamePanel.cameraY);
            g2d.drawImage(objectData.get("wood_chest").objectImages.get("wood_chest_closed"), TileManager.tileCoordToScreenLoc(0, Entity.Plane.X, gamePanel.cameraX), TileManager.tileCoordToScreenLoc(0, Entity.Plane.Y, gamePanel.cameraY), Config.tileSize, Config.tileSize, null);
        }
    }

}
