import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	public int speed;
	
	public Rocketship(int xa, int ya, int widtha, int heighta) {
		super(xa, ya, widtha, heighta);
		speed = 20;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

}
