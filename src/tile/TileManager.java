package tile;

import entity.Entity.Plane;
import main.GamePanel;
import utils.Config;
import utils.Utils;

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

    // THE BARRIER TILE SHOULD ALWAYS BE THE LAST TILE IN THE LIST
    private final String[][] tileNames = {{"dirt", "false"}, {"grass", "false"}, {"wall", "true"}, {"water_1", "false"}, {"water_2", "false"}, {"barrier", "true"}}; // {name, whether the block can be collided with}

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
        InputStream stream = getClass().getClassLoader().getResourceAsStream("maps/map_04.txt");
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
            Utils.logger("*rInvalid map data:*w Incorrect meta data.");
        }

        if (metaDataLine == null) {
            Utils.logger("*rInvalid map data:*w Incorrect meta data.");
            System.exit(0);
        }

        String[] metaData = metaDataLine.split(" ");

        if (metaData.length != 2) {
            Utils.logger("*rInvalid map data:*w Incorrect meta data.");
            System.exit(0);
        }

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

    public double distance(double x1, double y1, double x2, double y2) {

        double xDist = x1 - x2;
        double yDist = y1 - y2;

        //pythag
        double distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));

        return distance;
    }

    public void draw(Graphics2D g2d) {

        // draw tiles from map
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.getFirst().size(); j++) {
                int tileX = j - centreTileX;
                int tileY = i - centreTileY;

                // optimisations
                double distance = distance(tileX * Config.tileSize, tileY * Config.tileSize, gamePanel.getPlayerX(), gamePanel.getPlayerY());
                int threshold = (Math.max(Config.WINDOW_TILE_HEIGHT * Config.tileSize, Config.WINDOW_TILE_WIDTH * Config.tileSize));
                if (distance > threshold) continue;

                int id = map.get(i).get(j);
                g2d.drawImage(tiles[id].tile, tileCoordToScreenLoc(tileX, Plane.X) + gamePanel.cameraX, tileCoordToScreenLoc(tileY, Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize, null);
            }
        }

        // draw border using world dimensions

        // the "+2"s are to move the border out by one because the world should be the world tile height not border
        // i.e. if the world tile height is 5 the world should be 5x5 and the border should be 7x7
        for (int i = 0; i < Config.WORLD_TILE_HEIGHT + 2; i++) {
            for (int j = 0; j < Config.WORLD_TILE_WIDTH + 2; j++) {

                if (i == 0 || i == (Config.WORLD_TILE_HEIGHT + 1)) {
                    // top and bottom border
                    int tileX = j - (Config.WORLD_TILE_WIDTH / 2) - 1; // move the border blocks out by one
                    int tileY = i - (Config.WORLD_TILE_HEIGHT / 2) - 1;
                    g2d.drawImage(tiles[tileNames.length - 1].tile, tileCoordToScreenLoc(tileX, Plane.X) + gamePanel.cameraX, tileCoordToScreenLoc(tileY, Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize, null);

                } else if (j == 0 || j == (Config.WORLD_TILE_WIDTH + 1)) {
                    // left and right border
                    int tileX = j - (Config.WORLD_TILE_WIDTH / 2) - 1; // move the border blocks out by one
                    int tileY = i - (Config.WORLD_TILE_HEIGHT / 2) - 1;
                    g2d.drawImage(tiles[tileNames.length - 1].tile, tileCoordToScreenLoc(tileX, Plane.X) + gamePanel.cameraX, tileCoordToScreenLoc(tileY, Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize, null);

                }
            }
        }
    }
}
