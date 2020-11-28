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
public class BlogTests {


	@Test
	public void getAuthorID_setAuthorID_ReturnsCorrectValues() {
		
		Blog blog = new Blog();
		blog.setAuthorID(913);

		assertEquals(913, blog.getAuthorID());
		assertNotEquals(912, blog.getAuthorID());
	}

	@Test
	public void getBlogName_setBlogName_ReturnsCorrectValues() {
		
		Blog blog = new Blog();
		blog.setBlogName("Sports Blog Test");

		assertEquals("Sports Blog Test", blog.getBlogName());
		assertNotEquals("sports Blog Test", blog.getBlogName());
	}

	@Test
	public void getBlogBody_setBlogBody_ReturnsCorrectValues() {
		
		Blog blog = new Blog();
		blog.setBlogBody("Blog Body Test");

		assertEquals("Blog Body Test", blog.getBlogBody());
		assertNotEquals("blog Body Test", blog.getBlogBody());
	}

	@Test
	public void getPermission_setPermission_ReturnsCorrectValues() {
		
		Blog blog = new Blog();
		blog.setPermission("Blog Permission Test");

		assertEquals("Blog Permission Test", blog.getPermission());
		assertNotEquals("blog Permission Test", blog.getPermission());
	}

	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		Blog blog = new Blog();
		blog.setId(5290);

		assertEquals(5290, blog.getId());
		assertNotEquals(5291, blog.getId());
	}

	@Test
	public void getTags_setTags_ReturnsCorrectValues() {
		
		List<String> tagList = new ArrayList<>();
		tagList.add("Tag 1");
		tagList.add("Tag 2");
		tagList.add("Tag 3");

		Blog blog = new Blog();
		blog.setTags(tagList);

		assertEquals("Tag 1", blog.getTags().get(0));
		assertNotEquals("tag 1", blog.getTags().get(0));
		assertEquals("Tag 2", blog.getTags().get(1));
		assertNotEquals("tag 2", blog.getTags().get(1));
		assertEquals("Tag 3", blog.getTags().get(2));
		assertNotEquals("tag 3", blog.getTags().get(2));
	}
}
