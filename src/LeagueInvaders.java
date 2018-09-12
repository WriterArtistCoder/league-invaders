import java.awt.Dimension;
import javax.swing.*;

public class LeagueInvaders {

	public JFrame window;
	public GamePanel panel;
	public final static int windowSizeX = 500;
	public final static int windowSizeY = 800;
	public final static String VERSION = "v1.0.0";

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
