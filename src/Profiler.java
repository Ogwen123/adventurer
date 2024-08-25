public class Profiler {
    public int frameTime;
    public float fps;
    private long lastFrame;

    public Profiler() {
        frameTime = 0;
        fps = 0;
        lastFrame = System.nanoTime();
    }

    public void updateProfilerStats() {
        long time = System.nanoTime();
        this.frameTime = Math.round((float) ((time - this.lastFrame) / 1000) * 10) / 10; // nano to millis
        this.lastFrame = time;
        this.fps = 60;
    }
}
