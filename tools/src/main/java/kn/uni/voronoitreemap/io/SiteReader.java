package kn.uni.voronoitreemap.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.common.base.Splitter;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * Deserialization of Site collections
 * 
 * @author Sebastian Kuerten
 */
public class SiteReader
{

	private static NumberFormat nf = DecimalFormat.getNumberInstance(Locale.US);

	private BufferedReader reader;

	public SiteReader(InputStream input)
	{
		reader = new BufferedReader(
				new InputStreamReader(input, StandardCharsets.UTF_8));
	}

	public List<Site> read() throws IOException
	{
		List<Site> sites = new ArrayList<>();
		Splitter splitter = Splitter.on(' ');

		int i = 0;
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			i++;

			List<String> parts = splitter.splitToList(line);
			if (parts.size() != 3) {
				continue;
			}

			try {
				double x = nf.parse(parts.get(0)).doubleValue();
				double y = nf.parse(parts.get(1)).doubleValue();
				double weight = nf.parse(parts.get(2)).doubleValue();
				sites.add(new Site(x, y, weight));
			} catch (ParseException e) {
				throw new IOException("Error while parsing number in line " + i,
						e);
			}
		}

		return sites;
	}

}
