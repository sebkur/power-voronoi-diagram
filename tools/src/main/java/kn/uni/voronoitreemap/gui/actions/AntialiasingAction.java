package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * @author Sebastian Kuerten
 */
public class AntialiasingAction extends PowerBoxAction
{

	private static final long serialVersionUID = 1L;

	public AntialiasingAction(PowerBox powerBox)
	{
		super(powerBox, "Antialiasing", "High quality rendering");
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		boolean oldValue = powerBox.isAntialiasing();
		boolean newValue = !oldValue;
		powerBox.setAntialiasing(newValue);
		firePropertyChange(Action.SELECTED_KEY, oldValue, newValue);
		powerBox.repaint();
	}

	@Override
	public Object getValue(String key)
	{
		if (key.equals(Action.SELECTED_KEY)) {
			return powerBox.isAntialiasing();
		}
		return super.getValue(key);
	}

}
