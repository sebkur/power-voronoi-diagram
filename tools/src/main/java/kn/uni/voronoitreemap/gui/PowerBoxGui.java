package kn.uni.voronoitreemap.gui;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import kn.uni.voronoitreemap.gui.actions.AntialiasingAction;
import kn.uni.voronoitreemap.gui.actions.CentroidsAction;
import kn.uni.voronoitreemap.gui.actions.ClearAction;
import kn.uni.voronoitreemap.gui.actions.QuitAction;

/**
 * A GUI that shows a {@link PowerBox}.
 * 
 * @author Sebastian Kuerten
 */
public class PowerBoxGui
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("PowerBox GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main content
		PowerBox powerBox = new PowerBox();
		powerBox.setAntialiasing(true);

		frame.setContentPane(powerBox);

		// Actions
		ClearAction clear = new ClearAction(powerBox);
		QuitAction quit = new QuitAction();

		AntialiasingAction antialiasing = new AntialiasingAction(powerBox);
		CentroidsAction centroids = new CentroidsAction(powerBox);

		// Menu bar menus
		JMenu menuFile = new JMenu("File");
		JMenu menuOptions = new JMenu("Options");

		menuFile.add(clear);
		menuFile.add("Load");
		menuFile.add("Save");
		menuFile.add(quit);

		menuOptions.add(new JCheckBoxMenuItem(antialiasing));
		menuOptions.add(new JCheckBoxMenuItem(centroids));

		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuBar.add(menuFile);
		menuBar.add(menuOptions);

		// Show frame
		frame.setVisible(true);
		frame.setSize(800, 600);
	}

}
