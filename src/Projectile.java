import java.awt.Graphics;

public class Projectile extends GameObject {
	
	public int speed = 10;
	public boolean isSuper = false;

	public Projectile(int xa, int ya, int widtha, int heighta) {
		super(xa, ya, widtha, heighta);
		
	}
	
	public void update() {
		super.update();
		y -= speed;
		
		if (y <= 0) {
			isAlive = false;
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(GamePanel.bulletImg, x, y, width, height, null);
	}

}
