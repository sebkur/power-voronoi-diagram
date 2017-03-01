package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.swing.util.Components;
import kn.uni.voronoitreemap.gui.PowerBox;
import kn.uni.voronoitreemap.io.SiteIO;

/**
 * @author Sebastian Kuerten
 */
public class SaveAction extends PowerBoxAction
{

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(SaveAction.class);

	public SaveAction(PowerBox powerBox)
	{
		super(powerBox, "Save", "Save sites to file");
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		JFrame frame = Components.getContainingFrame(powerBox);
		JFileChooser chooser = new JFileChooser();
		int value = chooser.showSaveDialog(frame);
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			logger.debug("attempting to write document to file: " + file);
			try {
				OutputStream output = new FileOutputStream(file);
				SiteIO.write(output, powerBox.getSitesAsList());
				output.close();
			} catch (IOException e) {
				logger.debug("unable to write file", e);
			}
		}
	}

}
