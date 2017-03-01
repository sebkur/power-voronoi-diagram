package kn.uni.voronoitreemap.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * @author Sebastian Kuerten
 */
public class TestSiteWriter
{

	@Test
	public void write() throws IOException
	{
		System.out.println("Write to System.out:");
		write(System.out);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		write(baos);
		String output = new String(baos.toByteArray(), StandardCharsets.UTF_8);

		System.out.println("Write to byte array:");
		System.out.print(output);

		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("test1.txt");
		String expectedOutput = CharStreams
				.toString(new InputStreamReader(input, StandardCharsets.UTF_8));
		Closeables.closeQuietly(input);

		System.out.println("Expected output:");
		System.out.print(expectedOutput);

		Assert.assertEquals(expectedOutput, output);
	}

	private void write(OutputStream output) throws IOException
	{
		SiteWriter writer = new SiteWriter(output);

		for (Site site : TestData.SITES) {
			writer.write(site);
		}

		writer.flush();
	}

}
