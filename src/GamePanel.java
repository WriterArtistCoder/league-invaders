import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	public Timer frameRate;

	public Font titleFont;
	public Font subtitleFont;
	public Font textFont;
	
	public Rocketship ship = new Rocketship(250, 700, 50, 50);
	public ObjectManager manager = new ObjectManager(ship);

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	public int currentState = MENU_STATE;

	public GamePanel() {
		frameRate = new Timer(1000 / 60, this);

		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtitleFont = new Font("Georgia", Font.BOLD, 18);
		textFont = new Font("Arial", Font.PLAIN, 20);
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

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 10:
			if (currentState >= END_STATE) {
				currentState = MENU_STATE;
			} else {
				currentState++;
			}
			break;
		case 37:
			ship.x -= ship.speed;
			break;
		case 38:
			ship.y -= ship.speed;
			break;
		case 39:
			ship.x += ship.speed;
			break;
		case 40:
			ship.y += ship.speed;
			break;
		case (int) ' ':
			manager.addProjectile(new Projectile(ship.x+(ship.width/2)-5, ship.y, 10, 10));
			break;
		default:
			break;
		}
		
	}

	public void keyReleased(KeyEvent e) {

	}

	public void updateMenuState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, new LeagueInvaders().windowSizeX, new LeagueInvaders().windowSizeY);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 50);

		g.setFont(subtitleFont);
		g.setColor(Color.GREEN);
		g.drawString("WRITERARTISTCODER EDITION!", 70, 80);

		g.setFont(textFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press enter to start", 70, 140);
		g.drawString("Press space for instructions", 70, 160);
	}

	public void updateGameState() {
		manager.update();
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, new LeagueInvaders().windowSizeX, new LeagueInvaders().windowSizeY);

		manager.draw(g);
	}

	public void updateEndState() {

	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, new LeagueInvaders().windowSizeX, new LeagueInvaders().windowSizeY);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 80, 100);
	}

}
