package kn.uni.voronoitreemap.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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

		frame.setVisible(true);
		frame.setSize(800, 600);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.add("Clear");
		menuFile.add("Load");
		menuFile.add("Save");
		menuFile.add("Quit");

		menuBar.add(menuFile);
	}

}
