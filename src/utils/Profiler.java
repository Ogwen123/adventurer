package utils;
//import static utils.Utils.logger;

public class Profiler {
    public double frameTime;
    public float fps;
    public boolean seen = false;

    // constants


    public Profiler() {
        frameTime = 0;
        fps = 0;
    }

    public void updateProfilerStats(long timeTaken) {
        double roundingFactor = Math.pow(10, Config.DISPLAY_DECIMALS);

        this.frameTime = (double) Math.round(((float) timeTaken / 1_000_000) * roundingFactor) / roundingFactor; // nano to millis
        //logger("*bProcessing time taken *c(Max)*b: " + frameTime + " *c(16.67)*x");
        //this.fps = 1/(timeTaken / 1_000_000_000);
        this.seen = false;
    }
}
