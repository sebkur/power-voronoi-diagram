package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;

import de.topobyte.swing.util.action.SimpleAction;

/**
 * @author Sebastian Kuerten
 */
public class QuitAction extends SimpleAction
{

	private static final long serialVersionUID = 1L;

	public QuitAction()
	{
		super("Quit", "Exit the application");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}

}
