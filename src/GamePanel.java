import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	public Timer frameRate;

	public Font titleFont;
	public Font subtitleFont;
	public Font textFont;

	public Rocketship ship = new Rocketship(250, 700, 50, 50);
	public ObjectManager manager = new ObjectManager(ship);

	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spaceImg;
	public static BufferedImage firebitImg;
	public static BufferedImage superBulletImg;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	public int currentState = MENU_STATE;

	public GamePanel() {
		frameRate = new Timer(1000 / 60, this);

		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtitleFont = new Font("Georgia", Font.BOLD, 18);
		textFont = new Font("Arial", Font.PLAIN, 20);

		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
			firebitImg = ImageIO.read(this.getClass().getResourceAsStream("firebit.png"));
			superBulletImg = ImageIO.read(this.getClass().getResourceAsStream("superBullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame() {
		frameRate.start();
		manager.startManageEnemies();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		switch (currentState) {
		case MENU_STATE:
			updateMenuState();
			break;
		case GAME_STATE:
			updateGameState();
			break;
		case END_STATE:
			updateEndState();
			break;
		default:
			break;
		}
	}

	public void paintComponent(Graphics g) {
		switch (currentState) {
		case MENU_STATE:
			drawMenuState(g);
			break;
		case GAME_STATE:
			drawGameState(g);
			break;
		case END_STATE:
			drawEndState(g);
			break;
		default:
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 10:
			if (currentState >= END_STATE) {
				if (currentState == END_STATE) {
					ship = new Rocketship(250, 700, 50, 50);
					manager = new ObjectManager(ship);
					manager.startManageEnemies();
				}
				currentState = MENU_STATE;
			} else {
				currentState++;
			}
			break;
		case 37:
			if (ship.x > 0) {
				ship.x -= ship.speed;
			}
			break;
		case 38:
			if (ship.y > 0) {
				ship.y -= ship.speed;
			}
			break;
		case 39:
			if (ship.x < LeagueInvaders.windowSizeX-ship.width) {
				ship.x += ship.speed;
			}
			break;
		case 40:
			if (ship.y < LeagueInvaders.windowSizeY-ship.height) {
				ship.y += ship.speed;
			}
			break;
		case (int) ' ':
			if (currentState == MENU_STATE) {
				JOptionPane.showMessageDialog(null, "--CONTROLS--\n Press space to fire.\n Press K to super-fire (see SUPERFIRING.)\n Press enter to surrender." 
						+ "\n --NOTES--\n It takes 0.8s to reload."
						+ "\n --FUEL--\n When enemies are hit, they will respawn\n on their planet and continue advancing.\n They will also drop a star called a firebit.\n These will fuel your cannon with 2 new shots\n when collected."
						+ "\n --SUPERFIRING--\n Superfiring takes twice the energy but\n destroys every enemy existing.");
			} else if (currentState == GAME_STATE && ship.fuel > 0) {
				if ((int) System.currentTimeMillis()-manager.reloadTime > manager.lastFireTime) {
					manager.addProjectile(new Projectile(ship.x + (ship.width / 2) - 5, ship.y, 10, 10));
					manager.lastFireTime = (int) System.currentTimeMillis();
					ship.fuel--;
				}
			}
			break;
		case (int) 'K':
			if (currentState == GAME_STATE && ship.fuel >= 2) {
				if ((int) System.currentTimeMillis()-manager.reloadTime > manager.lastFireTime) {
					manager.addProjectile(new SuperProjectile(ship.x + (ship.width / 2) - 5, ship.y, 10, 10, manager));
					manager.lastFireTime = (int) System.currentTimeMillis();
					ship.fuel-=2;
				}
			}
			break;
		default:
			break;
		}

	}

	public void updateMenuState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.windowSizeX, LeagueInvaders.windowSizeY);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 50);

		g.setFont(subtitleFont);
		g.setColor(Color.GREEN);
		g.drawString("WRITERARTISTCODER EDITION: " + LeagueInvaders.VERSION, 70, 80);

		g.setFont(textFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press enter to start", 70, 140);
		g.drawString("Press space for instructions", 70, 160);
	}

	public void updateGameState() {
		manager.update();
		manager.purgeObjects();
		manager.checkCollision();

		if (!ship.isAlive) {
			currentState = END_STATE;
		}
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.windowSizeX, LeagueInvaders.windowSizeY, null);

		manager.draw(g);
	}

	public void updateEndState() {

	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.windowSizeX, LeagueInvaders.windowSizeY);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 80, 100);
		
		g.setFont(subtitleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Your score is " + manager.score, 80, 150);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

}
