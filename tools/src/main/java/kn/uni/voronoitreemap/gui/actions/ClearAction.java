package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class ClearAction extends PowerBoxAction
{

	private static final long serialVersionUID = 1L;

	public ClearAction(PowerBox powerBox)
	{
		super(powerBox, "Clear", "Remove all sites");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		powerBox.clearSites();
		powerBox.computeDiagram();
	}

}
