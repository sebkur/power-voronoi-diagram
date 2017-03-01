package kn.uni.voronoitreemap.io;

import java.io.OutputStream;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * Serialization of Site collections
 * 
 * @author Sebastian Kuerten
 */
public class SiteWriter
{

	private OutputStream output;

	public SiteWriter(OutputStream output)
	{
		this.output = output;
	}

	public void write(Site site)
	{

	}

}
