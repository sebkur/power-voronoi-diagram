package kn.uni.voronoitreemap.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * Deserialization of Site collections
 * 
 * @author Sebastian Kuerten
 */
public class SiteReader
{

	private InputStream input;

	public SiteReader(InputStream input)
	{
		this.input = input;
	}

	public List<Site> read()
	{
		List<Site> sites = new ArrayList<>();

		return sites;
	}

}
