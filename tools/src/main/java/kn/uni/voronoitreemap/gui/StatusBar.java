package kn.uni.voronoitreemap.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.topobyte.awt.util.GridBagConstraintsEditor;

/**
 * @author Sebastian Kuerten
 */
public class StatusBar extends JPanel
{

	private static final long serialVersionUID = 1L;

	private JLabel label;

	private int boxWidth;
	private int boxHeight;

	private int mouseX;
	private int mouseY;

	public StatusBar()
	{
		setLayout(new GridBagLayout());

		GridBagConstraintsEditor c = new GridBagConstraintsEditor();

		label = new JLabel();

		c.fill(GridBagConstraints.BOTH).weightX(1.0).gridPos(0, 0);

		add(label, c.getConstraints());
	}

	public void setText(String text)
	{
		label.setText(text);
	}

	public void setDefaultText()
	{
		label.setText(String.format("size: %d x %d mouse: %d, %d", boxWidth,
				boxHeight, mouseX, mouseY));
	}

	public void setBoxSize(int width, int height)
	{
		boxWidth = width;
		boxHeight = height;
	}

	public void setMouse(int x, int y)
	{
		mouseX = x;
		mouseY = y;
	}

}
