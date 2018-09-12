import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager {
	public Rocketship ship;
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	public int score = 0;
	
	public int lastFireTime;
	public int reloadTime = 800; // Time to reload in milliseconds
	
	public Timer enemyManager = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.windowSizeX), 0, 50, 50));
		}
	});

	public ObjectManager(Rocketship shipa) {
		ship = shipa;
		lastFireTime = (int) System.currentTimeMillis();
	}
	
	public void update() {
		if (ship.isAlive) {
			ship.update();
		}
		
		for (Projectile p : projectiles) {
			p.update();
		}
		
		for (Alien a : aliens) {
			a.update();
		}
	}
	
	public void draw(Graphics g) {
		if (ship.isAlive) {
			ship.draw(g);
		}
		
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
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (!p.isAlive) {
				projectiles.remove(p);
			}
		}
		
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			if (!a.isAlive) {
				aliens.remove(a);
			}
		}
		
		if (!ship.isAlive) {
			enemyManager.stop();
		}
	}
	
	public void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			if (ship.collisionBox.intersects(a.collisionBox)) {
				ship.isAlive = false;
			}
			
			for (int j = 0; j < projectiles.size(); j++) {
				Projectile p = projectiles.get(j);
				if (p.collisionBox.intersects(a.collisionBox)) {
					a.onHit();
					p.isAlive = false;
					score++;
				}
			}
		}
	}

}
