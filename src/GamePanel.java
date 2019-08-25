import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int SELECT = 1;
	final int GAME = 2;
	final int END = 3;
	final int EASY = 1;
	final int MEDIUM = 2;
	final int HARD = 3;
	int currentState = MENU;
	Font titleFont;
	Font smallFont;
	Timer frameDraw;
	Timer cactusSpawn;
	public int difficulty;
	Dinosaur dino;
	ObjectManager objectmanager;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	GamePanel(){
//		if(difficulty==EASY) {
//			dino = new Dinosaur(100,500,50,50,0.6);
//			objectmanager = new ObjectManager(dino,3.25,0.15);
//		
//		} else if(difficulty==MEDIUM) {
//			dino = new Dinosaur(100,500,50,50,0.8);
//			objectmanager = new ObjectManager(dino,4,0.25);
//		} else if(difficulty==HARD) {
//			dino = new Dinosaur(100,500,50,50,1);
//			objectmanager = new ObjectManager(dino,5.5,0.4);
//		} else {
			dino = new Dinosaur(100,500,50,50,1);
			objectmanager = new ObjectManager(dino,1,1);
		//}
		titleFont = new Font("Arial",Font.PLAIN,48);
		smallFont = new Font("Arial",Font.PLAIN,32);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("background.png");
		}
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState==SELECT) {
			drawSelectState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	void updateMenuState() {
		
	}
	void updateSelectState() {
		
	}
	void updateGameState() {
		objectmanager.update();
		dino.jump();
		if(dino.isActive==false) {
			currentState=END;
		}
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Cactus Jump", 75/500 * WIDTH, 100/800 * HEIGHT);
		g.setFont(smallFont);
		g.drawString("Press ENTER to start",100/500 * WIDTH,350/800 * HEIGHT);
		g.drawString("Press SPACE for instructions", 50/500 * WIDTH, 500/800 * HEIGHT);
	}
	void drawSelectState(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}
	void drawGameState(Graphics g) {
		g.drawImage(image,0,0,Game.WIDTH,Game.HEIGHT,null);
		objectmanager.draw(g);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Score: "+objectmanager.getScore(), 75, 100);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 125, 100);
		g.setFont(smallFont);
		g.drawString("Press ENTER to restart",100,500);
		g.drawString("Your score was "+objectmanager.getScore(),100,300);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == SELECT) {
			updateSelectState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
		
	}
	void startGame(){
		cactusSpawn = new Timer(2700, objectmanager);
	    cactusSpawn.start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState==END) {
				if(difficulty==EASY) {
					dino = new Dinosaur(100,500,50,50,0.6);
					objectmanager = new ObjectManager(dino,3.25,0.15);
				
				} else if(difficulty==MEDIUM) {
					dino = new Dinosaur(100,500,50,50,0.8);
					objectmanager = new ObjectManager(dino,4,0.25);
				} else if(difficulty==HARD) {
					dino = new Dinosaur(100,500,50,50,1);
					objectmanager = new ObjectManager(dino,5.5,0.4);
				} else {
					dino = new Dinosaur(100,500,50,50,0.8);
					objectmanager = new ObjectManager(dino,4,0.25);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState==MENU){
				JOptionPane.showMessageDialog(null, " Jump with SPACE \n "
						+ "Jump over cactuses \n Go as far as you can!");
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } 
		    else if(currentState == MENU) {
		    	currentState=SELECT;
		    	
		    }
		    else if(currentState == GAME){
		        currentState=END;
		        cactusSpawn.stop();
		    }
		}
		if(currentState==SELECT) {
			if(e.getKeyCode() ==KeyEvent.VK_1) { // easy
				dino.difficulty=EASY;
				difficulty=EASY;
				dino = new Dinosaur(100,500,50,50,0.6);
				objectmanager = new ObjectManager(dino,3.25,0.15);
				currentState=GAME;
				startGame();
			}
			if(e.getKeyCode()==KeyEvent.VK_5) { // medium
				dino.difficulty=MEDIUM;
				difficulty=MEDIUM;
				dino = new Dinosaur(100,500,50,50,0.8);
				objectmanager = new ObjectManager(dino,4,0.25);
				currentState=GAME;
				startGame();
			}
			if(e.getKeyCode()==KeyEvent.VK_9) { // hard
				dino.difficulty=HARD;
				difficulty=HARD;
				dino = new Dinosaur(100,500,50,50,1);
				objectmanager = new ObjectManager(dino,5.5,0.4);
				currentState=GAME;
				startGame();
			}
		}
		if(currentState==GAME) {
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				dino.jump=true;
			}
			if(e.getKeyCode()==KeyEvent.VK_Z) {
				dino.fall();
			}

		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
