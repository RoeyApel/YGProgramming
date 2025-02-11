public class GameLoop implements Runnable {
    private Thread gameThread;
    private final Game game;
    private volatile boolean running = false;

    private final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME = 1_000_000_000 / TARGET_FPS;

    public GameLoop(Game game) {
        this.game = game;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("error in stopping thread!!!");
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long now;
        double deltaTime;

        while (running) {
            now = System.nanoTime();
            deltaTime = (double) (now - lastTime) / 1_000_000_000;
            lastTime = now;

            game.update(deltaTime);
            game.gamePanel.repaint();

            long timeTaken = System.nanoTime() - now;
            long sleepTime = (OPTIMAL_TIME - timeTaken) / 1_000_000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
