package kn.uni.voronoitreemap.gui.actions;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class ShowCentroidsAction extends PowerBoxActionBoolean
{

	private static final long serialVersionUID = 1L;

	public ShowCentroidsAction(PowerBox powerBox)
	{
		super(powerBox, "Centroids", "Show cell centroids");
	}

	@Override
	protected boolean getValue()
	{
		return powerBox.isShowCentroids();
	}

	@Override
	protected void setValue(boolean value)
	{
		powerBox.setShowCentroids(value);
	}

}
