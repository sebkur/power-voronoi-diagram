package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import de.topobyte.swing.util.action.SimpleAction;
import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * An abstract base class for actions that work on a {@link PowerBox} and
 * manipulate a boolean value.
 * 
 * @author Sebastian Kuerten
 */
public abstract class PowerBoxActionBoolean extends SimpleAction
{

	private static final long serialVersionUID = 1L;

	protected PowerBox powerBox;

	public PowerBoxActionBoolean(PowerBox powerBox, String name,
			String description)
	{
		super(name, description);
		this.powerBox = powerBox;
	}

	@Override
	public Object getValue(String key)
	{
		if (key.equals(Action.SELECTED_KEY)) {
			return getValue();
		}
		return super.getValue(key);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		boolean oldValue = getValue();
		boolean newValue = !oldValue;
		setValue(newValue);
		firePropertyChange(Action.SELECTED_KEY, oldValue, newValue);
		powerBox.repaint();
	}

	protected abstract boolean getValue();

	protected abstract void setValue(boolean value);

}
