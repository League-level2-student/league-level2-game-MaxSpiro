import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Dinosaur extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	boolean jump;
	public double weight;
	double jumpStrength;
	public int difficulty;
	final int EASY = 1;
	final int MEDIUM = 2;
	final int HARD = 3;
	public Dinosaur(int x, int y, int width, int height, double weight) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.weight=weight;
		if (needImage) {
	        loadImage ("dino.jpg");
	    }
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
		}
	}
	void update() {
		super.update();
	}
	public void jump() {
	System.out.println(difficulty);
	if(difficulty==EASY) {
		weight=0.6;
	} else if(difficulty==MEDIUM) {
		weight=0.8;	
	} else if(difficulty==HARD) {
		weight=1;
	}
		if(jump) {
			if(y>=500) {
				jumpStrength=16;
			}
			y-=jumpStrength;
			jumpStrength-=weight;
		}
		if(y>=500) {
			jump=false;
			y=500;
		}
	}
	public void fall() {
		weight*=15;
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
