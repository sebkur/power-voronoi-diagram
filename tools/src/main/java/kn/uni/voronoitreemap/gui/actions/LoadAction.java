package kn.uni.voronoitreemap.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.swing.util.Components;
import kn.uni.voronoitreemap.gui.PowerBox;
import kn.uni.voronoitreemap.io.SiteIO;
import kn.uni.voronoitreemap.j2d.Site;

/**
 * @author Sebastian Kuerten
 */
public class LoadAction extends PowerBoxAction
{

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(LoadAction.class);

	public LoadAction(PowerBox powerBox)
	{
		super(powerBox, "Load", "Load sites from file");
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		JFrame frame = Components.getContainingFrame(powerBox);
		JFileChooser chooser = new JFileChooser();
		int value = chooser.showOpenDialog(frame);
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			logger.debug("attempting to open document from file: " + file);
			try {
				InputStream input = new FileInputStream(file);
				List<Site> sites = SiteIO.read(input);

				powerBox.clearSites();
				for (Site site : sites) {
					powerBox.addSite(site.getX(), site.getY(),
							site.getWeight());
				}
				powerBox.computeDiagram();
			} catch (IOException e) {
				logger.debug("unable to read file", e);
			}
		}
	}

}
