package edu.weber.group2.cms.blogPost.model;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadBlogTests {


	@Test
	public void getAuthorFirstName_setAuthorFirstName_ReturnsCorrectValues() {
		
		ReadBlog readBlog = new ReadBlog();
		readBlog.setAuthorFirstName("Margorie");

		assertEquals("Margorie", readBlog.getAuthorFirstName());
		assertNotEquals("margorie", readBlog.getAuthorFirstName());
	}

	@Test
	public void getBlogName_setBlogName_ReturnsCorrectValues() {
		
		ReadBlog readBlog = new ReadBlog();
		readBlog.setBlogName("Sports Blog Test");

		assertEquals("Sports Blog Test", readBlog.getBlogName());
		assertNotEquals("sports Blog Test", readBlog.getBlogName());
	}

	@Test
	public void getBlogBody_setBlogBody_ReturnsCorrectValues() {
		
		ReadBlog readBlog = new ReadBlog();
		readBlog.setBlogBody("Blog Body Test");

		assertEquals("Blog Body Test", readBlog.getBlogBody());
		assertNotEquals("readBlog Body Test", readBlog.getBlogBody());
	}

	@Test
	public void getPermissionID_setPermissionID_ReturnsCorrectValues() {
		
		ReadBlog readBlog = new ReadBlog();
		readBlog.setPermissionID(883);

		assertEquals(883, readBlog.getPermissionID());
		assertNotEquals(884, readBlog.getPermissionID());
	}

	@Test
	public void getBlogID_setBlogID_ReturnsCorrectValues() {
		
		ReadBlog readBlog = new ReadBlog();
		readBlog.setBlogID(5290);

		assertEquals(5290, readBlog.getBlogID());
		assertNotEquals(5291, readBlog.getBlogID());
	}

	@Test
	public void getTags_setTags_ReturnsCorrectValues() {

		// Creating these Integer objects eliminates abiguity with 
		// the assertEquals() methods while comparing
		Integer int1 = 23;
		Integer int2 = 567;
		Integer int3 = 12;
		
		List<Integer> tagList = new ArrayList<>();
		tagList.add(int1);
		tagList.add(int2);
		tagList.add(int3);

		ReadBlog readBlog = new ReadBlog();
		readBlog.setTags(tagList);

		assertEquals(int1, readBlog.getTags().get(0));
		assertNotEquals(int2, readBlog.getTags().get(0));
		assertEquals(int2, readBlog.getTags().get(1));
		assertNotEquals(int3, readBlog.getTags().get(1));
		assertEquals(int3, readBlog.getTags().get(2));
		assertNotEquals(int1, readBlog.getTags().get(2));
	}
}
