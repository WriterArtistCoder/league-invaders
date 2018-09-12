import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {

	public Alien(int xa, int ya, int widtha, int heighta) {
		super(xa, ya, widtha, heighta);
		
	}
	
	public void update() {
		super.update();
		y++;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}

}
