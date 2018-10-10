import java.awt.Graphics;

public class Firebit extends GameObject {

	public Firebit(Alien a) {
		super(a.x, a.y, a.width, a.height);
		
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(Graphics g) {
		g.drawImage(GamePanel.firebitImg, x, y, width, height, null);
	}

}
