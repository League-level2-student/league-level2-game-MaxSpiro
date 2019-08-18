import java.awt.Rectangle;

public class GameObject {
int x;
int y;
int width;
int height;
double speed;
boolean isActive;
Rectangle collisionBox;
GameObject(int x, int y, int width, int height){
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	isActive = true;
	collisionBox = new Rectangle(x,y,width,height);
}
void update() {
	collisionBox.setBounds(x,y,width,height);
}
}
