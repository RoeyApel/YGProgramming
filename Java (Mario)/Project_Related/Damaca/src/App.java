public class App {
    public static void main(String[] args) throws Exception {
        GameFrame gameFrame = new GameFrame();

        Game game = new Game();
        gameFrame.add(game);

        game.repaint();
        gameFrame.pack();
        gameFrame.setVisible(true);

    }
}
