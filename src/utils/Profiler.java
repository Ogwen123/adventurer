package utils;
//import static utils.Utils.logger;

public class Profiler {
    public double processingTime;
    public float fps;
    public boolean seen = false;
    private long lastUpdate;

    public int cameraX, cameraY;
    public double x, y;

    // constants


    public Profiler() {
        processingTime = 0;
        fps = 0;
        lastUpdate = System.nanoTime();
    }

    public void updateProfilerStats(long processingTimeTaken, int cameraX, int cameraY, double playerX, double playerY) { // processingTimeTaken is in nanoseconds
        if (!Config.SHOW_STATS_PANEL) return;
        // calculate fps
        long time = System.nanoTime();
        long timeTaken = time - lastUpdate;
        lastUpdate = time;
        this.fps = 1_000_000_000f/timeTaken;

        double roundingFactor = Math.pow(10, Config.DISPLAY_DECIMALS);
        this.processingTime = (double) Math.round(((float) processingTimeTaken / 1_000_000) * roundingFactor) / roundingFactor; // nano to millis
        this.seen = false;

        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.x = playerX;
        this.y = playerY;
    }
}
