import java.awt.Graphics;

public class Rocketship extends GameObject {

	public int speed;
	public int fuel;
	
	public Rocketship(int xa, int ya, int widtha, int heighta) {
		super(xa, ya, widtha, heighta);
		fuel = 10;
		speed = 20;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}

}
