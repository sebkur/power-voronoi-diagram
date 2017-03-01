/*******************************************************************************
 * Copyright (c) 2013 Arlind Nocaj, University of Konstanz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * For distributors of proprietary software, other licensing is possible on request: arlind.nocaj@gmail.com
 * 
 * This work is based on the publication below, please cite on usage, e.g.,  when publishing an article.
 * Arlind Nocaj, Ulrik Brandes, "Computing Voronoi Treemaps: Faster, Simpler, and Resolution-independent", Computer Graphics Forum, vol. 31, no. 3, June 2012, pp. 855-864
 ******************************************************************************/
package kn.uni.voronoitreemap.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Double;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JPanel;

import kn.uni.voronoitreemap.datastructure.OpenList;
import kn.uni.voronoitreemap.diagram.PowerDiagram;
import kn.uni.voronoitreemap.j2d.Point2D;
import kn.uni.voronoitreemap.j2d.PolygonSimple;
import kn.uni.voronoitreemap.j2d.Site;

/**
 * JPanel where you can place and move sites around. The weights can be changed,
 * too. Clicking a site with the left mouse button increases its weight and
 * clicking a site with the right mouse button decreases it. Press and drag a
 * site to move its location.
 * 
 * @author Arlind Nocaj
 */
public class PowerBox extends JPanel
{

	OpenList sites = new OpenList();
	PolygonSimple clipPoly = new PolygonSimple();

	HashMap<Double, HashSet<Site>> vertices = new HashMap<Double, HashSet<Site>>();

	JPanel panel = new JPanel();

	public PowerBox()
	{
		super();
		clipPoly.add(50, 50);
		clipPoly.add(50, 800);
		clipPoly.add(800, 800);
		clipPoly.add(800, 50);
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}

	private MouseAdapter mouseAdapter = new MouseAdapter() {

		private Site dragSite = null;

		@Override
		public void mouseClicked(MouseEvent e)
		{

			Site site = getSite(e.getX(), e.getY());

			boolean changedWeight = false;
			if (site != null) {
				int modifiers = e.getModifiers();
				if ((modifiers
						& InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
					System.out.println("Left button pressed.");
					site.setWeight(
							Math.pow((Math.sqrt(site.getWeight()) + 10), 2));
					changedWeight = true;
				}
				if ((modifiers
						& InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
					System.out.println("Middle button pressed.");
				}
				if ((modifiers
						& InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
					System.out.println("Right button pressed.");
					site.setWeight(
							Math.pow((Math.sqrt(site.getWeight()) - 10), 2));
					changedWeight = true;
				}
			}
			if (changedWeight) {
				computeDiagram();
				repaint();
				return;
			}

			addSite(e.getPoint());

			computeDiagram();
			setPreferredSize(getSize());
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			dragSite = getSite(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			dragSite = null;
		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			if (dragSite == null) {
				return;
			}
			Point point = e.getPoint();
			dragSite.setXY(point.getX(), point.getY());
			computeDiagram();
			invalidate();
		}

	};

	protected void addSite(Point p)
	{
		double weight = 30;
		Site site = new Site(p.getX(), p.getY(), weight);
		sites.add(site);
	}

	/**
	 * Determine a Site close to the specified location.
	 * 
	 * @return a close Site, or null if none is found within 10 pixels distance.
	 */
	protected Site getSite(int x, int y)
	{
		for (Site site : sites) {
			double distance = new kn.uni.voronoitreemap.j2d.Point2D(x, y)
					.distance(site.getX(), site.getY());
			if (distance < 10) {
				return site;
			}
		}
		return null;
	}

	public void computeDiagram()
	{

		PowerDiagram diagram = new PowerDiagram(sites, clipPoly);
		diagram.computeDiagram();
		setPreferredSize(getSize());
		repaint();
	}

	@Override
	public void paint(Graphics g2)
	{
		super.paint(g2);
//		paintComponents(g2);

		Graphics2D g = (Graphics2D) g2;
		g.setColor(Color.red);
		Site[] array = sites.array;
		int size = sites.size;

		// Draw sites
		g2.setColor(Color.green);
		for (int z = 0; z < size; z++) {
			Site s = array[z];
			double posX = s.getX();
			double posY = s.getY();
			double radius = Math.sqrt(s.getWeight());
			// Draw a circle that reflects the weight
			g.drawOval((int) posX - (int) radius, (int) posY - (int) radius,
					(int) (2 * radius), (int) (2 * radius));
			// and a square around the site
			int r2 = 5;
			Color normal = g.getColor();
			if (s.getPolygon() == null) {
				g.setColor(Color.red);
			}
			g.drawRect((int) posX - r2, (int) posY - r2, 2 * r2, 2 * r2);
			g.setColor(normal);
		}
//		if (vertices!=null){
//		for (Point2D p:vertices.keySet()){
//			g.setColor(Color.green);
//			int radius=5;
//			g.drawRect((int)p.getX()-radius, (int)p.getY()-radius, 2*radius, 2*radius);
//			
//		}
//		}
//		if (lines!=null){
//		for (HLine line:lines){
//			line.paint((Graphics2D)g);
//		}
//		}

		// Draw Voronoi cells
		g.setColor(Color.GRAY.brighter());
		int i = 0;
		for (int z = 0; z < size; z++) {
			Site s = array[z];
			if (i != -2) {
				PolygonSimple poly = s.getPolygon();
				if (poly != null) {
					g.draw(poly);
				}
			}
			i++;
		}

		// Draw sites and Voronoi cell centroids
		g.setColor(Color.blue);
		for (int z = 0; z < size; z++) {
			Site s = array[z];
			double posX = s.getX();
			double posY = s.getY();
			double radius = Math.sqrt(s.getWeight());

			// Draw a circle that reflects the weight
			g.drawOval((int) posX - (int) radius, (int) posY - (int) radius,
					(int) (2 * radius), (int) (2 * radius));
			// and a square around the site
			int r2 = 7;
			g.drawRect((int) posX - r2, (int) posY - r2, 2 * r2, 2 * r2);

			PolygonSimple poly = s.getPolygon();
			// Draw the centroid of the Voronoi cell
			if (poly != null) {
				Point2D center = poly.getCentroid();
				r2 = 5;
				g.drawRect((int) center.getX() - r2, (int) center.getY() - r2,
						2 * r2, 2 * r2);
			}
		}

		this.validate();
	}

}
