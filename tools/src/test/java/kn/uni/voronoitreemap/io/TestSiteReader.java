package kn.uni.voronoitreemap.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import kn.uni.voronoitreemap.j2d.Site;

/**
 * @author Sebastian Kuerten
 */
public class TestSiteReader
{

	@Test
	public void write() throws IOException
	{
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("test1.txt");
		SiteReader reader = new SiteReader(input);
		List<Site> sites = reader.read();

		List<Site> expectedSites = TestData.SITES;
		Assert.assertEquals(expectedSites.size(), sites.size());

		double delta = 0.00001;

		for (int i = 0; i < sites.size(); i++) {
			Site site = sites.get(i);
			Site expectedSite = expectedSites.get(i);

			Assert.assertEquals(expectedSite.getX(), site.getX(), delta);
			Assert.assertEquals(expectedSite.getY(), site.getY(), delta);
			Assert.assertEquals(expectedSite.getWeight(), site.getWeight(),
					delta);
		}

	}

}
