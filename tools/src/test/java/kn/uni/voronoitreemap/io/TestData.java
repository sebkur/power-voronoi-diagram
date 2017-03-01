package kn.uni.voronoitreemap.io;

import java.util.ArrayList;
import java.util.List;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * @author Sebastian Kuerten
 */
public class TestData
{

	public static List<Site> SITES = new ArrayList<>();
	static {
		SITES.add(new Site(1, 1, 10));
		SITES.add(new Site(2, 2, 20));
		SITES.add(new Site(2.5, 2.5, 25));
		SITES.add(new Site(3, 4, 30));
		SITES.add(new Site(3, 4, 2 / 3d));
	}

}
