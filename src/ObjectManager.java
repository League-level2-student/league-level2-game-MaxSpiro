import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Dinosaur dino;
	ArrayList <Cactus> cactuses = new ArrayList();
	Random random = new Random();
	int score = 0;
	double speed = 3;
	public int cactusTotal;
	ObjectManager(Dinosaur dino){
		this.dino = dino;
	}
 	void addCactus() {
 		cactuses.add(new Cactus(Game.WIDTH,500,50,50,speed));
 		cactusTotal++;
 		if(cactusTotal%4==0) {
 			speed+=0.04;
 		}
 	}
 	void update() {
 		System.out.println(speed);
 		for(int i=0; i<cactuses.size(); i++) {
 			cactuses.get(i).update();
 			if(cactuses.get(i).y>Game.HEIGHT || cactuses.get(i).y<0 || cactuses.get(i).x>Game.WIDTH || cactuses.get(i).x<0) {
 				cactuses.get(i).isActive = false;
 				score++;
 			}
 		}
 		dino.update();
 		checkCollision();
 		purgeObjects();
 	}
 	int getScore() {
 		return this.score;
 	}
 	void draw(Graphics g) {
 		dino.draw(g);
 		for(int i=0;i<cactuses.size();i++) {
 			cactuses.get(i).draw(g);
 		}
 	}
 	void purgeObjects() {
 		for(int i=0;i<cactuses.size();i++) {
 			if(cactuses.get(i).isActive==false) {
 				cactuses.remove(i);
 			}
 		}
 	}
 	void checkCollision() {
 		for(int i=0;i<cactuses.size();i++) {
 			if(cactuses.get(i).collisionBox.intersects(dino.collisionBox)) {
 				dino.isActive = false;
 			}
 		}
 	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addCactus();
	}
}
