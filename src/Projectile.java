import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	
	public int speed = 10;

	public Projectile(int xa, int ya, int widtha, int heighta) {
		super(xa, ya, widtha, heighta);
		
	}
	
	public void update() {
		super.update();
		y -= speed;
		
		if (y < 0) {
			isAlive = false;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}
