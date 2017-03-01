package kn.uni.voronoitreemap.gui;

import javax.swing.JFrame;

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
	}

}
