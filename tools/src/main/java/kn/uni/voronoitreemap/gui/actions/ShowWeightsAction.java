package kn.uni.voronoitreemap.gui.actions;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class ShowWeightsAction extends PowerBoxActionBoolean
{

	private static final long serialVersionUID = 1L;

	public ShowWeightsAction(PowerBox powerBox)
	{
		super(powerBox, "Weights", "Show circles");
	}

	@Override
	protected boolean getValue()
	{
		return powerBox.isShowWeights();
	}

	@Override
	protected void setValue(boolean value)
	{
		powerBox.setShowWeights(value);
	}

}
