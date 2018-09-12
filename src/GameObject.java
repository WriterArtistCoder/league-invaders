import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	
	int x;
	int y;
	int width;
	int height;
	public boolean isAlive = true;
	public Rectangle collisionBox;
	
	public GameObject(int xa, int ya, int widtha, int heighta) {
		x = xa;
		y = ya;
		width = widtha;
		height = heighta;
	}
	
	public void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g) {
		
	}

}
