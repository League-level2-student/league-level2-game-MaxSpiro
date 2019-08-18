import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Cactus extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	double speed;
	Cactus(int x, int y, int width, int height, double speed) {
		super(x, y, width, height);
		this.speed=speed;
		// TODO Auto-generated constructor stub
		if (needImage) {
		    loadImage ("cactus.jpg");
		}
	}
	void update() {
		x-=speed;
		super.update();
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.GREEN);
	        g.fillRect(x, y, width, height);
		}
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
