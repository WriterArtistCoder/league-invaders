import java.awt.Color;
import java.awt.Graphics;

public class SuperProjectile extends Projectile {
	
	
	public int speed = 15;
	public boolean isSuper = true;
	public final int initY;
	public int spawnTime;
	public ObjectManager manager;

	public SuperProjectile(int xa, int ya, int widtha, int heighta, ObjectManager managera) {
		super(xa, ya, widtha, heighta);
		initY = ya;
		manager = managera;
	
	}
	
	public void update() {
		super.update();
		for (Alien a : manager.aliens) {
			a.onHit(manager);
			manager.score++;
		}
	}
	
	public void draw(Graphics g) {
		if ((LeagueInvaders.windowSizeY-y)/(LeagueInvaders.windowSizeY-initY) > 1) {
			g.setColor(new Color(1, 1, 1, 1));
		} else if ((LeagueInvaders.windowSizeY-y)/(LeagueInvaders.windowSizeY-initY) < 0) {
			g.setColor(new Color(1, 1, 1, 0));
		} else {
			g.setColor(new Color(1, 1, 1, (LeagueInvaders.windowSizeY-y)/(LeagueInvaders.windowSizeY-initY)));
		}
		System.out.println((LeagueInvaders.windowSizeY-y)/(LeagueInvaders.windowSizeY-initY));
		
		if (spawnTime < System.currentTimeMillis()-500) {
			g.fillRect(0, 0, LeagueInvaders.windowSizeX, LeagueInvaders.windowSizeY);
		}
		g.drawImage(GamePanel.superBulletImg, x, y, width, height, null);
	}

}
