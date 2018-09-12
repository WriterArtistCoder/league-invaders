import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager {
	public Rocketship ship;
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public ArrayList<Alien> aliens = new ArrayList<Alien>();
	public Timer enemyManager = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addAlien(new Alien(new Random().nextInt(new LeagueInvaders().windowSizeX), 0, 50, 50));
		}
	});

	public ObjectManager(Rocketship shipa) {
		ship = shipa;
	}
	
	public void update() {
		ship.update();
		
		for (Projectile p : projectiles) {
			p.update();
		}
		
		for (Alien a : aliens) {
			a.update();
		}
	}
	
	public void draw(Graphics g) {
		ship.draw(g);
		
		for (Projectile p : projectiles) {
			p.draw(g);
		}
		
		for (Alien a : aliens) {
			a.draw(g);
		}
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	public void addAlien(Alien a) {
		aliens.add(a);
	}
	
	public void startManageEnemies() {
		enemyManager.start();
	}
	
	public void purgeObjects() {
		for (Projectile p : projectiles) {
			if (!p.isAlive) {
				projectiles.remove(p);
			}
		}
		
		for (Alien a : aliens) {
			if (!a.isAlive) {
				aliens.remove(a);
			}
		}
	}

}
