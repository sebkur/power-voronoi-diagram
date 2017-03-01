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
package kn.uni.voronoitreemap.convexClip;

import kn.uni.voronoitreemap.j2d.Point2D;

public class CVertexList
{

	private double epsilon = 0.00000001;
	public int n;
	public CVertex head;

	public void InsertBeforeHead(CVertex vertex)
	{
		if (head == null) {
			head = vertex;
			vertex.next = vertex;
			vertex.prev = vertex;
			n = 1;
		} else {
			if (!checkForIdentiy(vertex)) {
				head.prev.next = vertex;
				vertex.prev = head.prev;
				vertex.next = head;
				head.prev = vertex;
				++n;
			}
		}
	}

	private double abs(double a, double b)
	{
		double i = b - a;
		if (i > 0) {
			return i;
		} else {
			return -i;
		}
	}

	private boolean checkForIdentiy(CVertex vertex)
	{
		Point2D c = vertex.v;
		Point2D comp = head.prev.v;
		if (abs(c.x, comp.x) < epsilon && abs(c.y, comp.y) < epsilon)
			return true;
		if (abs(c.x, head.v.x) < epsilon && abs(c.y, head.v.y) < epsilon)
			return true;
		return false;
	}

	public CVertexList copyList()
	{
		CVertex temp1 = head, temp2;
		CVertexList erg = new CVertexList();
		do {
			temp2 = new CVertex();
			temp2.v = temp1.v;
			erg.InsertBeforeHead(temp2);
			temp1 = temp1.next;
		} while (temp1 != head);
		return erg;
	}

	@Override
	public String toString()
	{
		if (n == 0) {
			return "";
		}
		String erg = "";
		CVertex curr = head;
		do {
			erg += " " + curr.toString();
			curr = curr.next;
		} while (curr != head);
		return erg;
	}

	public void ReverseList()
	{
		CVertexList listcopy = copyList();
		CVertex temp1, temp2;
		head = null;
		n = 0;

		// Fill this list in proper order:
		temp1 = listcopy.head;
		do {
			temp2 = new CVertex();
			temp2.v = temp1.v;
			InsertBeforeHead(temp2);
			temp1 = temp1.prev;
		} while (temp1 != listcopy.head);
		// System.out.println("Reversing list...");
	}

}
