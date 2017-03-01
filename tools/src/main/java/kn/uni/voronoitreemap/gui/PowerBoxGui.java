package kn.uni.voronoitreemap.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import de.topobyte.awt.util.GridBagConstraintsEditor;
import kn.uni.voronoitreemap.gui.actions.AntialiasingAction;
import kn.uni.voronoitreemap.gui.actions.CentroidsAction;
import kn.uni.voronoitreemap.gui.actions.ClearAction;
import kn.uni.voronoitreemap.gui.actions.LoadAction;
import kn.uni.voronoitreemap.gui.actions.QuitAction;
import kn.uni.voronoitreemap.gui.actions.SaveAction;
import kn.uni.voronoitreemap.j2d.Site;

/**
 * A GUI that shows a {@link PowerBox}.
 * 
 * @author Sebastian Kuerten
 */
public class PowerBoxGui
{

	private static PowerBox powerBox;
	private static StatusBar statusBar;

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("PowerBox GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout
		JPanel content = new JPanel();
		content.setLayout(new GridBagLayout());
		frame.setContentPane(content);

		powerBox = new PowerBox();
		powerBox.setAntialiasing(true);

		statusBar = new StatusBar();

		GridBagConstraintsEditor c = new GridBagConstraintsEditor();

		c.fill(GridBagConstraints.BOTH).weight(1, 1).gridPos(0, 0);
		content.add(powerBox, c.getConstraints());

		c.fill(GridBagConstraints.HORIZONTAL).weightY(0).gridPos(0, 1);
		content.add(statusBar, c.getConstraints());

		// Status bar
		powerBox.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e)
			{
				statusBar.setBoxSize(powerBox.getWidth(), powerBox.getHeight());
				statusBar.setDefaultText();
			}

		});

		powerBox.addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e)
			{
				updateMouseInfo(e);
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				updateMouseInfo(e);
			}

		});

		// Actions
		ClearAction clear = new ClearAction(powerBox);
		LoadAction load = new LoadAction(powerBox);
		SaveAction save = new SaveAction(powerBox);
		QuitAction quit = new QuitAction();

		AntialiasingAction antialiasing = new AntialiasingAction(powerBox);
		CentroidsAction centroids = new CentroidsAction(powerBox);

		// Menu bar menus
		JMenu menuFile = new JMenu("File");
		JMenu menuOptions = new JMenu("Options");

		menuFile.add(clear);
		menuFile.add(load);
		menuFile.add(save);
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

	protected static void updateMouseInfo(MouseEvent e)
	{
		statusBar.setMouse(e.getX(), e.getY());

		Site site = powerBox.getSite(e.getX(), e.getY());
		statusBar.setSite(site);

		statusBar.setDefaultText();
	}

}
