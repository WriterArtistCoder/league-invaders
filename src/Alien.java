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
		g.drawImage(GamePanel.alienImg, x, y, width, height, null);
	}
	
	public void onHit(ObjectManager manager) {
		isAlive = false;
		manager.addFirebit(new Firebit(this));
	}

}
