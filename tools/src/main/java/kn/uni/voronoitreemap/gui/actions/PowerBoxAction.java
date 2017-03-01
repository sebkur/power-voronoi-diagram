package kn.uni.voronoitreemap.gui.actions;

import de.topobyte.swing.util.action.SimpleAction;
import kn.uni.voronoitreemap.gui.PowerBox;

/**
 * An abstract base class for actions that work on a {@link PowerBox}.
 * 
 * @author Sebastian Kuerten
 */
public abstract class PowerBoxAction extends SimpleAction
{

	private static final long serialVersionUID = 1L;

	protected PowerBox powerBox;

	public PowerBoxAction(PowerBox powerBox, String name, String description)
	{
		super(name, description);
		this.powerBox = powerBox;
	}

}
