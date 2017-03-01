package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class CentroidsAction extends PowerBoxAction
{

	private static final long serialVersionUID = 1L;

	public CentroidsAction(PowerBox powerBox)
	{
		super(powerBox, "Centroids", "Show cell centroids");
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		boolean oldValue = powerBox.isShowCentroids();
		boolean newValue = !oldValue;
		powerBox.setShowCentroids(newValue);
		firePropertyChange(Action.SELECTED_KEY, oldValue, newValue);
		powerBox.repaint();
	}

	@Override
	public Object getValue(String key)
	{
		if (key.equals(Action.SELECTED_KEY)) {
			return powerBox.isShowCentroids();
		}
		return super.getValue(key);
	}

}
