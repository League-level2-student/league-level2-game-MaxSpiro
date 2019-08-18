import javax.swing.JFrame;

public class Game {
	JFrame frame;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	GamePanel gamePanel;
	Game(){
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	void setup() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.addKeyListener(gamePanel);
		frame.setVisible(true);
	}
public static void main(String[] args) {
	Game cactusJump = new Game();
	cactusJump.setup();
}
}
