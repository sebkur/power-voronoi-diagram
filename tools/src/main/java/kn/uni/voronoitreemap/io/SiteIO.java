package kn.uni.voronoitreemap.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * Serialization and deserialization utilities
 * 
 * @author Sebastian Kuerten
 */
public class SiteIO
{

	public static void write(OutputStream output, Site[] sites)
			throws IOException
	{
		SiteWriter writer = new SiteWriter(output);
		for (Site site : sites) {
			writer.write(site);
		}
	}

	public static List<Site> read(InputStream input)
	{
		SiteReader reader = new SiteReader(input);
		List<Site> result = reader.read();
		return result;
	}

}
