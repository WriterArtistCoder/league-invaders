import java.awt.Dimension;

import javax.swing.*;

public class LeagueInvaders {

	public JFrame window;
	public GamePanel panel;
	final int windowSizeX = 500;
	final int windowSizeY = 800;

	public LeagueInvaders() {
		window = new JFrame();
		panel = new GamePanel();
	}

	public static void main(String args[]) {
		LeagueInvaders instance = new LeagueInvaders();
		instance.setup();
	}

	public void setup() {
		window.add(panel);
		window.getContentPane().setPreferredSize(new Dimension(windowSizeX, windowSizeY));
		window.pack();
		window.addKeyListener(panel);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		panel.startGame();
	}

}
