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

import javax.swing.JFrame;

import kn.uni.voronoitreemap.j2d.PolygonSimple;

/**
 * Show a JFrame with a {@link JPolygon} inside.
 * 
 * @author Arlind Nocaj
 * @author Sebastian Kuerten
 */
public class TestJPolygon {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(50, 50, 600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPolygon polygon = new JPolygon(1);
		polygon.setDraw(true);
		polygon.setFillColor(Color.RED);

		PolygonSimple p = new PolygonSimple();
		p.add(30, 50);
		p.add(300, 30);
		p.add(150, 100);
		p.add(30, 50);
		polygon.setPolygon(p);
		frame.add(polygon);
		frame.setVisible(true);
	}

}
