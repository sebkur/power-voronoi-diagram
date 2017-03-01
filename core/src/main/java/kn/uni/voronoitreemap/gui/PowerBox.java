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
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

	// Inner square for sites with cell
	private Color colorSitesWithCell = Color.GREEN;
	// Inner square for sites without cell
	private Color colorSitesWithoutCell = Color.RED;
	// Outer square for sites
	private Color colorSites = Color.BLUE;
	// Circles for sites
	private Color colorCircles = Color.BLACK;
	// Cell centroid markers
	private Color colorCentroids = Color.BLUE;
	// Cell edges
	private Color colorEdges = Color.GRAY.brighter();

	// Sizes of site markers
	private int sizeInnerSquares = 5;
	private int sizeOuterSquares = 7;
	// Size of centroid markers
	private int sizeCentroidSquares = 5;

	// Distance in pixels for detecting clicks on existing sites
	private double clickTolerance = 10;

	private int margin = 10;

	private boolean antialiasing = false;

	private boolean showCentroids = true;

	OpenList sites = new OpenList();
	PolygonSimple clipPoly;

	HashMap<Double, HashSet<Site>> vertices = new HashMap<Double, HashSet<Site>>();

	JPanel panel = new JPanel();

	public PowerBox()
	{
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		addComponentListener(componentAdapter);
	}

	protected void initClipPoly(int w, int h)
	{
		clipPoly = new PolygonSimple();
		clipPoly.add(margin, margin);
		clipPoly.add(margin, h - margin);
		clipPoly.add(w - margin, h - margin);
		clipPoly.add(w - margin, margin);
	}

	private ComponentAdapter componentAdapter = new ComponentAdapter() {

		@Override
		public void componentResized(ComponentEvent e)
		{
			initClipPoly(getWidth(), getHeight());
			computeDiagram();
		}

	};

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
					site.setWeight(
							Math.pow((Math.sqrt(site.getWeight()) + 10), 2));
					changedWeight = true;
				}
				if ((modifiers
						& InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
					// We could do something on middle click here
				}
				if ((modifiers
						& InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
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

	public void addSite(Point p)
	{
		double weight = 30;
		Site site = new Site(p.getX(), p.getY(), weight);
		sites.add(site);
	}

	public void clearSites()
	{
		sites.clear();
	}

	/**
	 * Determine a Site close to the specified location.
	 * 
	 * @return a close Site, or null if none is found within
	 *         <code>clickTolerance</code> pixels distance.
	 */
	public Site getSite(int x, int y)
	{
		for (Site site : sites) {
			double distance = new kn.uni.voronoitreemap.j2d.Point2D(x, y)
					.distance(site.getX(), site.getY());
			if (distance < clickTolerance) {
				return site;
			}
		}
		return null;
	}

	public void computeDiagram()
	{
		PowerDiagram diagram = new PowerDiagram(sites, clipPoly);
		diagram.computeDiagram();
		repaint();
	}

	@Override
	public void paint(Graphics g2)
	{
		super.paint(g2);
//		paintComponents(g2);

		Graphics2D g = (Graphics2D) g2;
		Site[] array = sites.array;
		int size = sites.size;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				antialiasing ? RenderingHints.VALUE_ANTIALIAS_ON
						: RenderingHints.VALUE_ANTIALIAS_OFF);

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
		g.setColor(colorEdges);
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

		// Draw Voronoi cell centroids
		if (showCentroids) {
			g.setColor(colorCentroids);
			for (int z = 0; z < size; z++) {
				Site s = array[z];

				PolygonSimple poly = s.getPolygon();
				if (poly == null) {
					continue;
				}

				// Draw the centroid of the Voronoi cell
				Point2D center = poly.getCentroid();
				int r2 = sizeCentroidSquares;
				g.drawRect((int) center.getX() - r2, (int) center.getY() - r2,
						2 * r2, 2 * r2);
			}
		}

		// Draw sites
		for (int z = 0; z < size; z++) {
			Site s = array[z];
			double posX = s.getX();
			double posY = s.getY();

			// Draw an inner square around the site...
			int r1 = sizeInnerSquares;
			if (s.getPolygon() != null) {
				g.setColor(colorSitesWithCell);
			} else {
				g.setColor(colorSitesWithoutCell);
			}
			g.drawRect((int) posX - r1, (int) posY - r1, 2 * r1, 2 * r1);

			// ...and outer square...
			g.setColor(colorSites);
			int r2 = sizeOuterSquares;
			g.drawRect((int) posX - r2, (int) posY - r2, 2 * r2, 2 * r2);

			// ...and a circle that reflects the weight
			g.setColor(colorCircles);
			double radius = Math.sqrt(s.getWeight());
			g.drawOval((int) posX - (int) radius, (int) posY - (int) radius,
					(int) (2 * radius), (int) (2 * radius));
		}

		this.validate();
	}

	public boolean isAntialiasing()
	{
		return antialiasing;
	}

	public void setAntialiasing(boolean antialiasing)
	{
		this.antialiasing = antialiasing;
	}

	public boolean isShowCentroids()
	{
		return showCentroids;
	}

	public void setShowCentroids(boolean showCentroids)
	{
		this.showCentroids = showCentroids;
	}

}
