package tile;

import entity.Entity.Plane;
import main.GamePanel;
import utils.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    // map meta data
    int centreTileX, centreTileY;

    private final String[][] tileNames = {{"dirt", "false"}, {"grass", "false"}, {"wall", "true"}, {"water_1", "false"}, {"water_2", "false"}}; // {name, collisions}

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tiles = new Tile[10];

        loadTileImages();
        loadMap();
    }

    public void loadTileImages() {
        try {
            for (int i = 0; i < tileNames.length; i++) {
                tiles[i] = new Tile();
                tiles[i].tile = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tile/" + tileNames[i][0] + ".png")));
                tiles[i].collision = (Objects.equals(tileNames[i][0], "true"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("maps/map_01.txt");
        if (stream == null) return;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        // read in the metadata
        // metadata layout ⌄
        // center tile x, centre tile y

        String metaDataLine;
        try {
            metaDataLine = reader.readLine();
        } catch (IOException e) {
            metaDataLine = null;
            e.printStackTrace();
        }

        if (metaDataLine == null) return;

        String[] metaData = metaDataLine.split(" ");
        centreTileX = Integer.parseInt(String.valueOf(metaData[0]));
        centreTileY = Integer.parseInt(String.valueOf(metaData[1]));

        while (true) {
            String line;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                line = null;
                e.printStackTrace();
            }

            if (line == null) break;

            String[] numbers = line.split(" ");
            ArrayList<Integer> rowList = new ArrayList<>();

            for (int i = 0; i < numbers.length; i++) {
                rowList.add(Integer.parseInt(String.valueOf(numbers[i])));
            }
            map.add(rowList);
        }
    }

    public int tileCoordToScreenLoc(int coord, Plane plane) {
        return (int) Math.floor((double) (coord * Config.tileSize) + ((double) ((plane == Plane.X ? Config.WINDOW_TILE_WIDTH : Config.WINDOW_TILE_HEIGHT) * Config.tileSize) / 2));
    }

    public void draw(Graphics2D g2d) {

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                int id = map.get(i).get(j);
                int tileX = j - centreTileX;
                int tileY = i - centreTileY;
                g2d.drawImage(tiles[id].tile, tileCoordToScreenLoc(tileX, Plane.X) + gamePanel.cameraX, tileCoordToScreenLoc(tileY, Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize, null);
            }
        }

        if (Config.Debug.showCameraBufferBox) g2d.drawRect(480 - Config.cameraBuffer, 384 - Config.cameraBuffer, Config.cameraBuffer * 2 , Config.cameraBuffer * 2);
    }
}
