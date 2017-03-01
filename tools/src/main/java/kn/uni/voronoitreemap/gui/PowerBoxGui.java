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

		PowerBox powerBox = new PowerBox();
		powerBox.setAntialiasing(true);

		frame.setContentPane(powerBox);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.add(new ClearAction(powerBox));
		menuFile.add("Load");
		menuFile.add("Save");
		menuFile.add(new QuitAction());

		JMenu menuOptions = new JMenu("Options");
		menuOptions
				.add(new JCheckBoxMenuItem(new AntialiasingAction(powerBox)));
		menuOptions.add(new JCheckBoxMenuItem(new CentroidsAction(powerBox)));

		menuBar.add(menuFile);
		menuBar.add(menuOptions);

		frame.setVisible(true);
		frame.setSize(800, 600);
	}

}
