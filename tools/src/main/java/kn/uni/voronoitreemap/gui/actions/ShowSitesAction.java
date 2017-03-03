package kn.uni.voronoitreemap.gui.actions;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class ShowSitesAction extends PowerBoxActionBoolean
{

	private static final long serialVersionUID = 1L;

	public ShowSitesAction(PowerBox powerBox)
	{
		super(powerBox, "Sites", "Show sites");
	}

	@Override
	protected boolean getValue()
	{
		return powerBox.isShowSites();
	}

	@Override
	protected void setValue(boolean value)
	{
		powerBox.setShowSites(value);
	}

}
