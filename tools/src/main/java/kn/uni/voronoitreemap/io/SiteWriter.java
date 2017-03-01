package kn.uni.voronoitreemap.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * Serialization of Site collections
 * 
 * @author Sebastian Kuerten
 */
public class SiteWriter
{

	private static NumberFormat nf = DecimalFormat.getNumberInstance(Locale.US);
	static {
		nf.setMinimumFractionDigits(1);
		nf.setMaximumFractionDigits(10);
	}

	private OutputStream output;
	private BufferedWriter writer;

	public SiteWriter(OutputStream output)
	{
		this.output = output;
		writer = new BufferedWriter(
				new OutputStreamWriter(output, StandardCharsets.UTF_8));
	}

	public void write(Site site) throws IOException
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append(nf.format(site.getX()));
		buffer.append(' ');
		buffer.append(nf.format(site.getY()));
		buffer.append(' ');
		buffer.append(nf.format(site.getWeight()));
		writer.write(buffer.toString());
		writer.newLine();
	}

	public void flush() throws IOException
	{
		writer.flush();
		output.flush();
	}

}
